package cn.xujian.mapper;

import cn.xujian.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {
//    保存账户数据
    @Insert("insert into account values(#{id},#{name},#{money})")
    public void save(Account account);
//    查询账户数据
    @Select("select * from account")
    public List<Account> findAll();
}
