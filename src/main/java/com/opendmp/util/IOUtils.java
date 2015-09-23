package com.opendmp.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

    private static Logger  logger = LoggerFactory.getLogger(IOUtils.class);

    private static String formattedDate;
    static {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        formattedDate = sdf.format(new Date());
    }

    public static String getClientIP(HttpServletRequest req) {
        String sourceip = req.getHeader("X-Forwarded-For");
        if (sourceip == null) {
            sourceip = req.getRemoteAddr();
        }
        return sourceip;
    }
    
    public static JSONObject getAllHeaders(HttpServletRequest req){
        JSONObject headerObj = new JSONObject();
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            Enumeration<String> headers = req.getHeaders(headerName);
            List<String> list = new ArrayList<>();
            while(headers.hasMoreElements()){
                String val = headers.nextElement();
                list.add(val);
            }
            if(list.size()==0){
                continue;
            }
            headerObj.put(headerName, StringUtils.listToString(list));
        }
        return headerObj;
    }

    public static String getVisitorId(HttpServletRequest req) {
        String visitorId = req.getParameter("visitorId");
        if (!StringUtils.isEmpty(visitorId)) {
            return visitorId;
        }
        visitorId = req.getParameter("vid");
        if (!StringUtils.isEmpty(visitorId)) {
            return visitorId;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("vid")) {
                    visitorId = cook.getValue();
                    return visitorId;
                }
            }
        }
        return visitorId;
    }

    public static String getPogIdFromHeaders(JSONObject headerJson){
        try{
            if(headerJson==null){
                return null;
            }
            if(headerJson.get("cookie") instanceof String){
                String cookieStr = (String) headerJson.get("cookie");
                
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getUserAgent(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        return userAgent;
    }

    public static void writePixel(HttpServletResponse resp) throws IOException {
        byte[] imageBytes = {
                71,
                73,
                70,
                56,
                57,
                97,
                1,
                0,
                1,
                0,
                (byte) 128,
                0,
                0,
                (byte) 255,
                (byte) 255,
                (byte) 255,
                0,
                0,
                0,
                33,
                (byte) 249,
                4,
                0,
                0,
                0,
                0,
                0,
                44,
                0,
                0,
                0,
                0,
                1,
                0,
                1,
                0,
                0,
                2,
                2,
                68,
                1,
                0,
                59, };
        resp.getOutputStream().write(imageBytes);
        resp.setHeader("Content-type", "image/gif");
        resp.setHeader("Content-Length", "" + imageBytes.length);
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Last-Modified", formattedDate);
        resp.setHeader("Expires", formattedDate);
        resp.setHeader("Date", formattedDate);
        resp.setHeader("Content-type", "image/gif");
    }

    public static boolean isPrivateIP(String ip) {
        if (ip == null || ip.trim().equals("")) {
            return true;
        }
        if (ip.equals("127.0.0.1")) {
            return true;
        }
        if (ip.startsWith("10.")) {
            return true;
        }
        if (ip.startsWith("192.168")) {
            return true;
        }
        if (ip.startsWith("172.")) {
            return true;
        }
        return false;
    }
    
    public static String getRemoteIPFromXFFList(String ipList){
        if(ipList==null||ipList.trim().equals("")){
            return null;
        }
        String[] split = ipList.split(",");
        for(String s:split){
            String ip = s.trim();
            if(!isValidIP(ip)){
                continue;
            }
            if(isPrivateIP(ip)){
                continue;
            }
            return ip;
        }
        return null;
    }
    
    public static boolean isValidIP(String ip){
        if(ip==null||ip.trim().equals("")){
            return false;
        }
        return Pattern.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}", ip);
    }

	public static JSONObject getPixelData(HttpServletRequest req) {
		JSONObject pixelObj = new JSONObject();
		Map<String, String[]> parameterMap = req.getParameterMap();
		for(Entry<String, String[]> en:parameterMap.entrySet()){
			String param = en.getKey();
			String[] valArr = en.getValue();
			if(valArr.length==1){
				pixelObj.put(param, valArr[1]);
			}else if(valArr.length>1){
				JSONArray arr = new JSONArray();
				arr.addAll(Arrays.asList(valArr));
				pixelObj.put(param, arr);
			}
		}
		JSONObject allHeaders = getAllHeaders(req);
		pixelObj.put("headers", allHeaders);
		return pixelObj;
	}

}
