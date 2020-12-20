package cn.xujian.proxy.cglib;

import cn.xujian.proxy.jdk.TargetInterface;

public class Target implements TargetInterface {
    public void sava() {
        System.out.println("save running......");
    }
}
