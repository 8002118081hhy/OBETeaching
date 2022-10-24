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
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all"/>
    <script>
        var ctx = "${ctx}";  //全局js项目路径
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
        table{
            width:96%;
            margin-left:2%;
            margin-right:2%;
            margin-top:20px;
            text-align: center;
        }
        .header{
            background-color: #1E9FFF;
        }
        .odd{
            background-color: #6FA5DB;
        }
        .even{
            background-color: #1E9FFF;
        }
        td {
            padding-bottom: 4px;
            padding-top: 4px;
        }
    </style>
</head>
<body class="childrenBody">
<table border="1" layer-filter="demo">
    <c:forEach items="${list}" var="theads" varStatus="s">
        <tr class="${s.index<2?'header':(s.index%2==0?'odd':'even')}">
            <c:forEach items="${theads}" var="t">
                <td rowspan="${t.rowspan}" colspan="${t.colspan}">${t.content==null?'':t.content}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
<script type="text/javascript">
    var $;
    var $form;
    var form;
    layui.config({
        base: "js/"
    }).use(['table'], function () {

    });
</script>
</body>
</html>