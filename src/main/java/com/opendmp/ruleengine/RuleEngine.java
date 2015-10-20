package com.opendmp.ruleengine;

import com.opendmp.service.PixelTrackingService;
import com.opendmp.service.RuleEngineService;
import com.opendmp.service.ValidationService;
import com.opendmp.util.IOUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by pramod.sharma on 22/09/15.
 */


@Controller
@RequestMapping("/re/v1")

public class RuleEngine {

    private static Logger logger = LoggerFactory.getLogger(RuleEngine.class);

    @Autowired
    private PixelTrackingService pixelTrackingService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private  RuleEngineService ruleEngineService;

    @RequestMapping({ "", "/" })
    public ModelAndView recordRule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            boolean ruleStatus = validateRule(ptd);
            if (ruleStatus = true) {
                ruleEngineService.recordRule(ptd);
                IOUtils.writePixel(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private boolean validateRule(JSONObject ptd)
    {
        boolean isValidRule = false;
        if(ptd != null)
        {
            //todo
            // Complete parsing need to done here
        }
        return isValidRule;
    }

    @RequestMapping("/update")
    public ModelAndView updateRule(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            if(! validationService.isValidRequest(req))
            {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            boolean ruleStatus = validateRule(ptd);
            if (ruleStatus = true) {
                ruleEngineService.recordRule(ptd);
                IOUtils.writePixel(resp);
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);

        }
        return null;
    }

    @RequestMapping("/createNew")
    public ModelAndView createNewRule(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            boolean ruleStatus = validateRule(ptd);
            if (ruleStatus = true) {
                ruleEngineService.recordRule(ptd);
                IOUtils.writePixel(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @RequestMapping("/delete")
    public ModelAndView deleteRule(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            boolean ruleStatus = validateRule(ptd);
            if (ruleStatus = true) {
                ruleEngineService.recordRule(ptd);
                IOUtils.writePixel(resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

}
