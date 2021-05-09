//package cn.xujian.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import javax.sql.DataSource;
////1.1 java代码方式配置
//@Configuration
//@PropertySource("classpath:jdbc.properties")
//public class JdbcConfig {
//    @Value("${jdbc.url}")
//    String url;
//    @Value("${jdbc.driver}")
//    String driver;
//    @Value("${jdbc.username}")
//    String username;
//    @Value("${jdbc.password}")
//    String password;
//
//    @Bean
//    public DataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername(username);
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(url);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//}
