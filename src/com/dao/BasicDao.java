package com.dao;

import com.utils.JDBCUtilsDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<E> {
    QueryRunner queryRunner=new QueryRunner();
    public List<E> QueryMulti(String sql,Class<E> cls,Object...parameter){
        Connection connection= JDBCUtilsDruid.getConnect();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<E>(cls), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);//这是运行异常，因为不是编译异常，所以在调用这个方法的调用者无需做进一步处理，但是还是会捕获到
        }
//        finally {
//            JDBCUtilsDruid.closeConnect(null,connection,null);
//        }
    }
    public E QuerySingle(String sql,Class<E> cls,Object...parameter){
        Connection connection = JDBCUtilsDruid.getConnect();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<E>(cls), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsDruid.closeConnect(null,connection,null);
//        }
    }
    public Object QueryOne(String sql, Object...parameter){
        Connection connection=JDBCUtilsDruid.getConnect();
        try {
           return queryRunner.query(connection,sql,new ScalarHandler(),parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsDruid.closeConnect(null,connection,null);
//        }
    }
    public Integer QueryCUD(String sql,Object...parameter){
        Connection connection=JDBCUtilsDruid.getConnect();
        try {
            return   queryRunner.update(connection,sql, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsDruid.closeConnect(null,connection,null);
//        }
    }
}
