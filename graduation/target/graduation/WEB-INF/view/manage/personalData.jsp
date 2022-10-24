<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息显示</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/static/layui/css/layui.css" media="all"/>
    <script>
        <%--JS gloable varilible--%>
        var ctx = "${ctx}";
    </script>
    <style type="text/css">
        .layui-form-item .layui-inline {
            width: 33.333%;
            float: left;
            margin-right: 0;
        }

        @media ( max-width: 1240px) {
            .layui-form-item .layui-inline {
                width: 100%;
                float: none;
            }
        }
    </style>
</head>
<body class="childrenBody">
<div class="layui-form" style="width:80%; margin: 50px auto;">
    <table class="layui-table">
        <thead>
        <tr>
            <th colspan="2" style="text-align: center;">个人信息展示</th>
        </tr>
        </thead>
        <tbody>

        <tr>
            <td>用户名</td>
            <td>${role=='管理员'?userInfo.adminname:userInfo.username}</td>
        </tr>

        <tr>
            <td>邮箱</td>
            <td>${role=='管理员'?userInfo.admineamil:userInfo.admineamil}</td>
        </tr>

        <tr>
            <td>电话</td>
            <td>${role=='管理员'?userInfo.adminphone:userInfo.adminphone}</td>
        </tr>

        <tr>
            <td>状态</td>
            <td>${role=='管理员'?userInfo.status:userInfo.status}</td>
        </tr>

        <tr>
            <td>角色</td>
            <td>${role=='管理员'?userInfo.role:userInfo.role}</td>
        </tr>

        </tbody>
    </table>
    <h1 style="text-align: center;">欢迎${sessionScope.loginuserName}登录-OBE教学目标管理系统！</h1>
</div>
<script type="text/javascript" src="${ctx }/static/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/view/manage/admin/saveAdmin.js"></script>
</body>
</html>