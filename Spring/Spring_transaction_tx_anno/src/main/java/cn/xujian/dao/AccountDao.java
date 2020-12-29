package cn.xujian.dao;

public interface AccountDao {
    void in(String inMan, double money);
    void out(String inMan, double money);
}
