package cn.xujian.producer;

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
public class Producer_WorkQueues {
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
//    创建队列
        /**
         * 参数1：队列名称  queue
         * 参数2：是否定义持久化队列（消息会持久化保存在服务器上） durable
         * 参数3：是否独占本连接    exclusive
         * 参数4：是否在不使用的时候队列自动删除  autoDelete
         * 参数5：其它参数  arguments
         */
//        如果没有hello_world的队列则自动创建
        channel.queueDeclare("work_queues",true,false,false,null);
        /**
         * 参数1：交换机名称；如果没有则指定空字符串（表示使用默认的交换机）
         * 参数2：路由key，简单模式中可以使用队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        //    发送消息
        for (int i = 0; i < 10; i++) {
            String message = i + "hello xujian boy";
            channel.basicPublish("", "work_queues", null, message.getBytes());
            System.out.println("已发送消息：" + message);
        }

        //6. 关闭资源
        channel.close();
        connection.close();
    }


}
