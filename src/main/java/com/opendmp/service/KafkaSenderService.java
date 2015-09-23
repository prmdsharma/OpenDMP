package com.opendmp.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opendmp.kafka.KafkaProducer;

@Service
public class KafkaSenderService {

    private static Logger   logger     = LoggerFactory.getLogger(KafkaSenderService.class);

    @Autowired
    private KafkaProducer   kafkaPixelProducer;

    private ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public void sendMessage(final JSONObject obj, boolean async) {
        if (async) {
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    kafkaPixelProducer.sendMessage(obj);
                }
            });
        } else {
            kafkaPixelProducer.sendMessage(obj);
        }
    }

}
