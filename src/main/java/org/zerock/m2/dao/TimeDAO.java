package org.zerock.m2.dao;

import lombok.extern.log4j.Log4j2;


@Log4j2
public enum TimeDAO {   //enum하면 인스턴스를몇개 만들지 지정. 하나면 INSTANCE;

    INSTANCE;

    //외부에서 사용하고 싶으면


    private static  final  String SQL = "select now()";  //쿼리문이 변하지 않으면 파이널 선언

    public String getTime() throws RuntimeException {

        StringBuffer buffer = new StringBuffer();//익명클래스를위함..?
        new JdbcTemplate() {
            @Override
            protected void execute() throws Exception {
                preparedStatement = connection.prepareStatement(SQL);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();  // 필수 한칸 띄우기
                buffer.append(resultSet.getString(1));
            }
        }.makeAll();   //makeall. RuntimeException을 던지게 설계 되어있다.

        return buffer.toString();
    }  // 스레드 세이프하게 설계
}


