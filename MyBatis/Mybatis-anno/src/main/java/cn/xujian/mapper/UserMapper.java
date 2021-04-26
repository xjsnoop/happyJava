package cn.xujian.mapper;

import cn.xujian.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("insert into user values(#{id},#{username},#{password})")
    public void save(User user);
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public void update(User user);
    @Delete("delete from user where id=#{id}")
    public void delete(int id);
    @Select("select * from user where id=#{id}")
    public User findById(int id);
    @Select("select * from user")
    public List<User> findAll();

    @Select("select * from user")
    @Results({
            @Result(id =true, column = "id" ,property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(
                    property = "ordersList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "cn.xujian.mapper.OrderMapper.findByUid")
            )
    })
    public List<User> findUserAndOrder();

//    多对多查询
    @Select("select * from user")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(
                    property = "roleList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "cn.xujian.mapper.RoleMapper.findByUserId")
            )
    })
    public List<User> findUserAndRole();
}
