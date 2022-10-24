package com.module.util.jdbc;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 */
public class DBUtil {
    private static String driver;//驱动类名
    private static String username;//用户名
    private static String password;//密码
    private static String url;//连接地址

    public static Connection getConn() {
        Connection conn = null;
        try {
            //加载属性文件，读取数据库连接配置信息
            Properties pro = new Properties();
            try {
                pro.load(DBUtil.class.getResourceAsStream("/jdbc.properties"));
            } catch (IOException e) {
                System.out.println("未找到配置文件！！！");
            }
            driver = pro.getProperty("jdbc.driver").trim();
            url = pro.getProperty("jdbc.url").trim();
            username = pro.getProperty("jdbc.username").trim();
            password = pro.getProperty("jdbc.password").trim();
            System.out.println("password = " + password);
            Class.forName(driver); //加载驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * jdbc执行executeUpdate语句封装方法
     *
     * @param sql    执行sql 语句
     * @param params 参数列表
     * @return
     */
    public static int executeUpdate(String sql, Object[] params) {
        try {
            Connection connection = getConn();
            PreparedStatement ps = connection.prepareStatement(sql);
            if (params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            int row = ps.executeUpdate();
            ps.close();
            connection.close();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        System.out.println("conn = " + conn);
    }

}
