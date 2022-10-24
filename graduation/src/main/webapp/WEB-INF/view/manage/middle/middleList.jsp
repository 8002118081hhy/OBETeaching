<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>课程目标列表</title>
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
        var courseId = '${courseId}'
    </script>
    <style type="text/css">
    </style>
    <script src="${ctx}/view/manage/echarts.js"></script>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote list_search">
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal achiveInfo"><i class="layui-icon">&#xe62c;</i> 课程达成情况</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal addBtn"><i class="layui-icon">&#xe608;</i> 添加课程目标</a>
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
<table id="middleList" lay-filter="test"></table>

<div id="xxxmy" style="width: 500px;width: 600px;margin-left: 10px"></div>


<%--//我添加的表格
<table id="main" lay-filter="test"></table>
<div id="main1" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '课程目标达成情况' //设置图表标题
        },
        //color设置图形颜色,注意后面是个数组
        color:['pink','red','green'],
        //图表提示框组件
        tooltip: {
            trigger:'item'
        },
        //图例组件
        legend: {
            //series里面有了name值，则legend里面的data可以删掉
            //data: ['销量']
        },
        //工具箱组件，可以另存为图片等功能
        toolbox:{
            feature:{
                saveAsImage:{}
            }
        },
        //网格配置，grid可以控制柱状图的图表大小
        grid:{
            left:'3%',
            right:'4%',
            bottom:'3%',
            //是否显示刻度标签，如果是true就显示，否则不显示
            containLabel:true
        },
        //设置X轴相关配置
        xAxis: {
            type:'category',
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
        },
        yAxis: {
            type:'value',
        },
        //系列图表配置，决定显示那种类型的图表
        //每一个{}决定一个柱条
        series: [
            {
                name: '销量',
                type: 'bar', //柱形图
                data: [5, 20, 36, 10, 10, 20]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>--%>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script type="text/javascript" src="${ctx}/static/js/common.js"></script>

<script type="text/javascript" src="${ctx}/view/manage/middle/middleList.js"></script>
<script type="text/html" id="barEdit">
    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="infoDetail">查看</a>
    <a class="layui-btn layui-btn-warm  layui-btn-xs" lay-event="infoSubjectivity">查看考核方案</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<style>
    .layui-table-cell {
        height: 36px;
        line-height: 36px;
    }
</style>
</body>
</html>