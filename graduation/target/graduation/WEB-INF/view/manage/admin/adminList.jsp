<%--管理员列表页面--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <%--引入layui的核心css文件--%>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/css/font_eolqem241z66flxr.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/css/list.css" media="all"/>
    <script>
        <%--JS gloable varilible--%>
        var ctx = "${ctx}";
    </script>
    <style type="text/css">
    </style>
</head>
<body class="childrenBody">
<blockquote class="laytui-elem-quote lis_search">
    <div class="layui-inline">
        <%--i标签表示可以添加图标--%>
        <a class="layui-btn layui-btn-normal adminAdd_btn"><i class="layui-icon">&#xe608;</i> 添加用户</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-danger batchDel"><i class="layui-icon">&#xe640;</i>批量删除</a>
    </div>
    <div class="layui-inline">
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
</blockquote>
<!-- 数据表格 -->
<table id="adminList" lay-filter="test"></table>
<%--引入layui.js文件--%>
<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/view/manage/admin/adminList.js"></script>
<script type="text/html" id="barEdit">
    <%--layui-btn:按钮样式
        layui-btn-xs:按钮大小为迷你
        layui-danger:警告按钮--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="statusTpl">
    {{#  if(d.status == '0'){ }}
    <span style="color: green;">可用</span>
    {{#  } else{ }}
    <span style="color: red;">禁用</span>
    {{#  } }}
</script>

<style>
    .layui-table-cell {
        height: 36px;
        line-height: 36px;
    }
</style>
</body>
</html>