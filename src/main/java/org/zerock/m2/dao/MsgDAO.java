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

    private static final String SQL_LIST ="select mno, who,  whom, if(whom = ? ,'R','S') kind, content, regdate, opendate\n" +
            "from \n" +
            "  tbl_msg \n" +
            "where \n" +
            "  whom = ? or who =? \n" +
            "order by kind asc, mno desc";


    private static final String SQL_SELECT = "select mno, who, whom, content, regdate," +
            "opendate from tbl_msg where mno=?";
// ?에 넣는 mno에 해당하는, 값들 ( mno, who, whom,content,regdate,opendate)을 확인하는 구문
    //mno를 조건으로 값을 조회하는 구문


    private static final String SQL_UPDATE_OPEN = "update tbl_msg set opendate = now() where mno = ?";

    private static final String SQL_DELETE = "delete from tbl_msg where mno=? and who=?";

    public void delete(Long mno, String who) throws RuntimeException{
        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception {

                preparedStatement = connection.prepareStatement(SQL_DELETE);
                preparedStatement.setLong(1, mno);
                preparedStatement.setString(2, who);
                preparedStatement.executeUpdate();

            }
        }.makeAll();
    }

    public MsgDTO select(Long mno) throws RuntimeException{

    MsgDTO msgDTO = MsgDTO.builder().build();

        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception {

                preparedStatement = connection.prepareStatement(SQL_UPDATE_OPEN);
                preparedStatement.setLong(1,mno);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                preparedStatement = null;//첫번째 쿼리 끝, 비우기.

                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setLong(1,mno);
                resultSet= preparedStatement.executeQuery();
                //1)쿼리를 set해서 실행.resultSet에 값담음

                resultSet.next();
                //mno,who, whom,content,regdate,
                //opnedate

                //2)resultSet에 담은값을 get해서 msgDTO에 담기.
                msgDTO.setMno(resultSet.getLong(1));
                msgDTO.setWho(resultSet.getString(2));
                msgDTO.setWhom(resultSet.getString(3));
                msgDTO.setContent(resultSet.getString(4));
                msgDTO.setRegdate(resultSet.getTimestamp(5));

                msgDTO.setOpendate(resultSet.getTimestamp(6));


            }
        }.makeAll();
    return msgDTO;
    }


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
