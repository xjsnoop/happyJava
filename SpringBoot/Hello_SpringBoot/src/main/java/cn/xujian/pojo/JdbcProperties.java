package cn.xujian.pojo;

//1.2 Spring Boot属性注入方式

import lombok.Data;
//使用lombok的注解，自动生成get set
@Data
public class JdbcProperties {
    private String url;

    private String driverClassName;

    private String username;

    private String password;
}
