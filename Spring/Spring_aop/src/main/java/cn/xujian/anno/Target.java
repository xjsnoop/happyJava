package cn.xujian.anno;

import org.springframework.stereotype.Component;

//目标类
@Component("target")
public class Target implements TargetInterface {
    public void save() {
        System.out.println("save running......");
    }
}
