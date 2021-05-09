package cn.xujian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
////扫描mybatis的所有业务mapper接口
//@MapperScan("cn.xujian.mapper")

//通用mapper
@MapperScan("cn.xujian.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
