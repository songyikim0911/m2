package org.zerock.m2.dao;



import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
abstract class JdbcTemplate {  //클래스가 퍼블릭일 필요가 없다. dao에서만 바라보면 돼서

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;


    public void makeAll() throws RuntimeException {
        try {
            makeConnection();
            execute();
            log.info("END");
        }catch(Exception e){
            log.error(e.getMessage());    // 어떤문제 생겼는지.
            throw new RuntimeException(e.getMessage());  //에러를 던져준다.
        }finally {
            finish();
        }
    }    //커넥션이 닫히는 코드

    protected abstract void execute()throws Exception;

    private void makeConnection() throws Exception{
        log.info("makeconnection");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bit08db","bituser","bituser");
    }

    private void finish() {
        log.info("------------finish--------------");
        log.info("Template ResultSet: "+ resultSet);
        log.info("Template PreparedStatement: "+preparedStatement);
        log.info(connection);

        if(resultSet != null){
            try{ resultSet.close(); }catch(Exception e){}
        }

        if(preparedStatement != null){
            try{ preparedStatement.close(); }catch(Exception e){}
        }

        if(connection != null){
            try{ connection.close(); }catch(Exception e){}
        }
    }
}
