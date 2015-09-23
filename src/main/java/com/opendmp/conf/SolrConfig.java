package com.opendmp.conf;

public class SolrConfig {

    private String  solrWriteUrl;
    private String  solrReadUrl;
    private boolean autoCommit;

    public String getSolrWriteUrl() {
        return solrWriteUrl;
    }

    public void setSolrWriteUrl(String solrWriteUrl) {
        this.solrWriteUrl = solrWriteUrl;
    }

    public String getSolrReadUrl() {
        return solrReadUrl;
    }

    public void setSolrReadUrl(String solrReadUrl) {
        this.solrReadUrl = solrReadUrl;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

}
