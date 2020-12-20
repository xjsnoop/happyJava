package cn.xujian.dao.impl;

import cn.xujian.dao.UserDao;
import cn.xujian.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserDaoImpl implements UserDao {
//    //无参构造方法
//    public UserDaoImpl(){
//        System.out.println("userDaoImpl对象被创建了");
//    }
//    //初始化方法
//    public void init(){
//        System.out.println("初始化操作");
//    }
//    //销毁的方法
//    public void destory(){
//        System.out.println("销毁的操作");
//    }
    private List<String> strList;
    private Map<String, User> strMap;
    private Properties properties;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public void setStrMap(Map<String, User> strMap) {
        this.strMap = strMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void save() {
        System.out.println(strList);
        System.out.println(strMap);
        System.out.println(properties);
        System.out.println(name+"==========="+age);
        System.out.println("save running");
    }
}
