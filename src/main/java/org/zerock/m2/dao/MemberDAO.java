package org.zerock.m2.dao;

import org.zerock.m2.dto.MemberDTO;

public enum MemberDAO {

    INSTANCE;

    private static final String SQL_LOGIN="select mid,mpw,mname,nickname,regdate,joindate,moddate \n" +
            "from tbl_member\n" +
            "where mid =? and mpw=?";


    public MemberDTO selectForLogin(String mid, String mpw) throws RuntimeException{

        MemberDTO dto = MemberDTO.builder().build();
        new JdbcTemplate(){
            @Override
            protected void execute() throws Exception {
                preparedStatement = connection.prepareStatement(SQL_LOGIN);
                preparedStatement.setString(1, mid);
                preparedStatement.setString(2, mpw);

                resultSet = preparedStatement.executeQuery();

                if(resultSet.next() == false){
                    throw new Exception("NOT EXIST");
                }

                dto.setMid(resultSet.getString(1));
                dto.setMpw(resultSet.getString(2));
                dto.setMname(resultSet.getString(3));
                dto.setNickname(resultSet.getString(4));
                dto.setJoindate(resultSet.getTimestamp(5));

                dto.setModdate(resultSet.getTimestamp(6));

            }
        }.makeAll();

        return dto;

    }

}
