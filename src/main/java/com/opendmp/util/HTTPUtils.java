package com.opendmp.util;

/**
 * Created by pramod.sharma on 22/09/15.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONAware;

public class HTTPUtils {

    public static String post(String url, String data, Map<String, String> headerMap) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            if (data == null) {
                data = "";
            }
            HttpEntity entity = new StringEntity(data);
            post.setEntity(entity);
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> en : headerMap.entrySet()) {
                    post.setHeader(en.getKey(), en.getValue());
                }
            }
            HttpResponse httpResponse = client.execute(post);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuilder sb = new StringBuilder(1024);
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
                return sb.toString();
            }
        } catch (Exception e) {

        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static String postData(String url, String data, String contentType) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            if (data == null) {
                data = "";
            }
            HttpEntity entity = new StringEntity(data);
            post.setEntity(entity);
            post.setHeader("Content-Type", contentType);
            HttpResponse httpResponse = client.execute(post);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuilder sb = new StringBuilder(1024);
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
                return sb.toString();
            }
        } catch (Exception e) {

        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> headerMap) {
        try {
            InputStream inputStream = getDataStream(url, headerMap);
            if (inputStream == null) {
                return null;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuilder sb = new StringBuilder(1024);
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getDataStream(String url) {
        return getDataStream(url, null);
    }

    public static InputStream getDataStream(String url, Map<String, String> headerMap) {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            if (headerMap != null && headerMap.size() > 0) {
                for (Entry<String, String> en : headerMap.entrySet()) {
                    get.setHeader(en.getKey(), en.getValue());
                }
            }
            HttpResponse httpResponse = client.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                return inputStream;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String postJson(String url, JSONAware jsonData) {
        String data = "";
        if (jsonData != null) {
            data = jsonData.toJSONString();
        }
        return postData(url, data, "application/json");
    }

}
