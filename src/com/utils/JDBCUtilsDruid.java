package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsDruid {


    static DataSource druidDataSource;
    public static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    static{
        try {
            Properties properties = new Properties();
            properties.load(JDBCUtilsDruid.class.getClassLoader().getResourceAsStream("Druid.properties"));
            druidDataSource =  DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnect()  {
//        try {
//            return druidDataSource.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        Connection connection = threadLocal.get();
        if(connection==null){
            try {
                connection=druidDataSource.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void commit(){
        Connection connection = threadLocal.get();
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //每次完成一个业务都要清空一些threadlocal里面的connect，有效避免效率降低
        threadLocal.remove();
    }

    public static void rollback(){
        Connection connection = threadLocal.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        threadLocal.remove();
    }
    public static void closeConnect(ResultSet resultSet,Connection connection, Statement statement){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
