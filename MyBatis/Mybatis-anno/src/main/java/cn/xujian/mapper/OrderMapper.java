package cn.xujian.mapper;


import cn.xujian.domain.Orders;
import cn.xujian.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("select * ,o.id oid from orders o, user1 u where o.uid = u.id")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "user.id"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "password",property = "user.password")
    })
    public List<Orders> findAll();

    @Select("select * from orders")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(
                    property = "user" , //要封装的属性名称
                    column = "uid",     //根据哪个字段取查询user表的数据
                    javaType = User.class,  //要封装的实体类型
                    //select属性  代表查询那个接口的方法获得数据
                    one = @One(select = "cn.xujian.mapper.UserMapper.findById")
            )
    })
    public List<Orders> findAll02();

    @Select("select * from orders where uid=#{uid}")
    public List<Orders> findByUid(int uid);
}

