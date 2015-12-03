package com.opendmp.service;

import javax.servlet.http.HttpServletRequest;

import com.opendmp.conf.DomainValidationConfig;
import com.opendmp.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opendmp.util.IOUtils;
import com.opendmp.service.UserCache;

import com.opendmp.conf.DomainValidationConfig;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class ValidationService {

    private static Logger         logger  = LoggerFactory.getLogger(ValidationService.class);
    @Autowired
    private DomainValidationConfig domainValidationConfig;

    @Autowired
    private UserCache userCache;

    private static final int MINUTE_MAX_RATE = 30;
    private static final int HOUR_MAX_RATE = 1000;
    private static final int DAY_MAX_RATE = 5000;

    private static final String IP_RATE_MIN = "IP_RATE_MIN";
    private static final String IP_RATE_HOUR = "IP_RATE_HOUR";
    private static final String IP_RATE_DAY= "IP_RATE_DAY";

    public boolean isValidRequest(HttpServletRequest req) {
        String ip = IOUtils.getClientIP(req);
        String domain = IOUtils.getDomain(req);
        String uid = IOUtils.getUID(req);

        if (isIpBlacklisted(ip)||isDomainBlackListed(domain)||isDomainWhiteListed(domain)||isRateExceded(ip,uid)) {
            logger.debug("IP_BLACKLISTED : " + ip);
            return false;
        }

        return true;
    }

    private boolean isRateExceded(String sourceip, String uid) {
        final String key = uid + "_" + sourceip;

        if(checkRateExceededFromCache(key, IP_RATE_MIN, MINUTE_MAX_RATE, 60)){
            logger.info("MINUTE_MAX_RATE_EXCEEDED : " + key);
            return true;
        }
        if(checkRateExceededFromCache(key, IP_RATE_HOUR, HOUR_MAX_RATE, 3600)){
            logger.info("HOUR_MAX_RATE_EXCEEDED : " + key);
            return true;
        }
        if(checkRateExceededFromCache(key, IP_RATE_DAY, DAY_MAX_RATE,86400)){
            logger.info("DAY_MAX_RATE_EXCEEDED : " + key);
            return true;
        }

        return false;
    }


    private boolean checkRateExceededFromCache(String key,String rateType, int limit,int ttl) {
        Boolean status = true;
        if(rateType.equals(IP_RATE_MIN)) {
            userCache.updateUserMinuteCache(key);
            AtomicLong uHits = userCache.getUseMinuterHits(key);
            if (limit > uHits.intValue()) {
                status = false;
            }
        }else if(rateType.equals(IP_RATE_HOUR)) {
            userCache.updateUserHourCache(key);
            AtomicLong uHits = userCache.getUserHourHits(key);
            if (limit > uHits.intValue()) {
                status = false;
            }
        } else if (rateType.equals(IP_RATE_DAY)) {
            userCache.updateUserDayCache(key);
            AtomicLong uHits = userCache.getUserDayHits(key);
            if (limit > uHits.intValue()) {
                status = false;
            }
        } else {

        }

        return status;
    }

    private boolean isIpBlacklisted(String sourceip) {
        /*
         todo validate the IP ;

         */
    	return false;
    }

    private boolean isDomainBlackListed(String srcDomain) {
        boolean status = false;
        if(domainValidationConfig.isDomainBlacklistEnable()) {
            if (! StringUtils.isEmpty(srcDomain)) {
                if(! domainValidationConfig.isDomainBlacklisted(srcDomain))
                    status = true;
            }
        } else {
            status = true;
        }

        return status;
    }

    private boolean isDomainWhiteListed(String srcDomain){
        boolean status = false;
        if(domainValidationConfig.isDomainWhitelistEnable()) {
            if (!StringUtils.isEmpty(srcDomain)) {
                status = domainValidationConfig.isDomainWhitelisted(srcDomain);
            }
        }else {
            status = true;
        }

        return status;
    }

}
