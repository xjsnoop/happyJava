

##  Spring

### spring是什么

分层的java SE/EE应用，full-stack轻量级开发框架，以IOC（Inserse Of Control   反转控制）和AOP（Aspect Oriented Programming  面向切面编程）为内核。

提供 展现层SpringMVC  和  持久层Spring JDBCTemplate   以及事物管理层等应用技术。

### 体系结构

<img src="https://img-blog.csdnimg.cn/2019102923475419.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoaW5rV29u,size_16,color_FFFFFF,t_70" alt="Spring模块组成(框架组成、整体架构、体系架构、体系结构)_ThinkWon的博客-CSDN博客" style="zoom: 80%;" />

### 快速开发步骤

1. 导入Spring开发的包

```java
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.6.RELEASE</version>
    <scope>compile</scope>
</dependency>
```

1. 编写Dao接口和实现类
2. 创建Spring核心配置文件（放在resources中）
3. 在Spring配置文件中配置UserDaoImpl    

```java
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl"></bean>
```

1. 使用Spring的API获得Bean实例   

```java
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
UserDao userDao = (UserDao) app.getBean("userDao");
```

### Spring  配置文件

####  Bean标签的配置范围 Scope

| 取值范围       | 说明                                                         |
| :------------- | :----------------------------------------------------------- |
| singleton      | 默认值，单例的   （默认）                                    |
| prototype      | 多例的                                                       |
| request        | WEB 项目中，Spring 创建一个 Bean 的对象，将对象存入到 request 域中 |
| session        | WEB 项目中，Spring 创建一个 Bean 的对象，将对象存入到 session 域中 |
| global session | WEB 项目中，应用在 Portlet 环境，如果没有 Portlet 环境那么globalSession 相当于 session |

```java
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl" scope="prototype"></bean>
```

#### Bean生命周期配置

init-method: 指定类中的初始化方法

destory-method:  指定类中的销毁方法

```java
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl" init-method="init" destroy-method="destory"></bean>
```

#### Bean实例化的三种方式

无参构造方法实例化

工厂静态方法实例化          要创建工厂类       

```java
<bean id="userDao" class="cn.xujian.factory.StaticFactory" factory-method="getUserDao" ></bean>
```

工厂实例方法实例化

```java
<!--    //创建工厂的容器 -->
    <bean id="factory" class="cn.xujian.factory.DynamicFactory" ></bean>
<!--    //通过工厂创建userdao -->
    <bean id="userDao" factory-bean="factory" factory-method="getUserDao"></bean>
```

#### Bean的依赖注入   （Dependency Injection）

因为UserService和UserDao都在容器内，可以在spring容器中，将UserDao设置到UserService内部。

使用的方法：     setter     和   构造方法

​																			使用set方法

```java
//在Service的实现类中使用setter
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void save() {
        userDao.save();
    }
}
```

```java
//在spring中声明
//name后为set方法后的方法名首字母小写，ref为引用的容器id
<bean id="userService" class="cn.xujian.service.impl.UserServiceImpl">
    <property name="userDao" ref="userDao"></property>
</bean>
```

​																			构造方法注入

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    //有参构造方法
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    //无参构造方法
    public UserServiceImpl() {
    }
    @Override
    public void save() {
        userDao.save();
    }
}
```

```java
//name后为有参构造的参数，ref为引用的容器id
<bean id="userService" class="cn.xujian.service.impl.UserServiceImpl">
    <constructor-arg name="userDao" ref="userDao"></constructor-arg>
</bean>
```

##### 依赖注入的数据类型

普通数据类型（byte  short  int  long  float double  boolean  char ）、引用数据类型、集合数据类型

​																	普通数据类型，使用set注入

```java
public class UserDaoImpl implements UserDao {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void save() {
        System.out.println(name+"==========="+age);
        System.out.println("save running");
    }
}
```

```java
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl" >
    <property name="name" value="徐健"></property>
    <property name="age" value="27"></property>
</bean>
```

​																					集合的注入

```java
//User类
public class User {
    private String name;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
```

```java
//向UserDao中注入
public class UserDaoImpl implements UserDao {
    //注入集合
    private List<String> strList;
    //User类在domain中创建该类
    //注入map
    private Map<String, User> strMap;
    //注入hash
    private Properties properties;

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public void setStrMap(Map<String, User> strMap) {
        this.strMap = strMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void save() {
        System.out.println(strList);
        System.out.println(strMap);
        System.out.println(properties);
        System.out.println(name+"==========="+age);
        System.out.println("save running");
    }
}
```

```java
//向userDao注入普通数据类型
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl" >
    <property name="name" value="徐健"></property>
    <property name="age" value="27"></property>
    //向userDao注入List集合
    <property name="strList" >
        <list>
            <value>aaa</value>
            <value>bbb</value>
            <value>ccc</value>
        </list>
    </property>
    //向userDao注入map集合
    <property name="strMap">
        <map>
            <entry key="1" value-ref="user1"></entry>
            <entry key="2" value-ref="user2"></entry>
        </map>
    </property>
    //向userDao注入properties
    <property name="properties">
        <props>
            <prop key="p1" >ppp1</prop>
            <prop key="p2" >ppp2</prop>
            <prop key="p3" >ppp3</prop>
        </props>
    </property>
</bean>
 //向user1注入普通数据类型   
<bean id="user1" class="cn.xujian.domain.User">
    <property name="name" value="tom"></property>
    <property name="addr" value="北京"></property>
</bean>
 //向user2注入普通数据类型 
<bean id="user2" class="cn.xujian.domain.User">
    <property name="name" value="jeson"></property>
    <property name="addr" value="上海"></property>
</bean>
```

##### 引入其它配置文件（分模块开发）

将部分配置拆解到其它配置文件中，在spring主配置文件applicationContext.xml中通过import标签进行加载

```java
<import resource="applicationContext-user.xml"></import>
```

拆分策略：

- 如果一个开发人员负责一个模块，我们采用公用配置（包括数据源、事务等）+每个系统模块一个单独配置文件（包括Dao、Service、Web控制器）的形式
- 如果是按照分层进行的分工，我们采用公用配置（包括数据源、事务等）+DAO Bean配置+业务逻辑Bean配置+Web控制器配置的形式

#### Spring相关的API

ApplicationContext的继承体系

![Spring：IOC原理总结| AnthonyZero's Bolg](https://anthonyzero.github.io/images/ioc3.jpg)

##### 实现类

ClassPathXmlApplicationContext:类路径加载
 	  FileSystemXmlApplicationContext:文件系统路径加载
	   AnnotationConfigApplicationContext:用于基于注解的配置
	   WebApplicationContext:专门为web应用准备的，从相对于Web根目录的路径中装载配置文件完成初始化。

##### getBean的使用方法

使用id的方式

```java
UserService userService = (UserService) app.getBean("userService");
```

使用类型的方式

```java
UserService userService = app.getBean(UserService.class);
```

##### 知识要点

获得spring容器

```java
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
```

从容器获取对象

```java
//通过id获取
//        UserService userService = (UserService) app.getBean("userService");
//通过类型获取
        UserService userService = app.getBean(UserService.class);
```

#### 配置数据源

1. 数据源（连接池）的作用

数据源（连接池）是提高程序性能出现的；事先实例化数据源，初始化部分连接资源；使用连接资源时从数据源中获取；使用完毕后将连接资源归还给数据源。

常见的数据源：DBCP、C3P0、BoneCP、Druid	

2.数据源的手动创建

- 导入依赖包

  ```java
  <!--        数据库连接的包-->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.49</version>
          </dependency>
      <!--        数据库连接池的包-->
          <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>druid</artifactId>
              <version>1.1.23</version>
          </dependency>
  ```

- ```java
  @Test
  //测试手动创建C3P0数据源
  public void test1() throws Exception {
      ComboPooledDataSource dataSource = new ComboPooledDataSource();
      //设置驱动
      dataSource.setDriverClass("com.mysql.jdbc.Driver");
      //设置连接url
      dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/db1");
      //设置用户名
      dataSource.setUser("root");
      //设置密码
      dataSource.setPassword("xj");
      //获取资源连接
      Connection connection = dataSource.getConnection();
      //打印地址
      System.out.println(connection);
      //归还资源
      connection.close();
  }
  @Test
  //测试手动创建Druid数据源
  public void test2() throws Exception {
      DruidDataSource dataSource = new DruidDataSource();
      //设置驱动
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      //设置连接url
      dataSource.setUrl("jdbc:mysql://localhost:3306/db1");
      //设置用户名
      dataSource.setUsername("root");
      //设置密码
      dataSource.setPassword("xj");
      //获取资源连接
      Connection connection = dataSource.getConnection();
      //打印地址
      System.out.println(connection);
      //归还资源
      connection.close();
  }
  ```

3.抽取jdbc.properties配置文件

```java
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/db1
jdbc.username=root
jdbc.password=xj
```

```java
@Test
//测试手动创建C3P0数据源,(加载配置文件的方式)
public void test3() throws Exception {
//读取配置文件
    ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
    String driver = resourceBundle.getString("jdbc.driver");
    String url = resourceBundle.getString("jdbc.url");
    String username = resourceBundle.getString("jdbc.username");
    String password = resourceBundle.getString("jdbc.password");
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //设置驱动
    dataSource.setDriverClass(driver);
    //设置连接url
    dataSource.setJdbcUrl(url);
    //设置用户名
    dataSource.setUser(username);
    //设置密码
    dataSource.setPassword(password);
    //获取资源连接
    Connection connection = dataSource.getConnection();
    //打印地址
    System.out.println(connection);
    //归还资源
    connection.close();
}
```

##### Spring配置数据源

```java
<!--        配置C3P0的注入-->
    <bean id="dataSource-C3P0" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/db1"></property>
        <property name="user" value="root"></property>
        <property name="password" value="xj"></property>
    </bean>
<!--        配置druid的注入-->
    <bean id="dataSource-Druid" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/db1"></property>
        <property name="username" value="root"></property>
        <property name="password" value="xj"></property>
    </bean>
```

```java
@Test
//测试Spring容器产生数据源对象   C3P0连接池
public void test4() throws SQLException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    DataSource dataSource = (DataSource) applicationContext.getBean("dataSource-C3P0");
    Connection connection = dataSource.getConnection();
    System.out.println(connection);
    connection.close();
}
@Test
//测试Spring容器产生数据源对象  Druid连接池
public void test5() throws SQLException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    DataSource dataSource = (DataSource) applicationContext.getBean("dataSource-Druid");
    Connection connection = dataSource. getConnection();
    System.out.println(connection);
    connection.close();
}
```

###### 抽取JDBC配置文件

为了后期改配置文件更加的专注。

需要引入Context的命名空间和约束路径：

- 命名空件：

  ```java
  xmlns:context="http://www.springframework.org/schema/context"
  ```

- 约束路径

```java
xsi:schemaLocation="http://www.springframework.org/schema/beans 
    				http://www.springframework.org/schema/beans/spring-beans.xsd
                    http://www.springframework.org/schema/context 											   http://www.springframework.org/schema/context/spring-context.xsd"
```

- 加载配置文件的参数

```java
<context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
<!--        配置C3P0的注入,通过加载外部jdbc配置文件-->
<bean id="dataSource-C3P0" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="${jdbc.driver}"></property>
    <property name="jdbcUrl" value="${jdbc.url}"></property>
    <property name="user" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
</bean>
```

#### Spring注解开发

Spring原始注解主要替代<Bean>标签的配置

![2.Spring注解开发之Spring原始注解__午睡了的博客-CSDN博客](https://img-blog.csdnimg.cn/20200812184233337.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNTEyMzA4,size_16,color_FFFFFF,t_70)

![Spring第五篇【注解】 - 幻竹- 博客园](https://img2020.cnblogs.com/blog/1944255/202008/1944255-20200813151418893-469833627.png)

使用方法：  ==需要在applicationContext.xml中配置组件扫描==

配置的<Bean>

```java
<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl"></bean>
<bean id="userService" class="cn.xujian.service.impl.UserServiceImpl">
    <property name="userDao" ref="userDao"></property>
</bean>
```



使用注解后

```java
//<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl"></bean>
@Component("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("save running....");
    }
}
```

```java
//    <bean id="userService" class="cn.xujian.service.impl.UserServiceImpl"></bean>
@Component("userService")
public class UserServiceImpl implements UserService {
//    <property name="userDao" ref="userDao"></property>
//    向userService注入userDao类
    @Autowired //按照数据类型从Spring容器中匹配
    @Qualifier("userDao") //按照id值从容器中匹配
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
```

配置组件扫描

```java
<!--    配置组件扫描-->
    <context:component-scan base-package="cn.xujian"></context:component-scan>
```

新注解的使用

```java
//标志该类是Spring的核心配置类
@Configuration
//扫描目录
@ComponentScan("cn.xujian")
//加载配置文件
@PropertySource("classpath:jdbc.properties")
public class SpringConfigure {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    //将当前方法的返回值以指定名称存储到Spring容器中
    @Bean(name = "dataSource-Druid-an")
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
```

当使用了核心配置文件的java类，在生成容器时需要使用专门用于基于注解配置的类

```java
ApplicationContext app1 = new AnnotationConfigApplicationContext("配置文件名.class");
```

##### Spring整合Junit

1. 导入Spring集成Junit的坐标

   ```java
   <!--    spring集成junit需要导入的包-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-test</artifactId>
               <version>5.2.6.RELEASE</version>
           </dependency>
   ```

2. 使用@Runwith注解替换原来的运行期

3. 使用@ContextConfiguration指定配置文件或配置类

4. 使用@Autowrite注入需要测试的对象

5. 创建测试方法进行测试

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
//加载配置类的方式
//@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.save();
    }
}
```

### AOP 面向切面编程

Aspect Oriented Programming，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

作用：在程序运行期，在不修改源码情况下对方法进行功能增强；减少重复代码，提高开发效率，便于维护

##### 常用代理技术

- JDK代理：基于接口的动态代理技术
- cglib代理：基于父类的动态代理技术

常用术语：

- Target（目标对象）：代理的目标对象

- Proxy（代理）：一个类被AOP织入增强后，产生一个结果代理类

- Joinpoint（连接点）：指被拦截到的点，spring中这些点指的是方法，spring只支持方法类型的连接点（可以被增强的方法）

- pointCut（切入点）：我们要对哪些joinPoint进行拦截的定义（程序运行中被增强的方法）

- Advice（通知\增强）：指拦截到joinpoint之后要做的事情

- Aspect（切面）：切入点和通知的结合

- Weaving（织入）：指把增强应用到目标对象来创建新的代理对象的过程，spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入

  

==JDK动态代理技术:==

```java
public class ProxyTest {
    public static void main(String[] args) {
        //目标对象
        final Target target = new Target();
        //增强对象
        final Advice advice = new Advice();
        //返回值是动态生成的代理对象
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标对象类加载器
                target.getClass().getInterfaces(),//目标对象相同的接口字节码对象数组
                new InvocationHandler() {
                    //调用代理对象的任何方法，实际执行的都是invoke方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();
                        Object invoke = method.invoke(target,args);//执行目标方法
                        //后置增强
                        advice.after();
                        return invoke;
                    }
                }
        );
        //调用代理对象的方法
        proxy.sava();
    }
}
```

==cglib动态代理==

```java
public class ProxyTest {
    public static void main(final String[] args) {
        //目标对象
        final Target target = new Target();
        //增强对象
        final Advice advice = new Advice();
        //返回值是动态生成的代理对象  基于cglib
        //1、创建增强器
        Enhancer enhancer = new Enhancer();
        //2、设置父类（目标）
        enhancer.setSuperclass(Target.class);
        //3、设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //执行前置
                advice.before();
                //执行目标
                Object invoke = method.invoke(target, args);
                //执行后置
                advice.after();
                return invoke;
            }
        });
        //4、创建代理对象
        Target proxy = (Target) enhancer.create();

        //调用代理对象的方法
        proxy.sava();

    }
}
```

#### Aop开发入门，基于XML

##### 				明确事项

- 编写核心业务代码（目标类的目标方法）
- 编写切面类，切面类中有通知（增强功能方法）
- 在配置文件中，配置织入关系，哪些通知与哪些连接点进行结合

技术实现的内容：   Spring框架皆空切入点方法的执行，一旦切入点被方法运行，使用代理机制，根据通知类别，在代理对象的对应位置将对应的功能织入。

AOP底层使用的代理方式：    在Spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式

##### 快速入门

1. 导入AOP相关坐标

   ```java
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.9.2</version>
   </dependency>
   ```

2. 创建目标接口和目标类（内部有切点）

   ```java
   //目标类接口
   public interface TargetInterface {
       public void save();
   }
   ```

   ```java
   //目标类
   public class Target implements TargetInterface {
       public void save() {
           System.out.println("save running......");
       }
   }
   ```

3. 创建切面类（内部有增强方法）

   ```java
   //切面类，就是增强方法的类
   public class MyAspect {
       public void before(){
           System.out.println("前置增强方法");
       }
       public void after(){
           System.out.println("后置增强");
       }
   }
   ```

4. 将目标类和切面类的对象创建权交给spring

   ```java
   <!--   配置目标对象-->
       <bean id="target" class="cn.xujian.aop.Target"></bean>
   <!--   配置切面对象-->
       <bean id="myAspect" class="cn.xujian.aop.MyAspect"></bean>
   ```

5. 在applicationContext.xml中配置织入关系

   ```java
   <!--   配置织入，告诉spring框架，哪些方法需要进行哪些增强-->
       <aop:config>
   <!--   申明切面-->
           <aop:aspect ref="myAspect">
   <!--   切面：切点+通知-->
               <aop:before method="before"  pointcut="execution(public void cn.xujian.aop.Target.save())"></aop:before>
               <aop:after method="after" pointcut="execution(public void cn.xujian.aop.Target.save())"></aop:after>
           </aop:aspect>
       </aop:config>
   ```

6. 测试代码

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration("classpath:applicationContext.xml")
   public class AopTest {
       @Autowired
       private TargetInterface target;
       @Test
       public void test1(){
           target.save();
       }
   }
   ```



###### 切点表达式的写法

execution([修饰符] 返回值类型 包名.类名.方法名(参数))                   execution(public void cn.xujian.aop.Target.save())

- 访问修饰符可以省略
- 返回值类型、包名、类名、方法名可以使用星号*代表任意
- 包名与类名之间一个点代表当前包下的类，两个点表示当前包及其子包下的类
- 参数列表可以使用两个点表示任意个数，任意类型的参数列表

![Spring-AOP | 码农家园](https://i2.wp.com/img-blog.csdnimg.cn/20200411165043628.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjQ1NDYxNw==,size_16,color_FFFFFF,t_70)

环绕通知

```java
//ProceedingJoinPoint   正在执行的切入点
public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("环绕前增强");
    Object proceed = proceedingJoinPoint.proceed();//切入点方法
    System.out.println("环绕后增强");
    return proceed;
}
```

```java
<aop:around method="around" pointcut="execution(public void cn.xujian.aop.Target.save())"></aop:around>
```

###### 切点表达式的抽取

```java
<!--   抽取切点表达式-->
            <aop:pointcut id="myPointcut" expression="execution(public void cn.xujian.aop.Target.save())"/>
```

```java
<aop:after method="after" pointcut-ref="myPointcut"></aop:after>
```

#### 基于注解开发

##### 快速入门

1. 创建目标接口和目标类（内部有切点）

   ```java
   //目标类
   @Component("target")//将目标类注入spring
   public class Target implements TargetInterface {
       public void save() {
           System.out.println("save running......");
       }
   }
   ```

2. 创建切面类（内部有增强方法）

   ```java
   //切面类，就是增强方法的类
   @Component("myAspect")  //将切面类注入spring
   @Aspect   //标注当前类是一个切面类
   public class MyAspect {
       @Before("execution(void cn.xujian.anno.Target.*(..))")
       public void before(){
           System.out.println("前置增强方法");
       }
   }
   ```

3. 将目标类和切面类的对象创建权交给Spring

4. 在切面类中使用注解配置织入关系

5. 在配置文件中开启组件扫描和AOP的自动代理

   ```java
   <!--   组件扫描-->
       <context:component-scan base-package="cn.xujian.anno"></context:component-scan>
   <!--   配置AOP自动代理-->
       <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
   ```

6. 测试

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration("classpath:applicationContext-anno.xml")
   public class AnnoTest {
       @Autowired
       private TargetInterface target;
       @Test
       public void test1(){
           target.save();
       }
   }
   ```



切点表达式的抽取

```java
//定义一个切点表达式   切点表达式抽取
@Pointcut("execution(void cn.xujian.anno.Target.*(..))")
public void pointcut(){
}
@After("pointcut()")
public void after(){
    System.out.println("后置增强");
}
```





### JdbcTemplate

###### 快速开发

1. 导入Jar包

   ```java
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.2.6.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.2.6.RELEASE</version>
   </dependency>
   ```

2. 创建数据库表和实体

3. 创建JdbcTemplate对象

   ```java
   <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
   <bean id="dataSource-Druid" class="com.alibaba.druid.pool.DruidDataSource">
       <property name="driverClassName" value="${jdbc.driver}"></property>
       <property name="url" value="${jdbc.url}"></property>
       <property name="username" value="${jdbc.username}"></property>
       <property name="password" value="${jdbc.password}"></property>
   </bean>
   <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
       <property name="dataSource" ref="dataSource-Druid"></property>
   </bean>
   ```

4. 执行数据库操作

   ```java
   //测试Spring产生jdbc模板对象
   @Test
   public void test2(){
       ApplicationContext app =new ClassPathXmlApplicationContext("applicationContext.xml");
       JdbcTemplate jdbcTemplate = (JdbcTemplate)app.getBean("jdbcTemplate");
       jdbcTemplate.update("insert into account (name,balance) value (?,?)","钢铁侠",100001);
   }
   ```

###### 增数据

```java
@Test
public void testInsert(){
    jdbcTemplate.update("insert into account (name ,balance) value (?,?)","super超人",120);
}
```

###### 删数据

```java
@Test
public void testDelete(){
    jdbcTemplate.update("delete from account where name = ?","tom");
}
```

###### 改数据

```java
@Test
public void testUpdate(){
    jdbcTemplate.update("update account set balance=? where name = ?",100,"超人");
}
```

###### 查数据

```java
//查询所有对象
@Test
public void testQueryAll(){
    List<Acount> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Acount>(Acount.class));
    System.out.println(accountList.get(1));
}
//查询一个对象
@Test
public void testQuertOne(){
    Acount acountOne = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<Acount>(Acount.class), "超人");
    System.out.println(acountOne);
}
//聚合查询
@Test
public void testQueryCount(){
    Long count = jdbcTemplate.queryForObject("select count(*) from account", long.class);
    System.out.println(count);
}
```



### 事务控制

#### 编程式事务控制

PlatformTransactionManager接口是Spring的事务管理器，常用操作事务方法

![09 spring transaction development](https://www.fatalerrors.org/images/blog/8ac95f84bdbd28952d22eec4c4f62e6e.jpg)

TransactionDefinition是事务的定义信息对象，里面有如下方法：

![09 spring transaction development](https://www.fatalerrors.org/images/blog/476442e910b444ebedc4ce1d60c05288.jpg)

- 事务隔离级别（解决脏读、不可重复读、虚读）

DEFAULT       READ_UNCOMMITTED         READ_COMMITTED       REPEATABLE_READ     SERIALIZABLE

- 事务传播行为

![Spring事务的7种传播行为一文搞定_熊猫学院-CSDN博客](https://img-blog.csdnimg.cn/20190924001440524.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0JydWNlTGl1X2NvZGU=,size_16,color_FFFFFF,t_70)



TransactionStatus接口

![Spring第九篇【编程式事务】 - How2学](https://img2020.cnblogs.com/blog/1944255/202008/1944255-20200818200034567-1063461061.png)



#### 基于XML的声明式事务控制

Spring声明式事务控制底层是AOP

```java
<!--    配置平台事务管理器-->
    <bean id="transactionManage" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource-druid"></property>
    </bean>
<!--    通知，事务的增强-->
    <tx:advice id="txAdvice" transaction-manager="transactionManage">
<!--    设置事务的属性信息    -->
        <tx:attributes>
<!--    对不同的方法配置不同的事务参数-->
            <!--  name ：切点方法的名称     isolation ：事务的隔离级别 propogation：事务的传播行为 
            	timeout ：超时时间  read-only ：是否只读-->
            <tx:method name="transfer" isolation="DEFAULT" propagation="REQUIRED" timeout="-1" read-only="false"/>
        </tx:attributes>
    </tx:advice>
<!--    配置事务aop的织入-->
    <aop:config>
        <aop:advisor advice-ref="txAdvice" pointcut="execution(* cn.xujian.service.impl.*.*(..))"></aop:advisor>
    </aop:config>
```

#### 基于注解的声明式事务控制

```java

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
//    给方法加事务
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void transfer(String inMan, String outMan, double money) {
        accountDao.in(inMan,money);
//        int i = 1/0;
        accountDao.out(outMan,money);


    }
}
```

```java
<!--    读取配置文件-->
    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
<!--    组件扫描-->
    <context:component-scan base-package="cn.xujian"></context:component-scan>
<!--    注入数据库连接池-->
    <bean id="dataSource-druid" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"></property>
        <property name="url" value="${jdbc.url}"></property>
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
<!--    注入jdbc模板类-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource-druid"></property>
    </bean>

<!--    配置平台事务管理器-->
    <bean id="transactionManage" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource-druid"></property>
    </bean>
<!--    事务的注解驱动-->
    <tx:annotation-driven transaction-manager="transactionManage"></tx:annotation-driven>

</beans>
```

- ==平台事务管理器配置==

- ==事务的注解驱动配置==

- ==事务的通知配置==





### Spring集成Web环境

ApplicationContext应用上下文获取方式：web项目中，使用ServletContextListener监听Web应用的启动，启动时加载spring配置文件，创建ApplicationContext对象，将其存储到最大的域ServletContext，这样便可从任意位置域中获得应用上下文ApplicationContext对象。

Spring提供的获取应用上下文的工具：  spring提供了ContextLoaderListener就是对上述功能的封装，提供了一个客户端工具WebApplicationContextUtil供使用者获得上下文对象。

1. 在web.xml中配置ContextLoaderListener监听器（导入spring-web包）
2. 使用WebApplicationContextUtil获取上下文对象

#### 快速入门

1. 导入SpringMVC包（此处要注意导入的所有spring的包版本一致，否则会导致注入bean错误）

   ```java
   <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-webmvc</artifactId>
     <version>5.2.6.RELEASE</version>
   </dependency>
   ```

2. 配置SpringMVC核心控制器DispathcerServlet（在web.xml中配置）

   ```java
   <!--  配置SpringMVC的前端控制器-->
     <servlet>
       <servlet-name>DispatcherServlet</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   <!--    servlet初始化的时候加载配置文件-->
       <init-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>classpath:spring-mvc.xml</param-value>
       </init-param>
       <load-on-startup>1</load-on-startup>
     </servlet>
     <servlet-mapping>
       <servlet-name>DispatcherServlet</servlet-name>
       <url-pattern>/</url-pattern>
     </servlet-mapping>
   ```

3. 创建Controller类和示图页面

   ```java
   @Controller
   public class UserController {
       //业务方法映射地址
       @RequestMapping("/quick")
       public String saves(){
           System.out.println("Controller save running");
           //此处return的为要跳转的示图
           return "success.jsp";
       }
   }
   ```

4. 使用注解配置Controller类中业务方法的映射地址

5. 配置SpringMVC核心文件spring-mvc.xml（spring的配置）

6. ```java
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                               http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
   <!--Controller的组件扫描-->
       <context:component-scan base-package="cn.xujian.controller"></context:component-scan>
   </beans>
   ```

7. 客户端发起请求测试

![SpringMVC_工作流程| Catnip](https://c-catnip.github.io/2019/08/19/%E5%90%8E%E7%AB%AF/SpringMVC/SpringMVC_%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B/SpringMVC%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B%E5%9B%BE.png)



注解：@requestMapping可以用在==类上==（请求的一级目录）和==方法上==（请求的二级目录），

​			属性：value   请求地址               method   请求方式        params    用于指定限制请求参数的条件

#### SpringMVC的数据响应

##### 页面跳转

###### 直接返回字符串

```java
@Controller
public class UserController {
    @RequestMapping(value = "/quick",method = RequestMethod.GET)
    public String saves(){
        System.out.println("Controller save running");
        //此处return的为要跳转的示图
        return "success.jsp";
    }
```

###### 通过ModelAndView对象返回：

- 方法1

```java
//使用ModelAndView最基本的操作
@RequestMapping(value = "/quick2",method = RequestMethod.GET)
public ModelAndView saves2(){
    /*
    * Model :模型用于封装数据
    * View  :视图用于展示数据
    *  */
    ModelAndView modelAndView = new ModelAndView();
    //设置模型数据
    modelAndView.addObject("username","xujian");
    //设置视图名称
    modelAndView.setViewName("success.jsp");
   return modelAndView;
}
```

- 方法2

```java
//由spring容器提供modelAndView对象
@RequestMapping(value = "/quick3")
public ModelAndView saves3(ModelAndView modelAndView){
    //设置模型数据
    modelAndView.addObject("username","quick3");
    //设置视图名称
    modelAndView.setViewName("success.jsp");
    return modelAndView;
}
```

- 方法3

```java
//model和view分开
@RequestMapping(value = "/quick4")
public String saves4(Model model){
    //设置模型数据
    model.addAttribute("username","quick4");
    //此处return的为要跳转的示图
    return "success.jsp";
}
```

##### 回写数据 

###### 直接返回字符串

- 方法1

```java
//最基本的利用httpServletResponse对象回写
@RequestMapping("/quick5")
public void saves5(HttpServletResponse httpServletResponse) throws IOException {
    httpServletResponse.getWriter().write("hello xujian");
}
```

- 方法2（重点）

```java
//将需要回写的字符串直接返回，通过@ResponseBody注解告知SpringMVC框架，方法返回的字符串不是跳转，是直接在http响应体中返回。
@RequestMapping("/quick6")
@ResponseBody
public String saves6(){
    return "hello quick6";
}
```

- 回写Json格式字符串

```java
//将对象转换为json字符串然后响应
@RequestMapping("/quick7")
@ResponseBody
public String saves7() throws JsonProcessingException {
    User user = new User("lisi",18);  //如果有中文需要处理编码格式
    //使用json的转换工具将对象转换成json格式的字符串再返回，（导包jackson-annotations、jackson-databind、jackson-core）
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(user);
    return json;
}
```

###### 返回对象或集合

```java
<!--    配置处理器映射器-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters">
            <list>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
            </list>
        </property>
    </bean>
```

```java
<!--    MVC的注解驱动-->
    <mvc:annotation-driven></mvc:annotation-driven>
<!--    如果spring找不到资源，交给原始的容器（tomcat）去找资源-->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
```

可用==mvc的注解驱动==来替代==配置处理映射器==，达到相同的json转换效果

```java
//期望SpringMVC自动将User转换成json格式的字符串
@RequestMapping("/quick8")
@ResponseBody
public User saves8()  {
    User user = new User("zhangshan",18);
    return user;
}
```





#### springMVC获得请求数据

服务器端获得请求的数据，有时需要进行数据的封装，SpringMVC可以接收如下类型的参数：

- 基本类型参数
- POJO类型参数
- 数组类型参数
- 集合类型参数

###### 

##### 获取基本类型数据

Controller中的业务方法的参数名称要与请求参数的name一致，参数值会自动映射匹配。

在客户端访问 ：http://localhost:8989/quick9?username=lisi

```java
//获得基本类型参数
@RequestMapping("/quick9")
@ResponseBody
public void saves9(String username)  {
    System.out.println(username);
}
```

##### 获得POJO类型数据

Controller中的业务方法的POJO参数的属性名要与请求参数的name一致，参数值会自动映射匹配。

在客户端访问：http://localhost:8989/quick10?username=xujian&age=26

```java
//获取POJO类型参数
@RequestMapping("/quick10")
@ResponseBody
public void saves10(User user)  {
    System.out.println(user);
}
```

##### 获得数组类型的参数

Controller中的业务方法的数组名称与请求参数的name一致，参数值会自动映射匹配。

在客户端访问：http://localhost:8989/quick11?strs=aaa&strs=bbb&strs=ccc

```java
//获取数组参数
@RequestMapping("/quick11")
@ResponseBody
public void saves11(String[] strs)  {
    System.out.println(Arrays.asList(strs));
}
```

##### 获取集合类型参数

获得集合参数时，要将集合参数包装到POJO对象当中

- 创建POJO对象

```java
public class VO {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
```

- 创建post请求

```java
    <form action="${pageContext.request.contextPath}/quick12" method="post">
<%--        表明是第几个user对象的username--%>
        <input type="text" name="userList[0].username">
        <input type="text" name="userList[0].age">
        <input type="text" name="userList[1].username">
        <input type="text" name="userList[1].age">
        <input type="submit" value="提交">
    </form>
```

- 获取集合类型参数

```java
//获取集合类型参数
@RequestMapping("/quick12")
@ResponseBody
public void saves12(VO vo)  {
    System.out.println(vo);
}
```



==特殊业务场景==

当使用ajax提交时，可以指定contentType为json形式，那么在方法参数位置使用@RequestBody注解可以直接接收集合数据而无需使用POJO进行包装。（此处遇到找不到js资源的问题，maven重新编驿解决）

- js的开发资源开放访问（二选一）

```java
<!--&lt;!&ndash;开发资源开放访问&ndash;&gt;-->
<!--    <mvc:resources mapping="/js/**" location="/js/"></mvc:resources>-->
    
<!--    如果spring找不到资源，交给原始的容器（tomcat）去找资源-->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
```

- 异步请求

```java
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    var userList = new Array();
    userList.push({username:"zhangshan",age:18});
    userList.push({username:"lisi",age:55});
    $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}/quick13",
        data:JSON.stringify(userList),
        contentType:"application/json;charset=utf-8"
    })
</script>
```

- controller（方法参数位置使用@RequestBody注解）

```java
//获取集合类型参数(特殊情况)
@RequestMapping("/quick13")
@ResponseBody
public void saves13(@RequestBody List<User> userList)  {
    System.out.println(userList);
}
```

##### 获得请求头

使用注解@RequestHeader

```java
//获得请求头
@RequestMapping("/quick17")
@ResponseBody
//@RequestHeader注解的value对应请求头里的属性名；使用@CookieValue注解可以方便获取cookies
public void saves17(@RequestHeader(value = "User-Agent") String user_agent){
    System.out.println(user_agent);
}
```

##### 文件上传



#### 请求数据乱码的问题

在web.xml中配置一个全局过滤器

```java
<!--  配置一个全局过滤器-->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

#### 常用一些注解

参数绑定注解@requestParam：

客户端访问：http://localhost:8989/quick14?name=李四

```java
//参数绑定注解@RequestParam
@RequestMapping("/quick14")
@ResponseBody
public void saves14(@RequestParam("name") String username)  {
    System.out.println(username);
}
```

注解的参数         value：与请求参数 名称

​							required：指定的请求参数是否必须包括，默认为true

​							defaultValue ：当没有指定请求参数时，使用指定的默认值赋值

#### 获得Restful风格的参数

Restful是一种软件架构风格、设计风格，基于此风格设计的软件可以更简洁、更有层次、更易于实现缓存机制。

Restful风格的请求是使用“url+请求方式”表示一次请求目的，http协议里四个表示操作方式的动词：

- GET ：获取资源                  eg ：/user/1  GET     得到id=1的user
- POST：新建资源                 eg：/user     POST     新增user        
- PUT：更新资源                    eg：/user/1  PUT     更新id=1的user
- DELETE：删除资源             eg：/user/1   DELETE    删除id=1的user

==地址/user/1可以写成/user/{id}，占位符在业务方法中可以使用@PathVariable注解进行占位符的匹配获取工作==

```java
//获得Restful风格的参数
@RequestMapping(value = "/quick15/{name}",method = RequestMethod.GET)
@ResponseBody
public void saves15(@PathVariable(value = "name", required = true) String username){
    System.out.println(username);
}
```

#### 自定义类型转换器

例如：日期类型的数据自定义转换

1. 定义转换器类型实现Converter接口。

   ```java
   public class DateConverter implements Converter<String, Date> {
       @Override
       public Date convert(String dateStr) {
           //将日期字符串转换成日期对象
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           Date date = null;
           try {
               date=format.parse(dateStr);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           return date;
       }
   }
   ```

2. 在配置文件中声明转换器。

   ```java
   <!--    声明转换器，自定义类型变换使用-->
       <bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
           <property name="converters">
               <list>
                   <bean class="cn.xujian.converter.DateConverter"></bean>
               </list>
                       
           </property>
       </bean>
   ```

3. 在<annotation-driven>中引用转换器

   ```java
   <!--    MVC的注解驱动-->
       <mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>
   ```

#### 文件上传

文件上传客户端三要素：

- 表单项  type=“file”

- 表单提交方式为Post

- 表单的enctype属性是多部分表单形式，即enctype="multipart/form-data"。

  ```jsp
  <body>
      <form action="${pageContext.request.contextPath}/quick18" method="post" enctype="multipart/form-data">
          名称<input type="text" name="username"><br/>
          文件<input type="file" name="uploadFile"><br/>
          <input type="submit" value="提交">
      </form>
  </body>
  ```

==单文件上传步骤：==

- 导入fileupload和io包

  ```java
  <!--    //文件上传相关包-->
      <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.2.1</version>
      </dependency>
  <!--    //文件上传相关包-->
      <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.5</version>
      </dependency>
  ```

- 配置文件上传解析器

  ```java
      <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
  <!--        上传文件总大小-->
          <property name="maxUploadSize" value="5242800"></property>
  <!--        上传文件单个大小-->
          <property name="maxUploadSizePerFile" value="5242800"></property>
  <!--        上传文件的编码类型-->
          <property name="defaultEncoding" value="UTF-8"></property>
      </bean>
  ```

- 编写文件上传代码

  ```java
  //文件上传
  @RequestMapping("/quick18")
  @ResponseBody
  //此处的MultipartFile对象的名字要和表单上传文件的value值一样
  public void fileUpload(String username, MultipartFile uploadFile) throws IOException {
      System.out.println(username);
      //获取文件名称
      String originalFilename = uploadFile.getOriginalFilename();
      System.out.println(originalFilename);
      //保存文件
      uploadFile.transferTo(new File("C:\\Users\\xj\\Desktop\\"+originalFilename));
  }
  ```

==多文件上传==

```jsp
<form action="${pageContext.request.contextPath}/quick18" method="post" enctype="multipart/form-data">
    名称<input type="text" name="username"><br/>
    文件<input type="file" name="uploadFile"><br/>
    文件<input type="file" name="uploadFile"><br/>
    文件<input type="file" name="uploadFile"><br/>
    文件<input type="file" name="uploadFile"><br/>
    <input type="submit" value="提交">
</form>
```

```java
//多文件上传
@RequestMapping("/quick19")
@ResponseBody
//此处的MultipartFile对象的名字要和表单上传文件的value值一样
public void fileUploads(String username, MultipartFile[] uploadFile) throws IOException {
    System.out.println(username);
    //获取文件名称
    for (MultipartFile multipartFile : uploadFile){
        String originalFilename = multipartFile.getOriginalFilename();
        if (!originalFilename.equals("")){
            System.out.println(originalFilename);
            //保存文件
            multipartFile.transferTo(new File("C:\\Users\\xj\\Desktop\\"+originalFilename));
        }
    }
}
```



### SpringMVC拦截器

类似与Servlet开发中的过滤器Filter，用于对处理器进行预处理和后处理。

![image-20210415232621504](C:\Users\xj\AppData\Roaming\Typora\typora-user-images\image-20210415232621504.png)

#### 快速入门

- 创建拦截器类实现HandlerInterceptor接口

```java
public class MyInterceptor1 implements HandlerInterceptor {
//    在目标方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return false;
    }
//  在目标方法执行之后  视图返回之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }
//  在整个流程执行完毕后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
```

- 配置拦截器

```java
<!--    配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
<!--            对哪些资源执行拦截操作-->
            <mvc:mapping path="/**"/>
            <bean class="cn.xujian.interceptor.MyInterceptor1"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

- 测试拦截的效果

```java
package cn.xujian.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {
//    在目标方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
//        获取键param的值，如果值为yes则正确访问目标资源，其它则重定向到error.jsp
        String param = request.getParameter("param");
        if( "yes".equals(param)){
            return true;
        }else {
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return false;
        }
    }
//  在目标方法执行之后  视图返回之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        对视图对象进行更改
        modelAndView.addObject("name","已经把本来的xujian改成了徐健");
        System.out.println("postHandle");
    }
//  在整个流程执行完毕后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
```

#### 案例     用户登陆权限控制

需求：用户没有登陆情况下，不能对后台菜单进行访问，点击菜单跳转到登陆页面，只有登陆成功后才能进行后台功能的操作。

- dao

```java
@Repository("managerDao")
public class ManagerImpl implements ManagerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Manager findByUsernameAndPassword(String username, String password) {
        Manager manager;

        try {
            manager = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
        } catch (DataAccessException e) {
            manager=null;
        }
        return manager;
    }
}
```

- service

```java
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public Manager login(String username, String password) {
        Manager manager = managerDao.findByUsernameAndPassword(username, password);
        return manager;
    }
}
```

- interceptor

```java
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        判断用户是否登陆   （判断session中有没有manager）
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
//            没有登陆
        if (manager==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        return true;
    }
}
```

- controller

```java
@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @RequestMapping("/login")
    public String login (String username, String password, HttpSession session){
        Manager manager = managerService.login(username, password);
//        密码和账户对应
        if (manager!=null){
            session.setAttribute("manager",manager);

            return "redirect:/index01.jsp";
        }
        return "/login.jsp";
    }
    @RequestMapping("/quick1")
    public String quick1(){
        return "/quick1.jsp";
    }
}
```

- .xml配置文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <import resource="spring-mvc.xml"></import>
<!--    读取配置文件-->
    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
<!--    组件扫描-->
    <context:component-scan base-package="cn.xujian"></context:component-scan>
<!--    注入数据库连接池-->
    <bean id="dataSource-druid" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"></property>
        <property name="url" value="${jdbc.url}"></property>
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>
<!--    注入jdbc模板类-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource-druid"></property>
    </bean>
<!--    配置平台事务管理器-->
    <bean id="transactionManage" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource-druid"></property>
    </bean>
<!--    事务的注解驱动-->
    <tx:annotation-driven transaction-manager="transactionManage"></tx:annotation-driven>

</beans>
```

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                            http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
<!--Controller的组件扫描-->
    <context:component-scan base-package="cn.xujian.controller"></context:component-scan>

<!--    MVC的注解驱动-->
    <mvc:annotation-driven ></mvc:annotation-driven>

<!--    如果spring找不到资源，交给原始的容器（tomcat）去找资源-->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>


<!--    配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
<!--            对哪些资源执行拦截操作-->
            <mvc:mapping path="/**"/>
<!--            对哪些资源放开拦截-->
            <mvc:exclude-mapping path="/login"/>
            <bean class="cn.xujian.interceptor.LoginInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
</beans>
```

#### SpringMVC的异常处理机制

##### 异常处理思路

系统中异常包括两类：预期异常和运行时异常RuntimeException，前者通过捕获异常从而获取异常信息，后者主要通过规范代码开发、测试等手段减少运行时异常的发生。

系统的Dao、Service、Controller出现异常都通过throws Exception向上抛出，最后由SpringMVC前端控制器交由异常处理器进行异常处理，如下图：

![image-20210417185932245](C:\Users\xj\AppData\Roaming\Typora\typora-user-images\image-20210417185932245.png) 



##### 异常处理两种方式

1.使用SpringMVC提供的简单异常处理器SimpleMappingExceptionResolver

- 人为制造异常

```java
@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public void show1() {
        System.out.println("抛出类型转换异常");
        Object str = "zhangshan";
        Integer num = (Integer)str;
    }

    @Override
    public void show2() {
        System.out.println("抛出除零异常");
        int i = 1/0;
    }

    @Override
    public void show3() throws FileNotFoundException {
        System.out.println("文件找不到异常");
        InputStream in = new FileInputStream("c:/aaa.txt");
    }

    @Override
    public void show4() {
        System.out.println("空指针异常");
        String str = null;
        str.length();
    }

    @Override
    public void show5() throws MyException {
        System.out.println("自定义异常");
        throw new MyException();
    }
}
```

- 在Spring_MVC.xml文件中配置简单异常处理

```java
<!--    配置简单异常处理器-->
    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="defaultErrorView" value="error2.jsp"></property>
        <property name="exceptionMappings">
            <map>
                <entry key="cn.xujian.exception.MyException" value="error1.jsp"></entry>
                <entry key="java.lang.ClassCastException" value="error.jsp"> </entry>
            </map>
        </property>
    </bean>
```

- 在controller中测试异常跳转

```java
@Controller
public class DemoController {
    @Autowired
    private DemoService demoService;
    @RequestMapping("/show")
    public String show() throws FileNotFoundException, MyException {
        System.out.println("show Running.....");
//        demoService.show1();
        demoService.show2();
//        demoService.show3();
//        demoService.show4();
//        demoService.show5();
        return "/index.jsp";
    }
}
```



2.实现Spring的异常处理接口HandlerExceptionResolver

- 创建异常处理器类实现HandlerExceptionResolver

```java
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
//    参数Exception：异常对象
//    返回值ModelAndView：跳转到错误视图的信息
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof MyException){
            modelAndView.addObject("info","自定义异常");
        }else if (e instanceof ClassCastException){
            modelAndView.addObject("info","类转换异常");
        }else if (e instanceof NullPointerException){
            modelAndView.addObject("info","空指针异常");
        }
        modelAndView.setViewName("/Myerror.jsp");
        return modelAndView;
    }
}
```

- 编写异常页面

- 在Spring_MVC中配置异常处理器


```java
<!--    配置自定义异常处理-->
    <bean class="cn.xujian.resolver.MyExceptionResolver"></bean>
```

- 在controller中测试异常跳转

### Spring项目练习

#### Spring练习环境搭建

1. 创建工程（Project&Module）
2. 导入静态页面
3. 导入需要的包
4. 创建包结构（controller、service、dao、domain、utils）
5. 导入数据库脚本
6. 创建POJO类
7. 创建配置文件（applicationContext.xml、spring-mvc.xml、jdbc.properties、log4j.properties）

#### 角色列表的展示和添加操作

1. 点击角色管理菜单发送请求到服务器端（修改角色管理菜单的url）
2. 创建RoleController和showList（）方法
3. 创建RoleService和showList（）方法
4. 创建RoleDao和findAll（）方法
5. 使用jdbcTemplate完成查询操作
6. 将查询数据存储到Model中
7. 转发到role-list.jsp页面进行展示

- 注意数据提交到数据库乱码问题：在web.xml中配置过滤器



#### 用户列表的展示和添加操作

```java
    @Override
    public List<User> showUser() {
        List<User> userList = userDao.findAllUser();
        for (User user : userList){
//            获取user的id
            Long id = user.getId();
            List<Role> roles = roleDao.findRoleById(id);
            user.setRoles(roles);
        }
        return userList;
    }
```

```java
@Override
public List<User> findAllUser() {
    List<User> userList = jdbcTemplate.query("select * from sys_user",new BeanPropertyRowMapper<User>(User.class));
    return userList;
}
```

```java
@Override
public List<Role> findRoleById(Long id) {
    List<Role> roles = jdbcTemplate.query("select * from sys_user_role ur , sys_role r where ur.roleId = r.id and ur.userId = ?", new BeanPropertyRowMapper<Role>(Role.class), id);
    return roles;
}
```

#### 删除用户操作

- jsp

```java
<script>
   function delUser(userId) {
      if (confirm("您确认要删除吗")){
         location.href="${pageContext.request.contextPath}/user/del/"+userId;
      }
   }
</script>
    
    
```

```java
<td class="text-center">
   <a href="javascript:void(0);" onclick="delUser('${user.id}')" class="btn bg-olive btn-xs">删除</a>
</td>
```

- controller

```java
//    删除单条user数据
    @RequestMapping("/del/{userId}")
    public String delUser(@PathVariable("userId") long userId){
        userService.delUser(userId);
        return "redirect:/user/list";
    }
```

userServiceImpl

```java
    @Override
    public void delUser(long userId) {
//        删除关系表相关数据
        userDao.delUserRoleRel(userId);
//        删除user表相关数据
        userDao.delUserById(userId);
    }
```

userDaoImpl

```java
@Override
public void delUserRoleRel(long userId) {
    jdbcTemplate.update("delete from sys_user_role where userId = ?",userId);
}

@Override
public void delUserById(long userId) {
    jdbcTemplate.update("delete from sys_user where id = ?",userId);
}
```

### Mybatis学习

##### 快速入门

1. 添加MyBatis的jar包

   ```java
   <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <version>5.1.49</version>
   </dependency>
   
   <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
   <dependency>
     <groupId>org.mybatis</groupId>
     <artifactId>mybatis</artifactId>
     <version>3.4.6</version>
   </dependency>
   ```

2. 创建user数据表

3. 编写User实体类

4. 编写映射文件UserMapper.xml

   ```java
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <!-- 将查询到的数据封装到user对象-->
   <mapper namespace="userMapper">
       <select id="findAll" resultType="cn.xujian.domain.User">
           select * from user
       </select>
   </mapper>
   ```

5. 编写核心文件SqlMapConfig.xml

   ```java
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
   
   <!--    配置数据源的环境-->
       <environments default="developement">
           <environment id="developement">
               <transactionManager type="JDBC"></transactionManager>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/test"/>
                   <property name="username" value="root"/>
                   <property name="password" value="xj"/>
               </dataSource>
           </environment>
       </environments>
   
   <!--    加载映射文件-->
       <mappers>
           <mapper resource="cn/xujian/mapper/UserMapper.xml"></mapper>
       </mappers>
   </configuration>
   ```

6. 编写测试类

   ```java
       @Test
       public void test01() throws IOException {
   //        获得核心配置文件
           InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
   //        获取session工厂对象
           SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
   //        获取session会话对象
           SqlSession sqlSession = sqlSessionFactory.openSession();
   //        执行操作 参数：命名空件+id
           List<User> userList = sqlSession.selectList("userMapper.findAll");
   //        打印数据
           System.out.println(userList);
   //        释放资源
           sqlSession.close();
       }
   ```



##### 映射文件概述

![image-20210419201355005](C:\Users\xj\AppData\Roaming\Typora\typora-user-images\image-20210419201355005.png)

##### 增删改查操作

插入操作：

```java
<!--    插入操作   此处为user对象内部的属性值-->
    <insert id="save" parameterType="cn.xujian.domain.User">
        insert into user values (#{id},#{username},#{password})
    </insert>
```

```java
//    增加数据
    @Test
    public void test02() throws IOException {
//        模拟user对象
        User user = new User();
        user.setUsername("tom");
        user.setPassword("hahaha");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.insert("userMapper.save",user);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }
```

修改数据：

```java
<!--    修改数据操作-->
    <update id="update" parameterType="cn.xujian.domain.User">
        update user set username = #{username} ,password = #{password} where id = #{id}
    </update>
```

```java
    //    修改数据
    @Test
    public void test03() throws IOException {
//        模拟user对象
        User user = new User();
        user.setId(6);
        user.setUsername("tomDad");
        user.setPassword("123");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.update("userMapper.update",user);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }
```

删除操作：

```java
<!--    删除操作,根据id删除-->
    <delete id="delete" parameterType="java.lang.Integer">
        delete from user where id = #{id}
    </delete>
```

```java
    //    删除数据操作
    @Test
    public void test04() throws IOException {

//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.delete("userMapper.delete",6);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }
```

##### 核心配置文件概述

![image-20210419210304836](C:\Users\xj\AppData\Roaming\Typora\typora-user-images\image-20210419210304836.png)

![image-20210419210412839](C:\Users\xj\AppData\Roaming\Typora\typora-user-images\image-20210419210412839.png)

properties可以用于引入配置文件

```java
<!--    加载外部properties文件-->
    <properties resource="jdbc.properties"></properties>
```

##### 动态SQL

使用if标签来判断传入的对象某个值是否传入，有传入值才加入判断。

```java
<select id="findByCondition" parameterType="cn.xujian.domain.User" resultType="cn.xujian.domain.User">
    select * from user
    <where>
        <if test="id!=0">
            and id = #{id}
        </if>
        <if test="username!=null">
            and username = #{username}
        </if>
        <if test="password!=null">
            and password = #{password}
        </if>
    </where>
</select>
```

测试代码

```java
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//模拟用户
        User condition = new User();
        condition.setId(5);
        condition.setUsername("宇琴");
        condition.setPassword("123456");

        List<User> userList = mapper.findByCondition(condition);
        System.out.println(userList);
    }
```



<foreach>标签，循环执行sql的拼接操作，

如：select * from user where id in (1,2,5)

```java
<select id="findByIds" resultType="user" parameterType="list">
    select * from user
    <where>
        <foreach collection="list" open="id in(" close=")" item="id" separator="," >
            #{id}
        </foreach>
    </where>
</select>
```

测试用例

```java
//    <foreach>测试用例
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//       模拟测试数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(3);
        ids.add(5);
        ids.add(6);

        List<User> userList = mapper.findByIds(ids);
        System.out.println(userList);

    }
```

<sql>中可将重复的sql提取出来，使用时用include引用，最终达到sql重用的目的

```java
<!--    使用sql片段抽取-->
<sql id="selectUser">select * from user</sql>
```

```java
<include refid="selectUser"></include>
```



##### Mybatis的核心配置详讲

###### typeHandlers标签

可以重写类型处理器或创建自己的类型处理器来处理不支持的或非标准的类型。具体为:实现org.apache.ibatis.type.TypeHandler接口或继承BaseTypeHandler类。

基本步骤：

1. 定义转换继承类BaseTypeHandler<>
2. 覆盖4个未实现的方法，其中setNonNullParameter为java程序设置数据到数据库的回调方法，getNullableResult为查询时 mysql的字符串类型转换成 java的Type类型的方法

3. 在MyBatis核心配置文件中进行注册
4. 测试转换是否正确

```java
public class DateTypeHandler extends BaseTypeHandler<Date> {
//	  将java类型转换成数据库需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }
//    将数据库中的类型转换为java类型
//    string s 为数据库中要转换的字段名称
//    resultSet为查询出的结果集
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
//    将获取的结果集中需要的数据（long）转换后date类型返回
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型转换为java类型
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型转换为java类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
```

```java
<!--    注册类型处理器-->
    <typeHandlers>
        <typeHandler handler="cn.xujian.handler.DateTypeHandler"></typeHandler>
    </typeHandlers>
```

```java
public class MybatisTest {
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        模拟user
        User user = new User();
        user.setUsername("小贱贱");
        user.setPassword("123hhh");
        user.setBirthday(new Date());

        mapper.save(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll();
        System.out.println(userList);
    }
}
```

######  plugins标签

MyBatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式即可获得分页的相关数据

开发步骤：

①导入通用PageHelper的坐标

②在mybatis核心配置文件中配置PageHelper插件

③测试分页数据获取

```java
<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper -->
<dependency>
  <groupId>com.github.pagehelper</groupId>
  <artifactId>pagehelper</artifactId>
  <version>3.7.5</version>
</dependency>
<dependency>
  <groupId>com.github.jsqlparser</groupId>
  <artifactId>jsqlparser</artifactId>
  <version>0.9.1</version>
</dependency>
```

```java
<!--    配置分页助手插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
<!--            指定方言-->
            <property name="dialect" value="mysql"/>
        </plugin>
```

```java
//    分页插件的测试
    @Test
    public void test03() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        设置分页相关参数，当前页+每页显示的条数
        PageHelper.startPage(1,3);

        List<User> userList = mapper.findAll();
        for (User user :userList){
            System.out.println(user);
        }
        sqlSession.close();
    }
```

**获得分页相关的其他参数**

```java
//其他分页的数据
PageInfo<User> pageInfo = new PageInfo<User>(select);
System.out.println("总条数："+pageInfo.getTotal());
System.out.println("总页数："+pageInfo.getPages());
System.out.println("当前页："+pageInfo.getPageNum());
System.out.println("每页显示长度："+pageInfo.getPageSize());
System.out.println("是否第一页："+pageInfo.isIsFirstPage());
System.out.println("是否最后一页："+pageInfo.isIsLastPage());

```
