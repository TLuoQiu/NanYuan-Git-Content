//定义全局变量，用来存储点击行的数据
var dataRow = null;



layui.use(['table', 'jquery'], function () {
    var table = layui.table;

    var $ = layui.jquery;
    //第一个实例
    table.render({
        elem: '#demo',
        height: 600,
        toolbar: 'default', //开启头部工具栏，并为其绑定左侧模板
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页   
            groups: 1, //只显示 1 个连续页码  
            first: false, //不显示首页   
            last: false //不显示尾页
        },
        limit: 5,
        limits: [5, 10, 15, 20],
        url: 'http://localhost:8080/saleChance/getSearchPageSale', //数据接口
        // page: true ,//开启分页
        cols: [
            [{
                type: 'checkbox',
                fixed: 'left'
            },
            //表头
            {
                field: 'sc_id',
                title: '编号',
                width: 80,
                sort: true,
                fixed: 'left'
            }, {
                field: 'sc_cusname',
                title: '客户名称',
                width: 110
            }, {
                field: 'sc_comming',
                title: '机会来源',
                width: 110,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.sc_comming) {
                        case 0:
                            res = '促销';
                            break;
                        case 1:
                            res = '广告';
                            break;
                        case 2:
                            res = '搜索引擎';
                            break;
                        case 3:
                            res = '线上咨询';
                            break;
                        case 4:
                            res = '电话咨询';
                            break;
                        case 5:
                            res = '邮件咨询';
                            break;
                        case 6:
                            res = '客户介绍';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'sc_name',
                title: '联系人',
                width: 110,
            }, {
                field: 'sc_phone',
                title: '联系电话',
                width: 110,
            }, {
                field: 'sc_success',
                title: '成功机率',
                width: 115,
                sort: true,
            }, {
                field: 'sc_message',
                title: '概要',
                width: 115,
                sort: true,
            }, {
                field: 'sc_desc',
                title: '机会描述',
                width: 110
            }, {
                field: 'sc_createuserid',
                title: '创建人',
                width: 110,
                sort: true
            }, {
                field: 'sc_createtime',
                title: '创建时间',
                width: 116,
                sort: true
            }, {
                field: 'sc_giveuserid',
                title: '被指派人',
                width: 110,
                sort: true
            }, {
                field: 'sc_givetime',
                title: '指派时间',
                width: 110,
                sort: true
            }, {
                field: 'sc_status',
                title: '状态',
                width: 115,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.sc_status) {
                        case 0:
                            res = '未分配';
                            break;
                        case 1:
                            res = '已分配';
                            break;
                        case 2:
                            res = '开发成功';
                            break;
                        case 3:
                            res = '开发失败';
                            break;
                        case 4:
                            res = '开发中';
                            break;
                    }
                    return res;
                }
            }, {
                fixed: 'right',
                title: '操作',
                toolbar: '#barDemo',
                width: 165
            }
            ]
        ]
    });
    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        // console.log(obj)
        //将选中行的数据赋值给dataRow
        dataRow = data;
        // 获取登录用户的u_id
        var u_id = window.sessionStorage.getItem("u_id");
        var r_id = window.sessionStorage.getItem("r_id");
        console.log(u_id);
        console.log(data.sc_createuserid);
        if (obj.event === 'del') {
            if (data.sc_createuserid == u_id && data.sc_status == 0) {
                layer.confirm('确定要删除吗', function (index) {
                    // 向服务端发送删除指令
                    // 利用ajax发送删除请求
                    $.ajax({
                        type: "POST", //请求方式
                        url: "http://localhost:8080/saleChance/deleteSale", //请求后端的url
                        data: data, //传输到后端的数据cus_id,定义格式为json
                        dataType: "JSON",
                        success: function (res) {
                            if (res.code == 200) {
                                layer.msg(res.msg);
                                // 刷新页面
                                // parent.location.reload();
                                //刷新页面
                                table.reload('demo', {
                                    where: {
                                    }
                                    , page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                }); //只重载数据表
                            }
                        }
                    });
                    layer.close(index);
                });
            } else if (data.sc_createuserid == u_id) {
                layer.msg('只能删除未分配的数据');
            } else {
                layer.msg('您不能删除其他人的数据');
            }
            // layer.confirm('确定要删除吗', function (index) {

            // obj.del();
            //向服务端发送删除指令
            // 利用ajax发送删除请求

            // $.ajax({
            //     type: "POST", //请求方式
            //     url: "http://localhost:8080/saleChance/deleteSale", //请求后端的url
            //     data: data, //传输到后端的数据cus_id,定义格式为json
            //     dataType: "JSON",
            //     success: function (res) {
            //         if (res.code == 200) {
            //             layer.msg(res.msg);
            //             // 刷新页面
            //             // parent.location.reload();
            //             //刷新页面
            //             table.reload('demo', {
            //                 where: {
            //                 }
            //                 , page: {
            //                     curr: 1 //重新从第 1 页开始
            //                 }
            //             }); //只重载数据表
            //         } else {
            //             //否则返回错误信息
            //             layer.msg(res.msg);
            //         }
            //     }
            // });

            //     layer.close(index);
            // });
        } else if (obj.event === 'edit') {
            // 获取到一行数据中的值，判断是否可以修改
            if (data.sc_status != 0) {
                layer.msg('只能编辑未分配的数据');
            } else {
                layer.open({
                    type: 2,
                    content: 'saleupdate.html',
                    area: ['90%', '70%'],
                    title: '修改销售信息'
                });
            }
        } else if (obj.event === 'assign') {
            // 获取到一行数据中的值，判断是否可以修改
            if (r_id != 1) {
                layer.msg('你不是销售主管,无法进行分配');
            } else if (data.sc_status != 0) {
                layer.msg('该销售机会已经被分配');
            } else {
                layer.open({
                    type: 2,
                    content: 'saleassign.html',
                    area: ['90%', '70%'],
                    title: '分配销售信息'
                });
            }
        }
    });
    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {

        //定义用户编号列表
        var id_list = [];
        var checkStatus = table.checkStatus(obj.config.id),
            dataList = checkStatus.data; //获取选中的数据
        //将data的Array数组的第一个对象赋值给dataRow
        dataRow = dataList[0];
        // console.log(dataRow);
        //进行遍历所有选中行数据，拿出每一行的id存储道数据中
        $.each(dataList, function (data) {

            id_list.push(dataList[data].sc_id);
        })
        var ids = "";
        for (var i = 0; i < checkStatus.data.length; i++) {
            ids += checkStatus.data[i].sc_id + ",";
        }
        console.log(ids);

        switch (obj.event) {
            case 'add':
                // layer.msg('添加');
                layer.open({
                    type: 2,
                    content: 'insertsale.html',
                    area: ['90%', '70%'],
                    title: '添加销售信息'
                });
                break;
            case 'update':
                if (dataList.length === 0) {
                    layer.msg('请选择一行');
                } else if (dataList.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else if (dataList[0].sc_status) {
                    layer.msg('只能编辑未分配的数据');
                } else {
                    layer.open({
                        type: 2,
                        content: 'saleupdate.html',
                        area: ['90%', '70%'],
                        title: '修改销售信息'
                    });
                }
                // console.log(dataList);
                // // layer.alert('编辑 [id]:' + checkStatus.data[0].cus_id);
                // layer.open({
                //     type: 2,
                //     content: 'saleupdate.html',
                //     area: ['90%', '70%'],
                //     title: '修改销售信息'
                // });
                break;
            case 'delete':
                if (id_list.length <= 0) {
                    layer.msg('请选择要删除的行', { icon: 5 })
                } else {
                    layer.confirm('真的要删除吗', function (index) {
                        layer.alert("向服务器发送删除指令")
                        $.ajax({
                            url: 'http://localhost:8080/saleChance/deleteSaleList',
                            type: 'POST',
                            datatype: 'json',
                            data: {
                                'ids': ids
                            },
                            success: function (res) {
                                if (res == "success") {
                                    console.log(res);
                                    layer.msg('批量删除成功');
                                    table.reload('demo', {
                                        where: {
                                        }
                                        , page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    }); //只重载数据表
                                } else {
                                    layer.msg('批量删除失败');
                                }
                            }
                        })
                        console.log(id_list);
                        layer.close(index);
                    })
                }
                break;
        };
    });

});