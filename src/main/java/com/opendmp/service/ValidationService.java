package com.opendmp.service;

import javax.servlet.http.HttpServletRequest;

import com.opendmp.conf.DomainValidationConfig;
import com.opendmp.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opendmp.util.IOUtils;
import com.opendmp.conf.DomainValidationConfig;

@Service
public class ValidationService {

    private static Logger         logger  = LoggerFactory.getLogger(ValidationService.class);
    @Autowired
    private DomainValidationConfig domainValidationConfig;

    private static final int MINUTE_MAX_RATE = 30;
    private static final int HOUR_MAX_RATE = 1000;
    private static final int DAY_MAX_RATE = 5000;

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

        if(checkRateExceededFromCache(key, "ip_rate_min", MINUTE_MAX_RATE, 60)){
            logger.info("MINUTE_MAX_RATE_EXCEEDED : " + key);
            return true;
        }
        if(checkRateExceededFromCache(key, "ip_rate_hour", HOUR_MAX_RATE, 3600)){
            logger.info("HOUR_MAX_RATE_EXCEEDED : " + key);
            return true;
        }
        if(checkRateExceededFromCache(key, "ip_rate_day", DAY_MAX_RATE,86400)){
            logger.info("DAY_MAX_RATE_EXCEEDED : " + key);
            return true;
        }

        return false;
    }

    private boolean checkRateExceededFromDB(String key, String table, int limit, int ttl) {
//        DBRecord dbRecordSec = aeroSpikeDBManager.get(DB_NAME, table, key);
//        if (dbRecordSec == null) {
//            aeroSpikeDBManager.set(DB_NAME, table, key, 1, ttl);
//        } else {
//            JSONObject bins = dbRecordSec.getBins();
//            if (bins != null && bins.get("value") instanceof Number) {
//                Number number = (Number) bins.get("value");
//                if (number.intValue() > limit) {
//                    return true;
//                }
//                Map<String, Number> binValueMap = new HashMap<>();
//                binValueMap.put("value", 1);
//                aeroSpikeDBManager.incrementValue(DB_NAME, table, key, binValueMap, ttl);
//            }
//        }
        return false;
    }

    private boolean checkRateExceededFromCache(String key,String rateType, int limit,int ttl) {
        /*
          todo
          build  cacahe for this
         */



        return false;
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
