package cn.xujian.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @param:
 * @return:
 * @auther: xj
 * @date: 2021-05-11 22:01
 * @description:发送消息
*/
public class Producer_Topic {
    public static void main(String[] args) throws IOException, TimeoutException {
//    创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
//    设置参数
        factory.setHost("127.0.0.1");  //默认为localhost
        factory.setPort(5672);  //默认为5672
        factory.setVirtualHost("/xujian");   //设置虚拟机
        factory.setUsername("xujian");
        factory.setPassword("xujian");
//    获取连接 Connection
        Connection connection = factory.newConnection();
//    创建channel
        Channel channel = connection.createChannel();
//    创建交换机
/*
*       exchange:  交换机名称
*       type：      交换机的类型：广播、定向、通配符、参数匹配
*       durable：   是否持久化
*       autoDelete  自动删除
*       internal    内部使用，一般false
*       arguments   参数
* */
        String exchangeName = "test_topic";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,false,null);
//    创建队列
        String queue1Name = "test_topic_queue1";
        String queue2Name = "test_topic_queue2";
        channel.queueDeclare(queue1Name,true, false,false,null);
        channel.queueDeclare(queue2Name,true, false,false,null);
//    绑定队列和交换机
        /*
        * queue  队列名称
        * exchange 交换机名称
        * routingKey  路由键，绑定名称   如果交换机类型为fanout，routingKey设置为”“
        * */

//        routing key  系统的名称.日志的级别         需求：所有error级别和order系统的日志存入数据库
//        队列1绑定
        channel.queueBind(queue1Name,exchangeName,"#.error");
        channel.queueBind(queue1Name,exchangeName,"order.*");
//        队列2绑定
        channel.queueBind(queue2Name,exchangeName,"*.*");

//    发送消息

//        测试order系统
//        String body = "日志信息：调用了findall方法....日志级别：info";
//        channel.basicPublish(exchangeName,"order.info",null,body.getBytes());

//        测试error级别
//        String body = "日志信息：调用了delete方法....日志级别：error";
//        channel.basicPublish(exchangeName,"order.error",null,body.getBytes());

//        测试无order系统和error级别
        String body = "日志信息：调用了find方法....日志级别：info";
        channel.basicPublish(exchangeName,"good.info",null,body.getBytes());

//    释放资源
        channel.close();
        connection.close();
    }


}
