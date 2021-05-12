package cn.xujian.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @param:
 * @return:
 * @auther: xj
 * @date: 2021-05-11 22:50
 * @description:pubsub模式，消费者接收消息
*/
public class Consumer_Routing2 {
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
        String queue2Name = "test_direct_queue2";
        //5. 创建消费者（接收消息并处理消息）；
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                //路由key
//                System.out.println("路由key为：" + envelope.getRoutingKey());
//                //交换机
//                System.out.println("交换机为：" + envelope.getExchange());
//                //消息id
//                System.out.println("消息id为：" + envelope.getDeliveryTag());
                //接收到的消息
                System.out.println("接收到的消息为：" + new String(body, "utf-8"));
                System.out.println("将日志信息打印到控制台");
            }
        };
        //6. 监听队列
        /**
         * 参数1：队列名
         * 参数2：是否要自动确认；设置为true表示消息接收到自动向MQ回复接收到了，MQ则会将消息从队列中删除；
         * 如果设置为false则需要手动确认
         * 参数3：消费者
         */
        channel.basicConsume(queue2Name, true, defaultConsumer);

    }
}
