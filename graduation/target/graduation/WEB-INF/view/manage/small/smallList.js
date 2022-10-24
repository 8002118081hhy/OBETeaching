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
                'smallList',  //此处填写重载table.reload的id属性
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
        id: 'smallList',
        elem: '#smallList'
        , url: ctx + '/manage/querySmallList' //数据接口
        , cellMinWidth: 80
        , limit: 10//每页默认数
        , limits: [10, 20, 30, 40]
        , cols: [[ //表头
            {type: 'checkbox'},
            {field: 'id', title: 'ID', width: 60, align: 'center', sort: true},
            {field : 'name',title : '二级毕业要求编号',width : 240,align : 'center'},
            // {field : 'w',title : '小毕业要求w值',width : 140,align : 'center'},
            {field : 'bigid',title : '所属一级毕业要求',width : 240,align : 'center',
                templet:function (d) {
                    return d.big?d.big.name:''
                }
            },
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
                    url: ctx + '/manage/deleteSmall?id=' + data.id,
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
                            table.reload('smallList', {})
                        } else {
                            layer.msg("操作失败，请重试", {icon: 5});
                        }
                    }
                })
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var url = ctx + "/manage/editSmall?id=" + data.id;//路径拼接
            location.href = url;
        } else if (obj.event === 'infoDetail') {
            var url = ctx + "/manage/smallInfo?id=" + data.id;
            layer.open({
                type: 2,
                btn: ['关闭'],
                btnAlign: 'c',
                title: "二级毕业要求详情查看",
                skin: 'layui-layer-molv', //加上边框
                area: ['500px', '400px'], //宽高
                shadeClose: true,
                content: url
            });
        }

    });

//添加二级毕业要求
    $(".smallachive").click(function () {
        var url = ctx + "/manage/smallAchive";
        location.href = url;  //路径跳转
    })

    //添加二级毕业要求
    $(".addBtn").click(function () {
        var url = ctx + "/manage/addSmall";
        location.href = url;  //路径跳转
    })

    //二级毕业达成情况
    $(".smallAchive").click(function () {
        var url = ctx + "/manage/addSmall";
        location.href = url;  //路径跳转
    })


//批量删除二级毕业要求
    $(".batchDel").click(function () {
        var checkStatus = table.checkStatus('smallList')
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
                    url: 'deletesSmall?idsStr=' + idsStr,//接口地址
                    type: "get",
                    success: function (d) {
                        if (d.code == 0) {
//重载表格
                            table.reload('smallList', {})
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
