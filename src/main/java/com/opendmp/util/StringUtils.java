package com.opendmp.util;

/**
 * Created by pramod.sharma on 22/09/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringUtils {

    public static Map<String, List<String>> getRequestParameters(String urlStr) {
        Map<String, List<String>> map = new HashMap<>();
        if (urlStr == null || urlStr.indexOf('?') == -1) {
            return map;
        }
        urlStr = urlStr.substring(urlStr.indexOf('?') + 1);
        String[] sarr = urlStr.split("\\&");
        for (String s : sarr) {
            if (s.indexOf('=') == -1) {
                continue;
            }
            String key = s.substring(0, s.indexOf('='));
            String val = s.substring(s.indexOf('=') + 1);
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(val);
        }
        return map;
    }

    public static String getStringParam(Map<String, List<String>> requestParams, String paramName) {
        if (requestParams == null) {
            return null;
        }
        List<String> list = requestParams.get(paramName);
        if (list != null && list.size() > 0) {
            String s = list.get(0);
            return s;
        }
        return null;
    }



    public static String getEscapedString(String s, char charTobeEscaped) {
        if (s == null) {
            return "";
        }
        s = s.replace(charTobeEscaped + "", "\\" + charTobeEscaped);
        return s;
    }

    public static void main(String[] args) {

    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static String listToString(List<String> list) {
        return listToString(list, ",");
    }

    public static String listToString(List<String> list, String seperator) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            boolean first = true;
            for (String s : list) {
                if (!first) {
                    sb.append(seperator);
                }
                sb.append(s);
                first = false;
            }
        }
        return sb.toString();
    }

}
