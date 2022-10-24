<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>编辑课程</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <script src="${ctx}/static/js/echarts.js"></script>
</head>
<body class="childrenBody">
<div id="myEcharts" style="width: 1200px;height: 800px;"></div>
<%--<div id="myEchart" style="width: 1200px;height: 800px;"></div>--%>
<script>
    <%--<c:forEach items="${visuals[1].other2}" var="b">
    "${b.courseid}",                </c:forEach>--%>
    <c:forEach items="${visuals}" var="visual">
    /*课程目标达成度使用echarts进行可视化*/
    var myEcharts = echarts.init(document.getElementById('myEcharts'))
    //var myEchart = echarts.init(document.getElementById('myEchart'))
    var option = {
        title: {
            text: '${visual.name}'+'课程目标达成情况',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a}<br>{b}:{c}' //{a}表示系列名称，就是series中对象的name的值   {b} 表示数据项名称  {c}表示数值
        },
        toolbox: { //工具栏组件
            show: true,
            feature: { //工具栏配置
                mark: { show: true },
                dataView: { //底层数据视图
                    show: true,
                    readOnly: false //是否只读
                },
                magicType: { //可视化视图转换
                    show: true,
                    type: ['line', 'bar'] // 折线图和柱状图进行转换
                },

                saveAsImage: { //将视图结果以图片形式保存到本地
                    show: true
                },
                restore: { //重置视图到初始设置
                    show: true
                }
            },
            orient: 'vertical', //布局朝向 horizontal 横着排列  vertical 竖着排列
            itemSize: 16, //工具栏图标大小
            itemGap: 10, //工具栏图标间隔
            showTitle: true, //是否在鼠标悬停的时候显示工具栏图标标题

        },
        xAxis:{
            type:'category',
            data:[
                <c:forEach items="${visual.other2}" var="b">
                "${b.smallid}",                </c:forEach>
            ]
        },
        yAxis:{
            type:'value'
        },
        //对每一块进行设置>对每一层进行设置>对整个区域进行设置
        //如果不设置，系统会给与默认样式，子元素的样式和父元素的样式相同
        series: [
            /*{
                name: '毕业完成度',
                type: 'bar',
                data: [
                    <%--<c:forEach items="${visual.other}" var="b">
                    ${b.value>b.value2?b.value2:b.value},
                    </c:forEach>--%>
                ]
            },*/
            //第一列
            {
                name: '毕业要求客观达成度',
                type: 'bar',
                data: [
                    <c:forEach items="${visual.other2}" var="b">
                    ${b.middleresult},
                    </c:forEach>
                ]
            },
            /*{
                name: '主观完成度',
                type: 'bar',
                data: [
                    <%--<c:forEach items="${visual.other}" var="b">
                        ${b.value2},
                    </c:forEach>--%>
                ]
            }*/
        ]
    }
    var option = {
        title: {
            text: '${visual.name}',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a}<br>{b}:{c}' //{a}表示系列名称，就是series中对象的name的值   {b} 表示数据项名称  {c}表示数值
        },
        toolbox: { //工具栏组件
            show: true,
            feature: { //工具栏配置
                mark: { show: true },
                dataView: { //底层数据视图
                    show: true,
                    readOnly: false //是否只读
                },
                magicType: { //可视化视图转换
                    show: true,
                    type: ['line', 'bar'] // 折线图和柱状图进行转换
                },

                saveAsImage: { //将视图结果以图片形式保存到本地
                    show: true
                },
                restore: { //重置视图到初始设置
                    show: true
                }
            },
            orient: 'vertical', //布局朝向 horizontal 横着排列  vertical 竖着排列
            itemSize: 16, //工具栏图标大小
            itemGap: 10, //工具栏图标间隔
            showTitle: true, //是否在鼠标悬停的时候显示工具栏图标标题

        },
        xAxis:{
            type:'category',
            data:[
                <c:forEach items="${visual.other2}" var="b">
                "${b.courseid}",
                </c:forEach>
            ]
        },
        yAxis:{
            type:'value'
        },
        //对每一块进行设置>对每一层进行设置>对整个区域进行设置
        //如果不设置，系统会给与默认样式，子元素的样式和父元素的样式相同
        series: [
            /*{
                name: '毕业完成度',
                type: 'bar',
                data: [
                    <%--<c:forEach items="${visual.other}" var="b">
                    ${b.value>b.value2?b.value2:b.value},
                    </c:forEach>--%>
                ]
            },*/
            //第一列
            {
                name: '毕业要求客观达成度',
                type: 'bar',
                data: [
                    <c:forEach items="${visual.other2}" var="b">
                    ${b.middleresult},
                    </c:forEach>
                ]
            },
            /*{
                name: '主观完成度',
                type: 'bar',
                data: [
                    <%--<c:forEach items="${visual.other}" var="b">
                        ${b.value2},
                    </c:forEach>--%>
                ]
            }*/
        ]
    }
    myEcharts.setOption(option)
    //myEchart.setOption(option)
    </c:forEach>
</script>

</body>
</html>