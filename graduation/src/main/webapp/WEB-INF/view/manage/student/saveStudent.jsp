<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>编辑学生</title>
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
      id="saveStudentForm"
      onsubmit="return false;">
    <input type="hidden" value="${student.id}" name="id">
    <div class="layui-form-item ">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" id="realname" name="realname" lay-verify="required"
                   placeholder="请输入姓名"
                   value="${student.realname}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <select name="gender">
                <option value="男" ${student.gender=='男'?'selected':''}>男</option>
                <option value="女" ${student.gender=='女'?'selected':''}>女</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">生日</label>
        <div class="layui-input-block">
            <input type="text" id="birthday" name="birthday" lay-verify="required"
                   placeholder="请输入生日"
                   value="${student.birthday}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <input type="text" id="grade" name="grade" lay-verify="required"
                   placeholder="请输入班级"
                   value="${student.grade}" class="layui-input"/>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="saveStudent">立即保存</button>
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


        laydate.render({
            elem: '#birthday' //指定元素  元素选择器
            , type: 'date'  //选择时间类型 可选值:year(年) month(年月)  date(年月日)  time(时分秒)  datetime(年月日时分秒)
            , format: 'yyyy-MM-dd'  //时间格式  常用时间格式:yyyy-MM-dd HH:mm:ss
            , range: false  //是否开始左侧选择  为true时可以左右选择时间
            , value: new Date() //初始值 今天
            //, min: 0 //几天前或者指定日期'2018-04-28 12:30:00'
            //, max: 1 //几天后或者指定日期'2017-04-29 12:30:00'
            , btns: ['clear', 'now', 'confirm'] //选择框右下角显示的按钮 清除-现在-确定
            ,done: function(value, date){//时间回调
                console.log(value);
                console.log(date);
            }
        });
        //表单提交
        form.on("submit(saveStudent)", function (data) {
            //弹出loading
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            var msg;
            var subURL = ctx + "/manage/saveStudent";//添加提交地址
            if (!isEmpty(data.field.id)) { //判断是添加还是修改
                subURL = ctx + "/manage/updateStudent";//修改提交地址
            }
            $.ajax({
                type: "post",
                url: subURL,
                data: data.field,
                dataType: "json",
                success: function (d) {
                    if (d.code == 0) {
                        msg = d.msg;
                        // 重置表单 saveStudentForm是表单的id
                        //$("#saveStudentForm")[0].reset();
                        //layui.form.render();
                        layer.msg(msg, {time: 3000, icon: 1}, function () {
                            var url = ctx + "/manage/studentList"; //返回列表页面
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