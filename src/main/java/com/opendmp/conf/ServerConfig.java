package com.opendmp.conf;

import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.opendmp.kafka.KafkaProducer;

@Configuration
@PropertySource({ "classpath:app.properties", "classpath:kafka.properties" })
public class ServerConfig {

	private static Logger logger = LoggerFactory.getLogger(ServerConfig.class);

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public PixelTrackerConfig pixelTrackerConfig() {
		PixelTrackerConfig config = new PixelTrackerConfig();
		config.setConsumerEnable(env.getProperty("pixel.consumer.enable",
				Boolean.class));
		config.setProducerEnable(env.getProperty("pixel.producer.enable",
				Boolean.class));
		config.setLogUploadEnable(env.getProperty("pixel.log.upload.enable",
				Boolean.class));
		config.setLogPath(env.getProperty("pixel.log.path"));
		config.setLogUploadPrefix(env.getProperty("pixel.log.upload.prefix"));
		return config;
	}

	public KafkaConfig kafkaPixelConfig() {
		KafkaConfig config = new KafkaConfig();
		config.setEnable(env.getProperty("kafka.enable", Boolean.class));
		config.setProducerBrokerUrl(env.getProperty(
				"kafka.broker.producer.url", String.class));
		config.setConsumerBrokerUrl(env.getProperty(
				"kafka.broker.consumer.url", String.class));
		config.setConsumerEnable(env.getProperty("kafka.pixel.consumer.enable",
				Boolean.class));
		config.setConsumerPartition(env.getProperty(
				"kafka.pixel.consumer.partition", Integer.class, 0));
		config.setTopic(env.getProperty("kafka.pixel.topic", String.class));
		config.setGroupId(env.getProperty("kafka.pixel.consumer.group",
				String.class));
		config.setCounsumerThread(env.getProperty(
				"kafka.pixel.consumer.thread", Integer.class, 1));
		return config;
	}

	@Bean(name = "kafkaPixelProducer")
	public KafkaProducer kafkaProducer() {
		KafkaProducer producer = new KafkaProducer(kafkaPixelConfig());
		return producer;
	}

	// @Bean(name="tagretSolrConfig")
	public SolrConfig tagretSolrConfig() {
		SolrConfig config = new SolrConfig();
		config.setSolrWriteUrl(env.getProperty("solr.target.write.url"));
		config.setSolrReadUrl(env.getProperty("solr.target.read.url"));
		config.setAutoCommit(env.getProperty("solr.target.autocommit",
				Boolean.class));
		return config;
	}

	@Bean
	public UserAgentStringParser userAgentStringParser() {
		UserAgentStringParser onlineUpdatingParser = UADetectorServiceFactory
				.getOnlineUpdatingParser();
		return onlineUpdatingParser;
	}

}
