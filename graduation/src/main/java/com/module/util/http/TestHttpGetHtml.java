//package com.module.util.http;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.*;
//import java.util.Date;
//
//
///**
// * 煎蛋网图片抓取
// *
// * @author tianming http://www.netbian.com/
// */
//public class TestHttpGetHtml {
//    HttpClient httpClient = HttpClientUtil.getHttpClient();
//
//    private String getIndexHtml(String url) {
//        GetMethod getMethod = null;
//        String infomation = "";
//        getMethod = new GetMethod(url);
//        try {
//            int statcode = httpClient.executeMethod(getMethod);
//            infomation = getMethod.getResponseBodyAsString();
//        } catch (HttpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return infomation;
//    }
//
//    private void jiexi() {
//
//        String html = getIndexHtml("http://www.umei.cc/meinvtupian/2.htm");
//        Document doc = Jsoup.parse(html);
//        Elements eles = doc.select("div[class=TypeList]").select("img");
//
//    }
//
//    /**
//     * 根据网址解析参数
//     *
//     * @param url
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public Landscape getDataBackup(String url) throws UnsupportedEncodingException {
//        // String url="http://www.meilele.com/category-chuang/goods-338206.html";
//        String html = getIndexHtml(url);
//        Document doc = Jsoup.parse(html, "utf-8");
//        String name = doc.select("h4[class=main-title]").text();
//        name = HttpClientUtil.toCharSet(name);
//
//        String address = doc.select("div[class=list]").select("table").select("tr").eq(1).select("td").eq(2).text();
//        address = HttpClientUtil.toCharSet(address);
//        address = address.substring(3);
//
//        String pingpai = doc.select("div[class=list]").select("table").select("tr").eq(0).select("td").eq(2).text();
//        pingpai = HttpClientUtil.toCharSet(pingpai);
//        pingpai = pingpai.substring(5);
//
//        String price = doc.select("strong[id=JS_effect_price]").text();
//
//        String picurl = doc.select("img[id=JS_goods_album_display]").attr("limg");
//        picurl = "https:" + picurl;
//
//        String score = "5.0";
//        Landscape pojo = new Landscape();
//        pojo.setName(name);
//       /* jiaju.setAddress(address);
//        jiaju.setPingpai(pingpai);
//        jiaju.setPrice(price);
//        jiaju.setPicurl(picurl);
//        jiaju.setScore(score);*/
//        pojo.setCreatetime(new Date());
//        return pojo;
//    }
//
//    /**
//     * 根据网址解析参数
//     *
//     * @param url
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public Landscape getData(String url) throws UnsupportedEncodingException {
//        String html = getIndexHtml(url);
//        Document doc = Jsoup.parse(html, "utf-8");
//        String name = doc.select("h1[class=zixunnrbt]").text();
//        String content = doc.select("div[class=newstxtnr]").html();
//        String picurl = doc.select("div[class=newstxtnr]").select("img").eq(0).attr("src");
//        content = content.replaceAll("src=\"ueditor/", "src=\"http://www.landscape.cn/ueditor/");
//        if (!picurl.contains("http")) {
//            picurl = "http://www.landscape.cn" + picurl;
//        }
//
//        Elements ul = doc.select("div[class=xmnr_li]").select("ul");
//        Elements li = ul.select("li");
//        String address = null;
//        String mianji = null;
//        String company = null;
//        String intro = "";
//
//        for (Element element : li) {
//            System.out.println("element = " + element);
//            System.out.println(" = ");
//            String a1 = element.select("div").eq(0).text();
//            String a2 = element.select("div").eq(1).text();
//            intro = intro + a1 + a2 + "<br>";
//            if (a1.contains("地点")) {
//                address = a2;
//            }
//            if (a1.contains("规模")) {
//                mianji = a2;
//
//            }/*  mianji = mianji.substring(0, mianji.indexOf("m"));
//                mianji = mianji.replace("㎡", "");
//                mianji = mianji.replace(".", "");
//                mianji = mianji.replace(",", "");
//                mianji = mianji.trim();*/
//            if (a1.contains("公司")) {
//                company = a2;
//            }
//        }
//
//        //name = HttpClientUtil.toCharSet(name);
//
////        String address = doc.select("div[class=list]").select("table").select("tr").eq(1).select("td").eq(2).text();
////        address = HttpClientUtil.toCharSet(address);
////        address = address.substring(3);
////
////        String pingpai = doc.select("div[class=list]").select("table").select("tr").eq(0).select("td").eq(2).text();
////        pingpai = HttpClientUtil.toCharSet(pingpai);
////        pingpai = pingpai.substring(5);
////
////        String price = doc.select("strong[id=JS_effect_price]").text();
////
////        String picurl = doc.select("img[id=JS_goods_album_display]").attr("limg");
////        picurl = "https:" + picurl;
//
//        Landscape pojo = new Landscape();
//        pojo.setName(name);
//        pojo.setPicurl(picurl);
//        pojo.setContent(content);
//        pojo.setIntro(intro);
//        pojo.setAddress(address);
//        if(null==mianji){
//            mianji="未知";
//        }
//        pojo.setMianji(mianji);
//        pojo.setCompany(company);
//        pojo.setCreatetime(new Date());
//        System.out.println("pojo = " + pojo);
//        return pojo;
//    }
//
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        TestHttpGetHtml demoTest = new TestHttpGetHtml();
//        String url = "http://www.landscape.cn/landscape/10440.html";
//        demoTest.getData(url);
//    }
//}
