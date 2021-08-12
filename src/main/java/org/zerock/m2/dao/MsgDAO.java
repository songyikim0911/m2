package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MsgDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public enum MsgDAO {
    INSTANCE;
    //DAO 는 DB 용어를 쓴다

    private static final String SQL_INSERT = "insert into tbl_msg (who,whom,content) values(?,?,?)";

    private static final String SQL_LIST = "select mno, who, whom, if(who = ?, 'R', 'S') kind, content, regdate, opendate\n" +
            "from\n" +
            "tbl_msg \n" +
            "where whom=? or who=? \n" +
            "order by kind asc, mno desc";

    public void insert(MsgDTO msgDTO) throws RuntimeException {

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception { //jdbc 인덱스 번호는 1부터시작
                //who,whom,content
                int idx = 1;
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setString(idx++,msgDTO.getWho());
                preparedStatement.setString(idx++,msgDTO.getWhom());
                preparedStatement.setString(idx++,msgDTO.getContent());

                int count = preparedStatement.executeUpdate();
                log.info("count :" + count);
            }
        }.makeAll();
    }

        public Map<String, List<MsgDTO>> selectList(String user)throws RuntimeException{
            Map<String, List<MsgDTO>> listMap = new HashMap<>();
            listMap.put("R", new ArrayList<>());//receive
            listMap.put("S", new ArrayList<>());//send

            new JdbcTemplate(){
                @Override
                protected void execute() throws Exception {

                    preparedStatement = connection.prepareStatement(SQL_LIST);
                    preparedStatement.setString(1,user);
                    preparedStatement.setString(2,user);
                    preparedStatement.setString(3,user);

                    resultSet = preparedStatement.executeQuery();

                    log.info(resultSet);

                    while(resultSet.next()){

                        String kind = resultSet.getString(4);

                        List<MsgDTO> targetList = listMap.get(kind);
                        //mno, who, whom, if(who = ?, 'R', 'S') kind, content,
                        // regdate, opendate

                        targetList.add(MsgDTO.builder()
                                .mno(resultSet.getLong(1))
                                .who(resultSet.getString(2))
                                .whom(resultSet.getString(3))
                                .content(resultSet.getString(5))
                                .regdate(resultSet.getTimestamp(6))
                                .opendate(resultSet.getTimestamp(7))
                                .build());

                    }
                }
            }.makeAll();


            return listMap;
    }


}
