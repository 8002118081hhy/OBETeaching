<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>编辑主观毕业要求</title>
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
      id="saveSubjectivityForm"
      onsubmit="return false;">
    <input type="hidden" value="${subjectivity.id}" name="id">
    <div class="layui-form-item ">
        <label class="layui-form-label">所属毕业要求</label>
        <div class="layui-input-block">
            <select name="bigid">
                <c:forEach items="${smalls}" var="s">
                    <option value="${s.id}" ${s.id==subjectivity.bigid?'selected':''}>${s.big.name}-${s.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <select name="types">
                <option value="1" ${subjectivity.types==1?'selected':''}>应届生毕业要求</option>
                <option value="2" ${subjectivity.types==2?'selected':''}>用人单位毕业要求</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">非常满意</label>
        <div class="layui-input-block">
            <input type="text" id="a1" name="a1" lay-verify="required"
                   placeholder="请输入非常满意"
                   value="${subjectivity.a1}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">满意</label>
        <div class="layui-input-block">
            <input type="text" id="a2" name="a2" lay-verify="required"
                   placeholder="请输入满意"
                   value="${subjectivity.a2}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">一般</label>
        <div class="layui-input-block">
            <input type="text" id="a3" name="a3" lay-verify="required"
                   placeholder="请输入一般"
                   value="${subjectivity.a3}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">不满意</label>
        <div class="layui-input-block">
            <input type="text" id="a4" name="a4" lay-verify="required"
                   placeholder="请输入不满意"
                   value="${subjectivity.a4}" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">非常不满意</label>
        <div class="layui-input-block">
            <input type="text" id="a5" name="a5" lay-verify="required"
                   placeholder="请输入非常不满意"
                   value="${subjectivity.a5}" class="layui-input"/>
        </div>
    </div>
    <input name="studentid" value="${studentId==null?subjectivity.studentid:studentId}" type="hidden">



    <div class="layui-form-item">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="saveSubjectivity">立即保存</button>
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
        form.on("submit(saveSubjectivity)", function (data) {
            //弹出loading
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            var msg;
            var subURL = ctx + "/manage/saveSubjectivity";//添加提交地址
            if (!isEmpty(data.field.id)) { //判断是添加还是修改
                subURL = ctx + "/manage/updateSubjectivity";//修改提交地址
            }
            $.ajax({
                type: "post",
                url: subURL,
                data: data.field,
                dataType: "json",
                success: function (d) {
                    if (d.code == 0) {
                        msg = d.msg;
                        // 重置表单 saveSubjectivityForm是表单的id
                        //$("#saveSubjectivityForm")[0].reset();
                        //layui.form.render();
                        layer.msg(msg, {time: 3000, icon: 1}, function () {
                            var url = ctx + "/manage/subjectivityList"; //返回列表页面
                            if('${studentId}'){
                                url = ctx + "/manage/subjectivityList?id=${studentId}";
                            }
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