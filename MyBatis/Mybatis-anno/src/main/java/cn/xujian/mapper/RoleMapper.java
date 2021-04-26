package cn.xujian.mapper;

import cn.xujian.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("select * from sys_user_role sur,sys_role sr where sur.roleId=sr.id and sur.userId=#{id} ")
    public List<Role> findByUserId(int uid);
}
