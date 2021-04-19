package cn.xujian.dao.impl;

import cn.xujian.dao.UserDao;
import cn.xujian.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUser() {
        List<User> userList = jdbcTemplate.query("select * from sys_user",new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
//需要返回user的id，使用了jdbc的一个api
    @Override
    public long save(final User user) {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?,?,?,?)", com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setObject(2,user.getUsername());
                preparedStatement.setObject(3,user.getEmail());
                preparedStatement.setObject(4,user.getPassword());
                preparedStatement.setObject(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator, keyHolder);

//        获取生成的主键
        long userId = keyHolder.getKey().longValue();
//        返回当前保存的user的id
        return userId;
    }

    @Override
    public void saveUserRoleRel(Long userId, long[] roleIDs) {
        for (long roleId:roleIDs){
            jdbcTemplate.update("insert into sys_user_role values (?,?)",userId,roleId);
        }

    }

    @Override
    public void delUserRoleRel(long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId = ?",userId);
    }

    @Override
    public void delUserById(long userId) {
        jdbcTemplate.update("delete from sys_user where id = ?",userId);
    }
}
