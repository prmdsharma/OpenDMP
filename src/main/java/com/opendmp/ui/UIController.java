package com.opendmp.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pramod.sharma on 08/10/15.
 */

@Controller
@RequestMapping("/ui/")
public class UIController
{
    private static Logger logger         = LoggerFactory.getLogger(UIController.class);
    private static String apiServerHost = "localhost";

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage()
    {
        ModelAndView model = new ModelAndView("index");
        model.addObject("apihost", apiServerHost);
        model.setViewName("dashboard");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout)
    {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping("/index")
    public ModelAndView openIndexPage1(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("index");
        model.addObject("apihost", apiServerHost);
        model.setViewName("dashboard");
        return model;
    }

    @RequestMapping("/dashboard")
    public ModelAndView openDashboardPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("dashboard");
        model.addObject("apihost", apiServerHost);
        model.setViewName("dashboard");
        return model;
    }

    @RequestMapping("/segments")
    public ModelAndView openSegmentsPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("segments");
        model.addObject("apihost", apiServerHost);
        model.setViewName("segments");
        return model;
    }

    @RequestMapping("/traits")
    public ModelAndView openTraitsPage(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("traits");
        model.addObject("apihost", apiServerHost);
        model.setViewName("traits");
        return model;
    }

    @RequestMapping("/user")
    public ModelAndView openUserPage(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("user");
        model.addObject("apihost", apiServerHost);
        model.setViewName("user");
        return model;
    }

}
