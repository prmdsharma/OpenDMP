package com.opendmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by pramod.sharma on 07/10/15.
 */
@Service
public class RuleEngineService {
    private static Logger logger         = LoggerFactory.getLogger(RuleEngineService.class);

    private void updateRECache()
    {

    }

    private boolean isValidRule()
    {
        boolean ruleStatus = false;
        /*
        todo validate the rule
         */

        return ruleStatus;
    }

    public boolean updateRuleEngineCache()
    {
        boolean status = false;

        if(isValidRule())
        {
            updateRECache();
            status = true;
        }

        return status;
    }

    public void evaluateNewUserAgainstRules()
    {
        /*
        todo
        Each request data - (All key value pair) need to be evaluate against each rule. There may be multiple rules true for one user
        and hence one user may be in multiple segments. Hence List of segment id's need to br returned.

        For this intelligent DS amd Algo need to be applied.
         */

    }

}
