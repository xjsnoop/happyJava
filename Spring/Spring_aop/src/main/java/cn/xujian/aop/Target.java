package cn.xujian.aop;
//目标类
public class Target implements TargetInterface {
    public void save() {
        System.out.println("save running......");
    }
}
