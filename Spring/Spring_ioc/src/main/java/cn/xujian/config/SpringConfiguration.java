package cn.xujian.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//标志该类是Spring的核心配置类
@Configuration
//扫描目录
@ComponentScan("cn.xujian")
//加载配置文件
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    //将当前方法的返回值以指定名称存储到Spring容器中
    @Bean("dataSource-Druid-an")
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //设置驱动
        dataSource.setDriverClassName(driver);
        //设置连接url
        dataSource.setUrl(url);
        //设置用户名
        dataSource.setUsername(username);
        //设置密码
        dataSource.setPassword(password);
        return dataSource;
    }
}
