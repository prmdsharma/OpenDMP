package com.opendmp.dcs;

/**
 * Created by pramod.sharma on 22/09/15.
 */

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.opendmp.service.PixelTrackingService;

import com.opendmp.service.DataCollectionService;
import com.opendmp.service.ValidationService;
import com.opendmp.util.IOUtils;



@Controller
@RequestMapping("/dcs/v1/")
public class DataCollectionServer {
    private static Logger  logger = LoggerFactory.getLogger(DataCollectionServer.class);


    @Autowired
    private DataCollectionService dataCollectionService;

    @Autowired
    private ValidationService    validationService;

    @RequestMapping({ "", "/" })
    public ModelAndView recordPixel(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
            IOUtils.writePixel(resp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("/visit")
    public ModelAndView recordVisit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    @RequestMapping("/click")
    public ModelAndView recordClick(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping({ "/conv" })
    public ModelAndView recordConversionPixel(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
            IOUtils.writePixel(resp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping({ "/rd" })
    public ModelAndView recordClickPixel(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            String rurl = req.getParameter("rurl");
            if (rurl == null) {
                try {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "redirect url not found");
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
            try {
                String rdUrl = URLDecoder.decode(rurl, "UTF-8");
                resp.sendRedirect(rdUrl);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                try {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid redirect url");
                } catch (IOException e1) {
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping({ "/dc" })
    public ModelAndView recordDCPixel(HttpServletRequest req, HttpServletResponse resp)
    {
        try {
            if (!validationService.isValidRequest(req)) {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
            JSONObject ptd = IOUtils.getPixelData(req);
            dataCollectionService.recordPixel(ptd);
            /*
               if (isNewUser())  // if cookie not found in case of the web
                  segment = getUserSegmentForNewUser(); //from the  pre-processed cache
               else
                  segment = getUserSegmentForOldUser();


               if (isCookieSyncRequired(segment))
                  doCookieSync()


             */


        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }

    private void getSegmentinfo(JSONObject ptd)
    {

      //todo

    }


}
