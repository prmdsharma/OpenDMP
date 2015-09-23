package com.opendmp.conf;

public class KafkaConfig {

    private String  producerBrokerUrl;
    private String  consumerBrokerUrl;
    private boolean enable;
    private String  topic;
    private boolean consumerEnable;
    private int     consumerPartition;
    private String  groupId;
    private int     counsumerThread;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isConsumerEnable() {
        return consumerEnable;
    }

    public void setConsumerEnable(boolean consumerEnable) {
        this.consumerEnable = consumerEnable;
    }

    public int getConsumerPartition() {
        return consumerPartition;
    }

    public void setConsumerPartition(int consumerPartition) {
        this.consumerPartition = consumerPartition;
    }

    public String getProducerBrokerUrl() {
        return producerBrokerUrl;
    }

    public void setProducerBrokerUrl(String producerBrokerUrl) {
        this.producerBrokerUrl = producerBrokerUrl;
    }

    public String getConsumerBrokerUrl() {
        return consumerBrokerUrl;
    }

    public void setConsumerBrokerUrl(String consumerBrokerUrl) {
        this.consumerBrokerUrl = consumerBrokerUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getCounsumerThread() {
        return counsumerThread;
    }

    public void setCounsumerThread(int counsumerThread) {
        this.counsumerThread = counsumerThread;
    }

}
