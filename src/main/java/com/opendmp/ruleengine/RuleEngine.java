package com.opendmp.ruleengine;

import com.opendmp.service.PixelTrackingService;
import com.opendmp.service.RuleEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pramod.sharma on 22/09/15.
 */
public class RuleEngine {
    private static Logger logger = LoggerFactory.getLogger(RuleEngine.class);


    @Autowired
    private RuleEngineService ruleEngineService;


    private void readXMLConfig()
    {
        /*
        todo
         */
    }

    private boolean preProcessXMLConfigToCache()
    {
        boolean status = false;
        /*
        todo
         */

        return status;

    }

    public void updateRuleEngineCache()
    {
        /*
        todo
         */
    }
    /*
    readXMLConfig();
    preProcessXMLConfigToCache();  // cache or redis to be defind
    This will be job to run each hour or whenever there is change in the config

    updateRuleEngineCache();
     */


    public String getSegmentFromUserData(String userData)
    {
        String segment = null;
        /*

         todo
         lookupRuleEngineCache(); return the segment data with cookie sync pixel.

         */

        return segment;
    }
}
