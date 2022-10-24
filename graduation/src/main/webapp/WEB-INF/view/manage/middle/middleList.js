layui.config({
    base: "js/"}).use(['form', 'layer', 'jquery', 'laypage', 'table', 'laytpl'], function () {
    var form = layui.form, table = layui.table;
    layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;


    let server='http://localhost:8087/';
    /*
    * 获取部门列表
    * */
    //声明一个方法
    function quaryClazzs(server){
        let clazzId='0';
        //ajax提交
        $.ajax({
            type:"get",
            url:ctx+"clazz/list",
            dataType:"json",
            async:false,
            success:function(response){
                window.console.log(response);
                switch(response){
                    case '200'://如果值可以匹配上
                        //index是clazz中的每一个对象
                        $.each(reponse.data,function(index,clazz){
                            if(index==0){
                                //index==0表示第一个对象
                                clazzId=clazz.id;
                                $('#deptlist').append('<li class="layui-nav-item layui-this"><a href="">'+clazz.name+'</a></li>');
                            }else{
                                //将clazz.name在左侧出现,layui-this表示默认选中
                                $('#deptlist').append('<li class="layui-nav-item"><a href="">'+clazz.name+'</a></li>');
                            }
                        })
                        break;
                    default:
                        break;
                }
            }
        });
        return clazzId;
    }
    //获取班级列表中第一个班级的编号
    let clazzId=quaryClazzs(server); //调用



    //搜索功能实现
    active = {
        search: function () {
            var keyword = $('#keyword')
            table.reload(  //执行重载
                'middleList',  //此处填写重载table.reload的id属性
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


//数据表格1
    table.render({
        id: 'middleList',
        elem: '#middleList'
        , url: ctx + '/manage/queryMiddleList' //数据接口
        ,where:{
          courseId:courseId
        }
        , cellMinWidth: 80
        , limit: 10//每页默认数
        , limits: [10, 20, 30, 40]
        , cols: [[ //表头
            {type: 'checkbox'},
            {field: 'id', title: 'ID', width: 60, align: 'center', sort: true},
            {field : 'name',title : '课程目标名称',width : 140,align : 'center'},
            {field : 'smallid',title : '对应毕业要求',width : 140,align : 'center',
                templet:function (d) {
                    return d.small ? d.small.big.name + "-" + d.small.name : '';
                }
            },
            //{field : 'w',title : '课程目标w值',width : 140,align : 'center'},
            {field : 'middleresult',title : '课程目标达成度',width : 140,align : 'center'},
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
                    url: ctx + '/manage/deleteMiddle?id=' + data.id,
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('middleList', {})
                        } else {
                            layer.msg("操作失败，请重试", {icon: 5});
                        }
                    }
                })
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var url = ctx + "/manage/editMiddle?id=" + data.id+"&courseId="+courseId;//路径拼接
            location.href = url;
        } else if (obj.event === 'infoDetail') {
            var url = ctx + "/manage/middleInfo?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "课程目标详情查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['500px', '400px'], //宽高
                shadeClose: true,
                content: url
            });
        } else if(obj.event === 'infoSubjectivity'){
            var url = ctx + "/manage/assessList?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "考核方案查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['1100px', '800px'], //宽高
                shadeClose: true,
                content: url
            });
        }

    });

    //一门课程达成情况
    $(".achiveInfo").click(function () {
        var url = ctx + "/manage/addMiddle1?courseId="+courseId;
        location.href = url;  //路径跳转
    })


//添加课程目标
    $(".addBtn").click(function () {
        var url = ctx + "/manage/addMiddle?courseId="+courseId;
        location.href = url;  //路径跳转
    })


//批量删除课程目标
    $(".batchDel").click(function () {
        var checkStatus = table.checkStatus('middleList')
            , data = checkStatus.data, idsStr = '';
//   layer.alert(JSON.stringify(data));
        if (data.length > 0) {
            $.each(data, function (n, value) {
                idsStr += value.id + ',';
            });
            idsStr = idsStr.substring(0, idsStr.length - 1);
            layer.confirm('真的要删除<strong>' + data.length + '</strong>条数据吗？', function (index) {
//调用删除接口，使用ajax提交
                $.ajax({
                    url: 'deletesMiddle?idsStr=' + idsStr,//接口地址
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
//重载表格
                            table.reload('middleList', {})
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
