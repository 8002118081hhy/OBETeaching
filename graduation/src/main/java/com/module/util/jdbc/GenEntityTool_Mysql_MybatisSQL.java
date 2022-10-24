package com.module.util.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

//JDBC读取数据库元数据,生成JAVA实体类

/**
 * java 通过数据库中属性生成相对应的Javabean
 * 只是实现了常用字段的生成 生成路径默认是项目根目录下面{项目名/生成文件在这里}
 * 使用方法（只适合mysql数据库）：
 * 修改下面的数据库连接信息，然后使用主函数运行下面方法，然后点击项目名称右键刷新，或者F5刷新，
 * 就可以看到生成实体类文件，将文件复制到项目中就可以，修改一下包路径就可以
 *
 * @author 谭清明
 * @QQ 2551449109
 */
public class GenEntityTool_Mysql_MybatisSQL {
    public static void main(String[] args) {
        // MYSQL 数据库连接驱动信息
        String databaseName = "db_ly";// mysql数据库名称
        String user = "root"; // 数据库连接用户名
        String password = "tianming"; // 数据库连接密码
        String tableName = "routeinfo"; // 要生成实体类的数据库表名（数据库中表名称）
        String entityName = "Routeinfo";// 要生成实体类的表名 【Java类名】
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName + "?characterEncoding=utf8";// //
        new GenEntityTool_Mysql_MybatisSQL(driverClassName, url, user, password, tableName, entityName);

        /**
         * 案例：
         * 使用说明： 修改数据库名称 --databaseName test
         * 修改数据库表名 --tableName tb_user
         * 修改生成Java类的类名 --entityName UserEntity
         *
         * 修改后直接使用main函数运行程序 可以在项目目录下面
         * entityName值命名.java的文件
         * 就是根据数据库生成实体类 @2551449109 有问题可以直接咨询
         *
         */

    }

    private String tablename = ""; // 要生成实体类的表名
    private String entityName = ""; // 要生成实体类的实体名称

    private String[] colnames; // 列名数组

    private String[] colTypes; // 列名类型数组

    private int[] colSizes; // 列名大小数组

    private String[] CommentSizes; // 注释内容数组

    private boolean f_util = true; // 是否需要导入包java.util.* false 不引入

    private boolean f_sql = false; // 是否需要导入包java.sql.* false 不引入

    private String driverClassName;// 数据库驱动名称
    private String url; // 数据库连接地址
    private String password; // 数据库连接密码
    private String user; // 数据库连接用户名

    public GenEntityTool_Mysql_MybatisSQL(String driverClassName, String url, String user, String password,
                                          String tablename, String entityName) {
        this.tablename = tablename;
        this.driverClassName = driverClassName;
        this.url = url;
        this.password = password;
        this.user = user;
        this.entityName = entityName;
        Connection conn;
        try {
            conn = getConnection();
            // 得到数据库连接
            String strsql = "select * from " + tablename;
            try {
                PreparedStatement pstmt = conn.prepareStatement(strsql);
                ResultSetMetaData rsmd = pstmt.getMetaData();
                int size = rsmd.getColumnCount(); // 共有多少列
                colnames = new String[size];
                colTypes = new String[size];
                colSizes = new int[size];
                CommentSizes = new String[size];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    colnames[i] = rsmd.getColumnName(i + 1);
                    colTypes[i] = rsmd.getColumnTypeName(i + 1);
                    if (colTypes[i].equalsIgnoreCase("datetime")) {
                        f_util = true;
                    }
                    if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                        f_sql = true;
                    }
                    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
                }

                /**
                 * 读取字段注释信息
                 *
                 */
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("show full columns from " + tablename);
                int a = 0;
                while (rs.next()) { //获取字段注释
                    CommentSizes[a] = rs.getString("Comment");
                    a++;
                }
                String content = parse(colnames, colTypes, colSizes);
                try {
                    //将内容输出为文件
                    FileWriter fw = new FileWriter(entityName + ".java");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConn(conn);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("加载数据库驱动错误!", e);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接错误!", e);
        }
        return conn;

    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public GenEntityTool_Mysql_MybatisSQL() {
        Connection conn;
        try {
            conn = getConnection();// 得到数据库连接
            String strsql = "select * from " + tablename;
            try {
                PreparedStatement pstmt = conn.prepareStatement(strsql);
                ResultSetMetaData rsmd = pstmt.getMetaData();
                int size = rsmd.getColumnCount(); // 共有多少列
                colnames = new String[size];
                colTypes = new String[size];
                colSizes = new int[size];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    colnames[i] = rsmd.getColumnName(i + 1);
                    colTypes[i] = rsmd.getColumnTypeName(i + 1);
                    if (colTypes[i].equalsIgnoreCase("datetime")) {
                        f_util = true;
                    }
                    if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                        f_sql = true;
                    }
                    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
                }
                String content = parse(colnames, colTypes, colSizes);
                try {
                    FileWriter fw = new FileWriter(entityName + ".java");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConn(conn);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 解析处理(生成实体类主体代码)
     */
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        if (f_util) {
            sb.append("import java.util.Date; \r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*; \r\n\r\n\r\n");
        }
        sb.append("public class " + entityName + " {\r\n");
        processAllAttrs(sb);
        sb.append("\n");// 所有属性之后追加一个换行
        processNullConstructor(sb);
        processAllMethod(sb);
        processtoString(sb);
        sb.append("}\r\n");
        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * 生成所有的方法
     *
     * @param sb
     */

    private void processAllMethod(StringBuffer sb) {
        /**
         * 生成servlet自动获取参数的代码
         */
        createRequest();
        /************jdbc PrepareStatement SQL start **************************/
        createInsertJdbcSQL();
        createDeleteJdbcSQL();
        createUpdateJdbcSQL();
        createSelectJdbcSQL();
        /************jdbc PrepareStatement SQL end **************************/

        /************jdbc Mybatis SQL start **************************/
        String showColnames = "";
        String restultColnames = "";
        String selectSql = "SELECT  \n";
        String insertSql = null;
        String insertSql2 = ") VALUES (\n";
        String updatesql = "";
        String deleteSql = "DELETE FROM " + tablename + " WHERE ";
        deleteSql += colnames[0] + "  = #{" + colnames[0] + "} ";

        insertSql = "INSERT INTO  " + tablename + "(\n";// insert SQL
        updatesql = "UPDATE  " + tablename + "\n<set> ";// update SQL
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");

            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n\n");

            // 以下用于生成实体类操作Mybatis中sql 语句
            showColnames += colnames[i] + ",\n";
            restultColnames += "<result property=\"" + colnames[i] + "\" column=\"" + colnames[i] + "\" />\n";
            selectSql += colnames[i] + ",\n";

            insertSql += colnames[i] + ",\n";
            insertSql2 += "#{" + colnames[i] + "},\n";

            if (i != 0) {
                updatesql += "\n<if test=\"" + colnames[i] + " != null and " + colnames[i] + " != ''\">\n";
                updatesql += "\t" + colnames[i] + " = #{" + colnames[i] + "},\n";
                updatesql += "</if>";
            }

        }
        showColnames = showColnames.substring(0, showColnames.length() - 2);// 除去最后一个，
        selectSql = selectSql.substring(0, selectSql.length() - 2);// 除去最后一个，
        selectSql += " FROM " + tablename;
        System.out.println(showColnames);
        System.out.println();
        System.out.println(restultColnames);
        System.out.println();
        System.out.println(selectSql);
        System.out.println();
        insertSql = insertSql.substring(0, insertSql.length() - 2);// 除去最后一个，
        insertSql2 = insertSql2.substring(0, insertSql2.length() - 2);// 除去最后一个，
        insertSql += insertSql2 + "\n\t) ";
        System.out.println(insertSql);
        System.out.println();
        updatesql += "\n</set>" + "\n\t" + " where " + colnames[0] + " = #{" + colnames[0] + "}";
        System.out.println(updatesql);
        System.out.println();
        System.out.println(deleteSql);
        /************jdbc Mybatis SQL end **************************/
    }

    /**
     * create insert SQL
     */
    private void createInsertJdbcSQL() {
        String insertSql = null;
        String insertSql2 = ") VALUES (";
        insertSql = "INSERT INTO  " + tablename + "(";
        for (int i = 0; i < colnames.length; i++) {
            insertSql += colnames[i] + ",";
            insertSql2 += "?,";
        }
        insertSql = insertSql.substring(0, insertSql.length() - 1);// 除去最后一个，
        insertSql2 = insertSql2.substring(0, insertSql2.length() - 1);// 除去最后一个，
        insertSql += insertSql2 + ")";
        System.out.println(insertSql + "\n");
    }

    /**
     * create delete SQL
     */
    private void createDeleteJdbcSQL() {
        String deleteSql = "DELETE FROM " + tablename + " WHERE ";
        deleteSql += colnames[0] + "  = ? " + "\n";
        System.out.println(deleteSql + "\n");
    }

    /**
     * create update SQL
     */
    private void createUpdateJdbcSQL() {
        String updateSql = "";
        updateSql = "UPDATE  " + tablename + " SET  ";// update SQL
        for (int i = 1; i < colnames.length; i++) {
            updateSql += colnames[i] + "=?,";
        }
        updateSql = updateSql.substring(0, updateSql.length() - 1);//去除多余的逗号
        updateSql += " WHERE " + colnames[0] + "=? ";
        System.out.println(updateSql + "\n");
    }

    /**
     * create select SQL
     */
    private void createSelectJdbcSQL() {
        String selectSql = "SELECT  ";
        for (int i = 0; i < colnames.length; i++) {
            selectSql += colnames[i] + ",";
        }
        selectSql = selectSql.substring(0, selectSql.length() - 1);// 除去最后一个，
        selectSql += " FROM " + tablename;
        System.out.println(selectSql + "\n");
    }


    /**
     * 生成Servlet自动获取参数的代码
     */
    private void createRequest() {
        String str = "";
        StringBuffer sb = new StringBuffer();
        String objName = initLowercase(entityName);
        sb.append(entityName + " " + objName + "= new " + entityName + "();\n");
        for (int i = 0; i < colnames.length; i++) {
            str += "String " + colnames[i] + "=" + "request.getParameter(\"" + colnames[i] + "\");\n";

            sb.append(objName + ".set" + initcap(colnames[i]) + "(" + colnames[i] + ");\n");
        }
        System.out.println(str + "\n");
        System.out.println(sb.toString() + "\n");
    }

    /**
     * 解析输出属性
     *
     * @return
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";");
            if (CommentSizes[i] != null && CommentSizes[i] != "") {
                sb.append("\t//" + CommentSizes[i]);
            }
            sb.append(" \r\n");
        }
    }

    /**
     * 把输入字符串的首字母改成小写
     *
     * @param str
     * @return
     */
    private String initLowercase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 判断数据库中的字段类型对应Java中的类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {
        // 以下数据库中字段对应Java字段匹配过程中，可能存在失误
        //对于特殊字段类型，可以需要手动修改一下
        // System.err.println(sqlType);// 输出字段类型名称
        // System.err.println(sqlType);// 输出字段类型名称
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint") || sqlType.equalsIgnoreCase("TINYINT UNSIGNED")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint") || sqlType.equalsIgnoreCase("SMALLINT UNSIGNED")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("INT UNSIGNED") || sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint") || sqlType.equalsIgnoreCase("BIGINT UNSIGNED")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("blob") || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("longblob")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("time")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Timestamp";
        } else if (sqlType.equalsIgnoreCase("double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "Clob";
        }
        return null;  //不能匹配情况下直接返回null，到时候需要自己优化
    }

    /**
     * 生成toString方法 双引号转义 \"
     *
     * @param sbf
     */
    public void processtoString(StringBuffer sbf) {
        sbf.append("\t@Override\n");
        sbf.append("\tpublic String toString(){\n");
        sbf.append("\t\t");
        sbf.append("return \" " + entityName + " [");
        for (int i = 0; i < colnames.length; i++) {
            if (i > 0) {   // 不是第一个属性追加 ",
                sbf.append(" \" , ");
            }
            sbf.append(colnames[i] + "=\" " + "+ " + colnames[i] + "+");
        }
        sbf.append("\"]\"");
        sbf.append(";");
        sbf.append("\n\t}\n\n");
    }

    /**
     * 生成无参构造函数
     *
     * @param sbf
     */
    public void processNullConstructor(StringBuffer sbf) {
        sbf.append("\tpublic " + entityName + " (){\n");
        sbf.append("\n\t}\n\n");
    }

}
