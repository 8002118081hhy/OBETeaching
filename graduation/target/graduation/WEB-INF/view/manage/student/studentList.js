layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage', 'table', 'laytpl'], function () {
    var form = layui.form, table = layui.table;
    layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //搜索功能实现
    active = {
        search: function () {
            var keyword = $('#keyword')
            table.reload(  //执行重载
                'studentList',  //此处填写重载table.reload的id属性
                {
                    page: {
                        curr: 1     //重新从第 1 页开始
                    },
                    where: {
                        keyword: keyword.val()
                    }
                });
        }
    };
    //查询按钮绑定查询事件
    $(".search_btn").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


//数据表格
    table.render({
        id: 'studentList',
        elem: '#studentList'
        , url: ctx + '/manage/queryStudentList' //数据接口

        , cellMinWidth: 80
        , limit: 10//每页默认数
        , limits: [10, 20, 30, 40]
        , cols: [[ //表头
            {type: 'checkbox'},
            {field: 'id', title: 'ID', width: 60, align: 'center', sort: true},
            {field : 'realname',title : '姓名',width : 140,align : 'center'},
            {field : 'gender',title : '性别',width : 140,align : 'center'},
            {field : 'birthday',title : '生日',width : 140,align : 'center'},
            {field : 'grade',title : '班级',width : 140,align : 'center'},

            {title: '操作', toolbar: '#barEdit'}
        ]]
        , page: true //开启分页
    });
//监听工具条
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行吗?', function (index) {
                $.ajax({
                    url: ctx + '/manage/deleteStudent?id=' + data.id,
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('studentList', {})
                        } else {
                            layer.msg("操作失败，请重试", {icon: 5});
                        }
                    }
                })
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var url = ctx + "/manage/editStudent?id=" + data.id;//路径拼接
            location.href = url;
        } else if (obj.event === 'infoDetail') {
            var url = ctx + "/manage/studentInfo?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "学生详情查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['500px', '400px'], //宽高
                shadeClose: true,
                content: url
            });
        } else if(obj.event === 'score'){
            var url = ctx + "/manage/scoreList?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "学生客观成绩查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['90%', '90%'], //宽高
                shadeClose: true,
                content: url
            });
        } else if(obj.event === 'sub'){//主观成绩查看
            console.log("主观成绩")
            var url = ctx + "/manage/subjectivityList?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "学生主观成绩查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['90%', '90%'], //宽高
                shadeClose: true,
                content: url
            });
        } else if(obj.event === 'find'){
            var url = ctx + "/manage/visual?studentId=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "学生毕业达标度查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['90%', '90%'], //宽高
                shadeClose: true,
                content: url
            });
        }

    });

    //数据表格
    table.render({
        id: 'scoreList',
        elem: '#scoreList'// elme用来绑定容器的id属性
        , url: ctx + '/manage/queryScoreList' //数据接口
        ,where:{
            studentId:studentId
        }
        , cellMinWidth: 80
        , limit: 10//每页默认数
        , limits: [10, 20, 30, 40]
        , cols: [[ //表头
            {type: 'checkbox'},
            {field: 'id', title: 'ID', width: 60, align: 'center', sort: true},
            {field : 'assessid',title : '考核方案',width : 600,align : 'center',
                templet:function (d) {
                    //工程活动1.1 软件工程  课程目标1  平时成绩   软件工程
                    return d.assess ? d.assess.middle.small.big.name+ "-" + d.assess.middle.small.name + "-" + d.assess.middle.course.name  + "-" + d.assess.middle.name + "-" + d.assess.name +"(权重:"+d.assess.weight+")" : '';
                }
            },
            {field : 'score',title : '得分',width : 140,align : 'center'},
            {title: '操作', toolbar: '#barEdit'}
        ]]
        , page: true //开启分页
    });

//添加学生
    $(".addBtn").click(function () {
        var url = ctx + "/manage/addStudent";
        location.href = url;  //路径跳转
    })


//批量删除学生
    $(".batchDel").click(function () {
        var checkStatus = table.checkStatus('studentList')
            , data = checkStatus.data, idsStr = '';
//   layer.alert(JSON.stringify(data));
        if (data.length > 0) {
            $.each(data, function (n, value) {
                idsStr += value.id + ',';
            });
            idsStr = idsStr.substring(0, idsStr.length - 1);
            layer.confirm('真的要删除<strong>' + data.length + '</strong>条数据吗？', function (index) {
//调用删除接口
                $.ajax({
                    url: 'deletesStudent?idsStr=' + idsStr,//接口地址
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
//重载表格
                            table.reload('studentList', {})
                        } else {
                            layer.msg("删除错误，稍后再试！", {icon: 5});
                        }
                    }
                })
                layer.close(index);
            });
        } else {
            layer.msg("请选择要操作的数据！");
        }

    })

})
