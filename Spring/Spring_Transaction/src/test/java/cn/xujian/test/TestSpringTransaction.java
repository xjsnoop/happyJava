package cn.xujian.test;

import cn.xujian.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_Transaction.xml")
public class TestSpringTransaction {
    @Autowired
    private AccountServiceImpl accountService;
    @Test
    public void test1(){
        accountService.transfer("张三","李四",1);
    }
}
