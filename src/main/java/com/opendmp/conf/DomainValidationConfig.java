package com.opendmp.conf;

import com.opendmp.util.StringUtils;

import java.util.*;

/**
 * Created by pramod.sharma on 30/11/15.
 */
public class DomainValidationConfig {
    private boolean enable_Domain_Whitelist;
    private boolean enable_Domain_Blacklist;
    private Map<String, String> whiteListDomainMap = new HashMap<>();
    private Map<String, String> blackListDomainMap = new HashMap<>();


    public boolean isDomainWhitelistEnable() {
        return enable_Domain_Whitelist;
    }
    public void setDomainWhitelistEnable(boolean enable) {this.enable_Domain_Whitelist = enable;}

    public boolean isDomainBlacklistEnable() {
        return enable_Domain_Blacklist;
    }
    public void setEnable(boolean enable) {this.enable_Domain_Blacklist = enable;}

    public void updateWhiteListDomainMap(String domainList){
        if(! StringUtils.isEmpty(domainList)) {
            StringTokenizer domains = new StringTokenizer(domainList,",");
            while(domains.hasMoreTokens()) {
                whiteListDomainMap.put(domains.nextToken(),"true");
            }
        }
    }

    public void updateBlackListDomainMap(String domainList){
        if(! StringUtils.isEmpty(domainList)) {
            StringTokenizer domains = new StringTokenizer(domainList,",");
            while(domains.hasMoreTokens()) {
                blackListDomainMap.put(domains.nextToken(),"true");
            }
        }
    }

    public boolean isDomainWhitelisted(String domain) {
        if(whiteListDomainMap.containsKey(domain)) {
            return true;
        }

        return false;
    }

    public boolean isDomainBlacklisted(String domain) {
        if(blackListDomainMap.containsKey(domain)){
            return true;
        }

        return false;
    }

}
