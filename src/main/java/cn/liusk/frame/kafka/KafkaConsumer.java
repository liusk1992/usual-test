/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.frame.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.Properties;

/**
 * kafka消费者
 * @author liusk
 * @version $Id: KafkaConsumer.java, v 0.1 2018/5/24 18:09 liusk Exp $
 */
public class KafkaConsumer {

    private static Consumer<String,String> consumer;

    /**
     * 消费者
     */
    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstants.KAFKA_SERVER_IP);//服务器ip:端口号，集群用逗号分隔
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConstants.KAFKA_TOPIC));
    }

    /**
     * 从kafka上接收对象消息，将json字符串转化为对象，便于获取消息的时候可以使用get方法获取。
     */
    public static void getMsgFromKafka(){
        while(true){
            ConsumerRecords<String, String> records = KafkaConsumer.getKafkaConsumer().poll(100);
            if (records.count() > 0) {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.toString());
                    String msg = record.value();
                    System.out.println(msg);
                }
            }
        }
    }

    public static Consumer<String, String> getKafkaConsumer() {
        return consumer;
    }

    public static void closeKafkaConsumer() {
        consumer.close();
    }

    public static void main(String[] args) {
        getMsgFromKafka();
    }
}
