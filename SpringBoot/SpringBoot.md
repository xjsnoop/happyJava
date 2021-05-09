## SpringBoot

### Spring Boot入门

**目标**：能够使用Spring Boot搭建项目

**分析**：

需求：可以在浏览器中访问http://localhost:8080/hello输出一串字符

实现步骤：

1. 创建工程；
2. 添加依赖（启动器依赖，spring-boot-starter-web）；

```java
<!--    加入父工程，继承-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.5</version>
    </parent>

    <groupId>cn.xujian</groupId>
    <artifactId>Hello_SpringBoot</artifactId>
    <version>1.0-SNAPSHOT</version>

<!--    设置jdk版本为1.8-->
    <properties>
        <java.version>1.8</java.version>
    </properties>

<!--导入spring-boot的启动依赖-->

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```



3.创建启动类；（启动类放在其他文件上一层的目录）

```java
//springboot工程都有一个启动引导类，这是工程的入口
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```

4.创建处理器Controller；

```java
// springboot的注解
@RestController
public class HelloController {
//    以get的方式请求并返回字符串
    @GetMapping("hello")
    public String hello(){
        return "hello,springboot";
    }
}
```

5.测试，运行启动类。

**小结**：

Spring Boot工程可以通过添加启动器依赖和创建启动引导类实现快速创建web工程。

> spring-boot-starter-web默认的应用服务器端口是8080

### 1.1   Java代码方式配置

**目标**：可以使用@Value获取配置文件配置项并结合@Bean注册组件到Spring

**分析**：

需求：使用Java代码配置数据库连接池，并可以在处理器中注入并使用

步骤：

1. 添加依赖；

   ```java
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid</artifactId>
       <version>1.1.23</version>
   </dependency>
   ```

2. 创建数据库；

3. 创建数据库连接参数的配置文件jdbc.properties；

4. 创建配置类；

   ```java
   @Configuration
   @PropertySource("classpath:jdbc.properties")
   public class JdbcConfig {
       @Value("${jdbc.url}")
       String url;
       @Value("${jdbc.driver}")
       String driver;
       @Value("${jdbc.username}")
       String username;
       @Value("${jdbc.password}")
       String password;
   
       @Bean
       public DataSource dataSource(){
           DruidDataSource dataSource = new DruidDataSource();
           dataSource.setUsername(username);
           dataSource.setDriverClassName(driver);
           dataSource.setUrl(url);
           dataSource.setPassword(password);
           return dataSource;
       }
   }
   ```

5. 改造处理器类注入数据源并使用

   ```java
       @Autowired
       private DataSource dataSource;
   //    以get的方式请求并返回字符串
       @GetMapping("hello")
       public String hello(){
           System.out.println(dataSource);
           return "hello,springboot";
       }
   ```

### 1.2   Spring Boot属性注入方式

**目标**：能够使用@ConfigurationProperties实现Spring Boot配置文件配置项读取和应用

**分析**：

需求：将配置文件中的配置项读取到一个对象中；

实现：可以使用Spring Boot提供的注解@ConfigurationProperties，该注解可以将Spring Boot的配置文件（默认必须为application.properties或application.yml）中的配置项读取到一个对象中。

实现步骤：

1. 创建配置项类JdbcProperties类，在该类名上面添加@ConfigurationProperties；

2. 将jdbc.properties修改名称为application.properties；

   ```java
   jdbc.driverClassName=com.mysql.jdbc.Driver
   jdbc.url=jdbc:mysql://localhost:3306/springboot_test
   jdbc.username=root
   jdbc.password=xj
   ```

3. 将JdbcProperties对象注入到JdbcConfig；

4. 测试

**小结**：

- 使用@ConfigurationProperties编写配置项类将配置文件中的配置项设置到对象中

```java
//jdbc表示application文件中的前缀，配置类中的类变量名必须要与前缀之后的名称保持  松散绑定(相同)。   注：注解解析器没有的话导入jar，spring-boot-configuration-processor

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


```

- 使用@ConfigurationProperties在方法上面使用

```java
//使用此方法不需要在对象上加@ConfigurationProperties(prefix = "jdbc")注解，把每个属性当对象注入，自动匹配。（依靠对象内set方法）
@Configuration
public class JdbcConfig01 {
	@Bean
	@ConfigurationProperties(prefix = "jdbc")
	public DataSource dataSource() {
   		return new DruidDataSource();
	}
}
```



### 1.3   多个yml文件配置

**目标**：可以将多个yml文件在application.yml文件中配置激活

**分析**：

yml与properties配置文件除了展示形式不相同以外，其它功能和作用都是一样的；在项目中原路的读取方式不需要改变。

1）yml配置文件的特征：

1. 树状层级结构展示配置项；
2. 配置项之间如果有关系的话需要分行空两格；
3. 配置项如果有值的话，那么需要在 `:`之后空一格再写配置项值；

将application.properties配置文件修改为application.yml的话：

```yml
jdbc:
  driverClassName: com.mysql.jdbc.Driver
  url: jdbc:mysql://localhost:3306/springboot_test
  username: root
  password: xj

key:
  abc: cba
  def:
    - g
    - h
    - j
```



2）多个yml配置文件；在spring boot中是被允许的。这些配置文件的名称必须为application-***.yml，并且这些配置文件必须要在application.yml配置文件中激活之后才可以使用。



3）如果properties和yml配置文件同时存在在spring boot项目中；那么这两类配置文件都有效。在两个配置文件中如果存在同名的配置项的话会以properties文件的为主。



**小结**：

在多个配置文件时，需要将这些文件在application.yml文件中进行激活：

```yml
#激活配置文件;需要指定其它的配置文件名称
spring:
  profiles:
    active: abc,def
```

### 1.4   自动配置原理

**目标**：了解Spring Boot项目的配置加载流程

**小结**：

- 在 `META-INF\spring.fatories`文件中定义了很多自动配置类；可以根据在pom.xml文件中添加的 启动器依赖自动配置组件
- 通过如下流程可以去修改application配置文件，改变自动配置的组件默认参数

![1560091228494](D:/Develop/Spring学习课件笔记/1-微服务SpringBoot快速开发/1-微服务SpringBoot快速开发/笔记/assets/1560091228494.png)

### 1.5  lombok应用

**目标**：使用lombok的注解实现pojo类的简化

**分析**：

使用Spring Boot整合SSM工程；需要使用到数据库数据。

- 将数据库表数据导入到数据库中（springboot_test）；

- 编写数据库表对应的实体类；一般情况下需要编写get/set/toString等这些方法会耗时并且会让实体类看起来比较臃肿。可以使用lombok插件对实体类进行简化。

  lombok是一个插件工具类包；提供了一些注解@Data、@Getter等这些注解去简化实体类中的构造方法、get/set等方法的编写。

  1. 在IDEA中安装lombok插件；
  2. 添加lombok对应的依赖到项目pom.xml文件；
  3. 改造实体类使用lombok注解

**小结**：

在Bean上使用：
@Data ：自动提供getter和setter、hashCode、equals、toString等方法
@Getter：自动提供getter方法
@Setter：自动提供setter方法
@Slf4j：自动在bean中提供log变量，其实用的是slf4j的日志功能。

### 1.6   Spring Boot整合-SpringMVC端口和静态资源

**目标**：可以修改tomcat的端口和访问项目中的静态资源

**分析**：

- 修改tomcat端口

  查询**Properties，设置配置项（前缀+类变量名）到application配置文件中

- 访问项目中的静态资源

  静态资源放置的位置；放置静态资源并访问这些资源

**小结**：

- 修改项目tomcat端口：

```yml
#tomcat端口
server:
  port: 80
```

- 在spring boot项目中静态资源可以放置在如下目录：

  ![1560096384799](D:/Develop/Spring学习课件笔记/1-微服务SpringBoot快速开发/1-微服务SpringBoot快速开发/笔记/assets/1560096384799.png)

###    1.7   Spring Boot整合-SpringMVC拦截器

**目标**：可以在Spring Boot项目中配置自定义SpringMVC拦截器

**分析**：

1. 编写拦截器（实现HandlerInterceptor）；

   ```java
   @Slf4j
   public class MyInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           log.debug("pre方法");
           return true;
       }
   
       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           log.debug("post方法");
       }
   
       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           log.debug("after方法");
       }
   }
   ```

2. 编写配置类实现 WebMvcConfigurer，在该类中添加各种组件；

3. 测试

**小结**：

可以在spring boot项目中通过配置类添加各种组件；如果要添加拦截器的话：

```java
package com.itheima.config;

import com.itheima.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //注册拦截器
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    //添加拦截器到spring mvc拦截器链
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
    }
}

```



### 1.8   Spring Boot整合-事务和连接池

**目标**：配置Spring Boot自带默认的hikari数据库连接池和使用@Transactional注解进行事务配置

**分析**：

- 事务配置

  1. 添加事务相关的启动器依赖，mysql相关依赖；

     ```java
     <!--        mysql相关依赖包-->
             <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-starter-jdbc</artifactId>
             </dependency>
             <dependency>
                 <groupId>mysql</groupId>
                 <artifactId>mysql-connector-java</artifactId>
             </dependency>
     ```

  2. 编写业务类UserService使用事务注解@Transactional

- 数据库连接池hikari配置

  只需要在application配置文件中指定数据库相关参数

**小结**：

- 事务配置；只需要添加jdbc启动器依赖
- 数据库连接池使用默认的hikari，在配置文件中配置如下：

```java
#配置数据库连接池参数
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot_test
    username: root
    password: xj
```



### 1.9    Spring Boot整合-Mybatis

**目标**：配置Mybatis在Spring Boot工程中的整合包，设置mybatis的实体类别名，输出执行sql语句配置项

**分析**：

1. 添加启动器依赖；
2. 配置Mybatis：实体类别名包，日志，映射文件等；
3. 配置MapperScan

**小结**：

- 添加mybatis官方对于spring boot的一个启动器

  ```java
  <!--        springboot整合mybatis-->
          <dependency>
              <groupId>org.mybatis.spring.boot</groupId>
              <artifactId>mybatis-spring-boot-starter</artifactId>
              <version>2.1.4</version>
          </dependency>
  ```
  

  
- 配置mybatis

  ```yml
  mybatis:
    # 实体类别名包路径
    type-aliases-package: cn.xujian.pojo
    # 映射文件路径
    # mapper-locations: classpath:mappers/*.xml
    configuration:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  ```

  

- 设置启动器类中的mapper扫描

  ```java
  @SpringBootApplication
  //扫描mybatis的所有业务mapper接口
  @MapperScan("cn.xujian.mapper")
  public class Application {
      public static void main(String[] args) {
          SpringApplication.run(Application.class,args);
      }
  }
  ```

### 1.10    Spring Boot整合-通用Mapper

**目标**：配置通用Mapper组件到Spring Boot项目中并使用Mapper<T>接口

**分析**： 

通用Mapper：可以实现自动拼接sql语句；所有的mapper都不需要编写任何方法也就是不用编写sql语句。可以提高开发效率。

1. 添加启动器依赖；

   ```java
   <!--通用mapper依赖项-->
           <dependency>
               <groupId>tk.mybatis</groupId>
               <artifactId>mapper-spring-boot-starter</artifactId>
               <version>2.1.5</version>
           </dependency>
   ```

2. 改造UserMapper继承Mapper<User>；

   ```java
   public interface UserMapper extends Mapper<User> {}
   ```

3. 修改启动引导类Application中的Mapper扫描注解；

   ```java
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
   ```

4. 修改User实体类添加jpa注解；

   ```java
   @Data
   //指定关联的表
   @Table(name = "tb_user")
   public class User {
       @Id
   //    主键回填
       @KeySql(useGeneratedKeys = true)
       private Long id;
   //    用于对象属性名和数据库属性名不一致时指定映射。
       @Column(name = "user_name")
       private String user_name;
       
       private String password;
       
       private String name;
       
       private Integer age;
       
       private Integer sex;
       
       private Date birthday;
       
       private String note;
       
       private Date created;
       
       private Date updated;
       
   }
   ```

   

5. 改造UserService实现业务功能；

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserMapper userMapper;
   //    根据id查询
       public User query(Long id){
           return userMapper.selectByPrimaryKey(id);
       }
   //    新增保存用户
       @Transactional
       public void saveUser(User user){
   //        选择性新增，如果属性为空则该属性不会出现在insert语句上
           userMapper.insertSelective(user);
           System.out.println("新增用户");
       }
   }
   ```

**小结**：

> 在启动引导类上面的mapper扫描注解 一定要修改为 通用mapper的扫描注解

####  Spring Boot整合测试

**目标**：可以访问处理器对应路径将数据库中的数据根据id查询

**分析**：

1. 改造HelloController，注入UserService利用其方法实现查询；
2. 启动项目进行测试 http://localhost/user/用户id --> http://localhost/user/8

**小结**：

修改了HelloController：

```java
@RestController
public class HelloControll {
    @Autowired
    private UserService userService;
    /**
     * @param: id
     * @return: user
     * @auther: xj
     * @date: 2021-05-09 22:14
     * @description:根据用户id查询并返回用户
    */
    @GetMapping("user/{id}")
    public User queryById(@PathVariable Long id){
        return userService.query(id);
    }

    /**
     * @param: user
     * @return:
     * @auther: xj
     * @date: 2021-05-09 22:23
     * @description:插入数据
    */
    @GetMapping("user/add")
    public void addUser(){
        //    模拟一个user对象
        User user = new User("徐健","333");
        userService.saveUser(user);
    }
}
```

### 1.11    Spring Boot整合-Junit

**目标**：在Spring Boot项目中使用Junit进行单元测试UserService的方法

**分析**：

1. 添加启动器依赖spring-boot-starter-test；
2. 编写测试类

**小结**：

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryById() {
        User user = userService.queryById(8L);
        System.out.println("user = " + user);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUserName("test2");
        user.setName("test2");
        user.setAge(13);
        user.setPassword("123456");
        user.setSex(1);
        user.setCreated(new Date());
        userService.saveUser(user);
    }
}
```

> 在Spring Boot项目中如果编写测试类则必须要在类上面添加@SpringBootTest

### 1.12    Spring Boot整合-redis

**目标**：在Spring Boot项目中使用Junit测试RedisTemplate的使用

**分析**：

1. 添加启动器依赖；spring-boot-starter-data-redis
2. 配置application.yml中修改redis的连接参数；（redis需要启动）
3. 编写测试类应用RedisTemplate操作redis中的5种数据类型（string/hash/list/set/sorted set）

**小结**：

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        //string 字符串
        //redisTemplate.opsForValue().set("str", "heima");
        redisTemplate.boundValueOps("str").set("xiaoxu");
        System.out.println("str = " + redisTemplate.opsForValue().get("str"));

        //hash 散列
        redisTemplate.boundHashOps("h_key").put("name", "xiaoxu");
        redisTemplate.boundHashOps("h_key").put("age", 26);
        //获取所有域
        Set set = redisTemplate.boundHashOps("h_key").keys();
        System.out.println(" hash散列的所有域：" + set);
        //获取所有值
        List list = redisTemplate.boundHashOps("h_key").values();
        System.out.println(" hash散列的所有域的值：" + list);

        //list 列表
        redisTemplate.boundListOps("l_key").leftPush("c");
        redisTemplate.boundListOps("l_key").leftPush("b");
        redisTemplate.boundListOps("l_key").leftPush("a");
        //获取全部元素
        list = redisTemplate.boundListOps("l_key").range(0, -1);
        System.out.println(" list列表中的所有元素：" + list);

        // set 集合
        redisTemplate.boundSetOps("s_key").add("a", "b", "c");
        set = redisTemplate.boundSetOps("s_key").members();
        System.out.println(" set集合中的所有元素：" + set);

        // sorted set 有序集合
        redisTemplate.boundZSetOps("z_key").add("a", 30);
        redisTemplate.boundZSetOps("z_key").add("b", 20);
        redisTemplate.boundZSetOps("z_key").add("c", 10);
        set = redisTemplate.boundZSetOps("z_key").range(0, -1);
        System.out.println(" zset有序集合中的所有元素：" + set);
    }
}

```



### 1.13    Spring Boot项目部署

**目标**：将Spring Boot项目使用maven指令打成jar包并运行测试

**分析**：

1. 需要添加打包组件将项目中的资源、配置、依赖包打到一个jar包中；可以使用maven的`package`；
2. 部署：java -jar 包名

**小结**：

- 添加打包组件

  ```xml
      <build>
          <plugins>
             <!-- 打jar包时如果不配置该插件，打出来的jar包没有清单文件 -->
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
              </plugin>
          </plugins>
      </build>
  ```

  

- 部署运行

  ```sh
  java -jar 包名
  ```

  