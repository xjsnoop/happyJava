package cn.xujian.proxy.jdk;

public class Advice {
    //前置增强
    public void before(){
        System.out.println("前置增强");
    }
    //后置增强
    public void after(){
        System.out.println("后置增强");
    }
}
