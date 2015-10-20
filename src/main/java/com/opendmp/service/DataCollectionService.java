package com.opendmp.service;

import com.opendmp.conf.PixelTrackerConfig;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.opendmp.conf.DataCollectionConfig;

/**
 * Created by pramod.sharma on 23/09/15.
 */

@Service
public class DataCollectionService {
    private static Logger logger         = LoggerFactory.getLogger(PixelTrackingService.class);
    private static Logger        trackingLogger = LoggerFactory.getLogger("dcs." + PixelTrackingService.class.getName());

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Autowired
    private DataCollectionConfig dataCollectionConfig;

    @Autowired
    private UserAgentStringParser userAgentStringParser;

    private ExecutorService logThreadPool  = Executors.newFixedThreadPool(10);

    public void recordPixel(final JSONObject pixel) {
        try {
            logThreadPool.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        try{
                            String userAgent = (String) pixel.get("user-agent");
                            if(userAgent!=null){
                                ReadableUserAgent readableUserAgent = userAgentStringParser.parse(userAgent);
                                if(readableUserAgent!=null){
                                    if(readableUserAgent.getOperatingSystem()!=null){
                                        pixel.put("os",readableUserAgent.getOperatingSystem().getName());
                                    }
                                    pixel.put("browser",readableUserAgent.getName());
                                }
                            }
                        }catch(Exception e){
                            logger.error(e.getMessage(), e);
                        }

                        trackingLogger.info(pixel.toString());
                        if (dataCollectionConfig.isProducerEnable()) {
                            kafkaSenderService.sendMessage(pixel, false);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }

            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }





}
