package com.opendmp.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opendmp.conf.KafkaConfig;

public class KafkaProducer {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaConfig config) {
        kafkaConfig = config;
        init();
    }

    private KafkaConfig              kafkaConfig;

    private Producer<String, String> producer;

    public void init() {
        if (!kafkaConfig.isEnable()) {
            logger.info("kafka is disabled");
            return;
        }
        Properties props = new Properties();
        props.put("metadata.broker.list", kafkaConfig.getProducerBrokerUrl());
        props.put("broker.list", kafkaConfig.getProducerBrokerUrl());
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        producer = new Producer<String, String>(new ProducerConfig(props));
        logger.info("kafka producer[" + kafkaConfig.getTopic() + "] started");
    }

    public void sendMessage(JSONObject jsonObj) {
        if (!kafkaConfig.isEnable()) {
            return;
        }
        if (jsonObj == null) {
            return;
        }
        KeyedMessage<String, String> message = new KeyedMessage<String, String>(kafkaConfig.getTopic(), jsonObj.toString());
        producer.send(message);
    }

}
