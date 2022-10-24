package com.module.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;

import java.io.UnsupportedEncodingException;

public abstract class HttpClientUtil {
    private static HttpClient httpClient = null;
    private static int timeout = 50000;

    /*
     * 无参构造器
     */
    public HttpClientUtil() {

    }

    public static HttpClient getHttpClient() {
        httpClient = new HttpClient(new SimpleHttpConnectionManager(true));
        httpClient.getParams().setSoTimeout(timeout);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        return httpClient;

    }

    /**
     * 中文抓取乱码处理
     *
     * @param s
     * @return
     */
    public static String toCharSet(String s) {
        try {
            return new String(s.getBytes("ISO8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
