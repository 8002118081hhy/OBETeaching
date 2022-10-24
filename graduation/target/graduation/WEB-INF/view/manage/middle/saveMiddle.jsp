<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>编辑课程目标</title>
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
    </style>
</head>
<body class="childrenBody">
<form class="layui-form layui-form-pane" action="" style="width:80%;margin:0 auto;margin-top: 2%;"
      id="saveMiddleForm"
      onsubmit="return false;">
    <input type="hidden" value="${middle.id}" name="id">
    <div class="layui-form-item ">
        <label class="layui-form-label">目标名称</label>
        <div class="layui-input-block">
            <input type="text" id="name" name="name" lay-verify="required"
                   placeholder="请输入课程目标名称"
                   value="${middle.name}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">毕业要求</label>
        <div class="layui-input-block">
            <select name="smallid">
                <c:forEach items="${bigs}" var="b">
                    <c:forEach items="${b.smalls}" var="s">
                        <option value="${s.id}" ${s.id==middle.smallid?'selected':''}>${b.name} - ${s.name}</option>
                    </c:forEach>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item " style="display: none">
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <input type="text" id="courseid" name="courseid" lay-verify="required"
                   placeholder="请输入课程"
                   value="${courseId!=null?courseId:middle.courseid}" class="layui-input"/>
        </div>
    </div>

    <div class="layui-form-item ">
        <label class="layui-form-label">目标支撑度</label>
        <div class="layui-input-block">
            <input type="text" id="w" name="w" lay-verify="required"
                   placeholder="请输入目标支撑度"
                   value="${middle.w}" class="layui-input"/>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="saveMiddle">立即保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button onclick="backPage()" class="layui-btn layui-btn-warm">返回</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript">
    var $;
    var $form;
    var form;
    layui.config({
        base: "js/"
    }).use(['form', 'layer', 'jquery', 'laypage', 'table', 'laytpl', 'laydate'], function () {
        var form = layui.form, table = layui.table;
        layer = parent.layer === undefined ? layui.layer : parent.layer,
            laypage = layui.laypage, laydate = layui.laydate,
            $ = layui.jquery;
        nowTime = new Date().valueOf();
        //表单提交
        form.on("submit(saveMiddle)", function (data) {
            //弹出loading
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            var msg;
            var subURL = ctx + "/manage/saveMiddle";//添加提交地址
            if (!isEmpty(data.field.id)) { //判断是添加还是修改
                subURL = ctx + "/manage/updateMiddle";//修改提交地址
            }
            $.ajax({
                type: "post",
                url: subURL,
                data: data.field,
                dataType: "json",
                success: function (d) {
                    if (d.code == 0) {
                        msg = d.msg;
                        // 重置表单 saveMiddleForm是表单的id
                        //$("#saveMiddleForm")[0].reset();
                        //layui.form.render();
                        layer.msg(msg, {time: 3000, icon: 1}, function () {
                            var url = ctx + "/manage/middleList"; //返回列表页面
                            if('${courseId}'!=''){
                                url = ctx + '/manage/middleList?id=${courseId}'
                            }
                            console.log(url)
                            window.location.href = url;
                        });
                    } else {
                        msg = d.msg;
                        layer.alert(msg);
                    }
                }
            });
            return false;
        })

    });
</script>
</body>
</html>