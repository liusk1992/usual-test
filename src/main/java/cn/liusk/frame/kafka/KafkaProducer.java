/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.frame.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * kafka生产者
 * @author liusk
 * @version $Id: KafkaProducer.java, v 0.1 2018/5/24 18:07 liusk Exp $
 */
public class KafkaProducer {

    private static Producer<String,String> producer;

    /**
     * 生产者，注意kafka生产者不能够从代码上生成主题，只有在服务器上用命令生成
     */
    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstants.KAFKA_SERVER_IP);//服务器ip:端口号，集群用逗号分隔
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    /**
     * 发送对象消息 至kafka上,调用json转化为json字符串，应为kafka存储的是String。
     * @param msg
     */
    public static void sendMsgToKafka(String msg) {
        producer.send(new ProducerRecord<String, String>(KafkaConstants.KAFKA_TOPIC, String.valueOf(new Date().getTime()),
                msg));
        closeKafkaProducer();
    }

    public static void closeKafkaProducer() {
        producer.close();
    }


    public static void main(String[] args) {
        sendMsgToKafka("hei kafka!");
    }

}
