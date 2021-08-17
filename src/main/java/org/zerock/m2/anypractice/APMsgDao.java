package org.zerock.m2.anypractice;

import org.zerock.m2.dto.MsgDTO;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APMsgDao {

    private static final String SQL_LIST= "select mno, who, whom, if(whom = ? , 'R', 'S'), kind, content, regdate, opendate\n" +
            "from \n" +
            "tbl_msg \n"+
            "where \n"+
            "whom=? or who=? \n" +
            "order by kind asc, mno desc";

    public Map<String, List<MsgDTO>> selectList(String user) throws RuntimeException {

        Map<String, List<MsgDTO>> listMap = new HashMap<>();
        listMap.put("R", new ArrayList<>());//receive
        listMap.put("S", new ArrayList<>());//send

        new JdbcTemplate(){
            @Override
            protected void execute() throws Exception {
                preparedStatement = connection.prepareStatement(SQL_LIST);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2,user);
                preparedStatement.setString(3,user);

                resultSet= preparedStatement.executeQuery();

                while(resultSet.next()){

                    String kind = resultSet.getString(4);

                    List<MsgDTO> targetList = listMap.get(kind);

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
