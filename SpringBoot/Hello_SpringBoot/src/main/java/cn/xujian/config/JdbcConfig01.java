package cn.xujian.config;

import cn.xujian.pojo.JdbcProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//1.2 Spring Boot属性注入方式

//@Configuration
//public class JdbcConfig01 {
//    @Bean
//    @ConfigurationProperties(prefix = "jdbc")
//    public DataSource dataSource(){
//        return new DruidDataSource();
//    }
//}
