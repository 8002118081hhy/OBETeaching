package com.module.util.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * 煎蛋网图片抓取
 *
 * @author tianming http://www.netbian.com/
 */
public class HttpTestDemo {
    HttpClient httpClient = HttpClientUtil.getHttpClient();

    private String getIndexHtml(String url) {
        GetMethod getMethod = null;
        String infomation = "";
        getMethod = new GetMethod(url);
        try {
            int statcode = httpClient.executeMethod(getMethod);
            infomation = getMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infomation;
    }

    private void jiexi() {
        // 煎蛋网分页 从21页图片抓取第一页
        for (int x = 1; x <= 10; x++) {

            try {
                System.out.println("暂停5秒继续抓取...");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String html = getIndexHtml("http://www.umei.cc/meinvtupian/" + x + ".htm");
            Document doc = Jsoup.parse(html);
            Elements eles = doc.select("div[class=TypeList]").select("img");
            for (int i = 0; i < eles.size(); i++) {
                String src = eles.get(i).attr("src");
                String title = eles.get(i).attr("title");
                System.out.println(src);
                writeDir(src, "第" + x + "页-" + i);
            }
        }
    }

    private void writeDir(String src, String file_name) {
        InputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // 截取文件后缀名
            String fileNames[] = src.split("/");
            String fileName = fileNames[fileNames.length - 1];
            String prefix = fileName.substring(fileName.lastIndexOf(".")); // 截取文件后缀名，防止gif图片变成普通图片
            // 超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            File dir = new File("D://金馆长表情包02");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File imageFile = new File("D://金馆长表情包02/" + file_name + prefix);
            outStream = new FileOutputStream(imageFile);
            outStream.write(data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readInputStream(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outStream.toByteArray();
    }

    public static void main(String[] args) {
        HttpTestDemo demoTest = new HttpTestDemo();
        demoTest.jiexi();
    }
}
