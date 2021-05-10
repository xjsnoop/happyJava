package cn.xujian.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
//        string字符串
//        redisTemplate.opsForValue().set("str","xujian");
        redisTemplate.boundValueOps("str").set("xujian");
        System.out.println("str = " + redisTemplate.opsForValue().get("str"));
//        hash散列
        redisTemplate.boundHashOps("h_key").put("name","徐健");
        redisTemplate.boundHashOps("h_key").put("age",13);
        // 获取所有域
        Set set = redisTemplate.boundHashOps("h_key").keys();
        System.out.println("hash散列的所有域：" + set);
        //  获取所有值
        List list = redisTemplate.boundHashOps("h_key").values();
        System.out.println("hsah散列所有域的值：" + list);
//        list列表
        redisTemplate.boundListOps("l_key").leftPush("c");
        redisTemplate.boundListOps("l_key").leftPush("b");
        redisTemplate.boundListOps("l_key").leftPush("a");
        List list1 = redisTemplate.boundListOps("l_key").range(0, -1);
        System.out.println("list列表中的所有元素：" + list1);
//        set集合
        redisTemplate.boundSetOps("a_key").add("a","b","c");
        Set set1 = redisTemplate.boundSetOps("a_key").members();
        System.out.println("set集合所有元素 ： " + set1);
//        sorted set 有序集合
        redisTemplate.boundZSetOps("z_key").add("a",30);
        redisTemplate.boundZSetOps("z_key").add("b",10);
        redisTemplate.boundZSetOps("z_key").add("c",20);
        Set set2 = redisTemplate.boundZSetOps("z_key").range(0, -1);
        System.out.println("zset有序集合所有的元素 ： " + set2);


    }
}
