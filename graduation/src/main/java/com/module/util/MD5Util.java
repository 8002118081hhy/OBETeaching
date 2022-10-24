package com.module.util;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能简介：MD5加密工具类
 * 密码等安全信息存入数据库时，转换成MD5加密形式
 */
public class MD5Util {
    public static String salt = ""; //加盐密码

    /*获取加密后的密码*/
    public static String getMd5(String pwd) {
        pwd = salt + pwd;
        String outStr = null;
        if (pwd == null) {
            outStr = null;
        } else if ("".equals(pwd)) {
            outStr = "";
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5"); //创建MessageDigest对象，采用MD5算法
                md.update(pwd.getBytes()); //向MessageDegist传送要计算的数据，这里把密码pwd传送过去
                byte b[] = md.digest(); //计算摘要
                StringBuffer buf = new StringBuffer();
                //将字节数组转换成16进制
                for (int i = 1; i < b.length; i++) {
                    int c = b[i] >>> 4 & 0xf;  //>>>无符号右移，高位补0
                    buf.append(Integer.toHexString(c));
                    c = b[i] & 0xf;
                    buf.append(Integer.toHexString(c));
                }
                outStr = buf.toString();
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return outStr;
    }

    /*测试*/
    public static void main(String[] args) {
        String pwd = "123456";
        pwd = MD5Util.getMd5(pwd);
        System.out.println(pwd);

    }

}