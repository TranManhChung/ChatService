package com.vng.apigateway;

//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

//kafka producer
//class Producer {
//
//    public static void main(String[]args){
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "localhost:9092");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        KafkaProducer kafkaProducer = new KafkaProducer(properties);
//        try {
//            for (int i = 0; i < 100 ; i++ ){
//                System.out.println(i);
//                kafkaProducer.send(new ProducerRecord("devglan-test", Integer.toString(i), "test message - " + i));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            kafkaProducer.close();
//        }
//    }
//
//}
//
////kafka consumer
//class Consumer{
//
//    public static void main(String[] args) {
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "localhost:9092");
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("group.id", "test-group");
//
//        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
//        List topics = new ArrayList();
//        topics.add("devglan-test");
//        kafkaConsumer.subscribe(topics);
//        try{
//            while (true){
//                ConsumerRecords<Long,String> records = kafkaConsumer.poll(1);
//                records.forEach(record -> {
//                    System.out.println("Record Key " + record.key());
//                    System.out.println("Record value " + record.value());
//                    System.out.println("Record partition " + record.partition());
//                    System.out.println("Record offset " + record.offset());
//                });
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }finally {
//            kafkaConsumer.close();
//        }
//    }
//
//}