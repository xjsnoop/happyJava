package cn.xujian.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {
//将java类型转换成数据库需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }
//    将数据库中的类型转换为java类型
//    string s 为数据库中要转换的字段名称
//    resultSet为查询出的结果集
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        将获取的结果集中需要的数据（long）转换后date类型返回
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型转换为java类型
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型转换为java类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
