package com.opendmp.conf;

/**
 * Created by pramod.sharma on 20/10/15.
 */
public class DataCollectionConfig {

    private boolean producerEnable;
    private boolean consumerEnable;
    private boolean logUploadEnable;
    private String  logPath;
    private String  logUploadPrefix;

    public String getLogUploadPrefix() {
        return logUploadPrefix;
    }

    public void setLogUploadPrefix(String logUploadPrefix) {
        this.logUploadPrefix = logUploadPrefix;
    }

    public boolean isProducerEnable() {
        return producerEnable;
    }

    public void setProducerEnable(boolean producerEnable) {
        this.producerEnable = producerEnable;
    }

    public boolean isConsumerEnable() {
        return consumerEnable;
    }

    public void setConsumerEnable(boolean consumerEnable) {
        this.consumerEnable = consumerEnable;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public boolean isLogUploadEnable() {
        return logUploadEnable;
    }

    public void setLogUploadEnable(boolean logUploadEnable) {
        this.logUploadEnable = logUploadEnable;
    }

}
