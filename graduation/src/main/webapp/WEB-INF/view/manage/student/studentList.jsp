<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>学生列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/css/font_eolqem241z66flxr.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/css/list.css" media="all"/>
    <script>
        var ctx = "${ctx}"; //js全局项目路径
    </script>
    <style type="text/css">
    </style>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote list_search">
    <%--layui-inline:不独占一行，多个相邻元素排在一行，直到一行
    排不下才会换一行，其宽度随元素的内容而变化
    addBtn:js响应--%>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal addBtn"><i class="layui-icon">&#xe608;</i> 添加学生</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-danger batchDel"><i class="layui-icon">&#xe640;</i>批量删除</a>
    </div>
    <!--搜索表单开始-->
    <div class="layui-inline layui-layout-right">
        <form class="layui-form">
            <div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" id="keyword" name="keyword" value="" placeholder="请输入关键字"
                               class="layui-input search_input">
                    </div>
                    <a class="layui-btn search_btn" lay-submit="" data-type="search"
                       lay-filter="search">查询</a>
                </div>
            </div>
        </form>
    </div>
    <!--搜索表单结束-->
    <div class="layui-inline">
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
</blockquote>
<!-- 数据表格 -->
<%--lay-event:按键监听事件--%>
<table id="studentList" lay-filter="test"></table>
<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/view/manage/student/studentList.js"></script>
<script type="text/html" id="barEdit">
    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="infoDetail">查看</a>

    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="score">客观成绩</a>
<%--    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="sub">主观成绩</a>--%>
   <%-- <a class="layui-btn layui-btn-xs" lay-event="find">查看毕业达标度</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <table id="scoreList" lay-filter="test"></table>
</script>
<style>
    .layui-table-cell {
        height: 36px;
        line-height: 36px;
    }
</style>
</body>
</html>