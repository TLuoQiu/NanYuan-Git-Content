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
        limit: 10,
        limits: [5, 10, 15, 20],
        url: 'http://localhost:8080/customer/getCus', //数据接口
        // page: true ,//开启分页
        cols: [
            [{
                type: 'checkbox',
                fixed: 'left'
            },
            //表头
            {
                field: 'cus_id',
                title: '编号',
                width: 80,
                sort: true,
                fixed: 'left'
            }, {
                field: 'cus_name',
                title: '客户名称',
                width: 110
            }, {
                field: 'cus_region',
                title: '客户地区',
                width: 110,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_region) {
                        case 1:
                            res = '东北';
                            break;
                        case 2:
                            res = '华北';
                            break;
                        case 3:
                            res = '西北';
                            break;
                        case 4:
                            res = '西南';
                            break;
                        case 5:
                            res = '华南';
                            break;
                        case 6:
                            res = '华中';
                            break;
                        case 7:
                            res = '华东';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_industry',
                title: '客户行业',
                width: 110,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_industry) {
                        case 1:
                            res = '金融';
                            break;
                        case 2:
                            res = '房地产';
                            break;
                        case 3:
                            res = '商业服务';
                            break;
                        case 4:
                            res = '物流运输';
                            break;
                        case 5:
                            res = '生产';
                            break;
                        case 6:
                            res = '政府';
                            break;
                        case 7:
                            res = '文化传媒';
                            break;
                        case 8:
                            res = '其他';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_level',
                title: '客户等级',
                width: 110,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_level) {
                        case 'A':
                            res = '重点客户';
                            break;
                        case 'B':
                            res = '普通客户';
                            break;
                        case 'C':
                            res = '非优先客户';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_satisfy',
                title: '客户满意度',
                width: 115,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_satisfy) {
                        case 1:
                            res = '一级';
                            break;
                        case 2:
                            res = '二级';
                            break;
                        case 3:
                            res = '三级';
                            break;
                        case 4:
                            res = '四级';
                            break;
                        case 5:
                            res = '五级';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_credit',
                title: '客户信用度',
                width: 115,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_credit) {
                        case 1:
                            res = '一级';
                            break;
                        case 2:
                            res = '二级';
                            break;
                        case 3:
                            res = '三级';
                            break;
                        case 4:
                            res = '四级';
                            break;
                        case 5:
                            res = '五级';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_addr',
                title: '客户地址',
                width: 110
            }, {
                field: 'cus_zipcode',
                title: '客户邮编',
                width: 110,
                sort: true
            }, {
                field: 'cus_phone',
                title: '客户电话',
                width: 116,
                sort: true
            }, {
                field: 'cus_fax',
                title: '客户传真',
                width: 110,
                sort: true
            }, {
                field: 'cus_url',
                title: '客户网址',
                width: 110,
                sort: true
            }, {
                field: 'user_id',
                title: '客户经理id',
                width: 115,
                sort: true
            }, {
                field: 'cus_status',
                title: '客户状态',
                width: 110,
                sort: true,
                templet: function (d) {
                    // d这个参数含有一行数据
                    let res = null;
                    switch (d.cus_status) {
                        case 0:
                            res = '流失';
                            break;
                        case 1:
                            res = '正常';
                            break;
                    }
                    return res;
                }
            }, {
                field: 'cus_createtime',
                title: '创建时间',
                width: 180,
                sort: true
            }, {
                fixed: 'right',
                title: '操作',
                toolbar: '#barDemo',
                width: 120
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
        if (obj.event === 'del') {
            layer.confirm('确定要删除吗', function (index) {
                // obj.del();
                //向服务端发送删除指令
                // 利用ajax发送删除请求

                $.ajax({
                    type: "POST", //请求方式
                    url: "http://localhost:8080/customer/deleteCus", //请求后端的url
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
                        } else {
                            //否则返回错误信息
                            layer.msg(res.msg);
                        }
                    }
                });

                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2,
                content: 'cusupdate.html',
                area: ['90%', '70%'],
                title: '修改客户'
            });
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
        $.each(dataList, function (data) {

            id_list.push(dataList[data].sc_id);
        })
        // const obj_data = Object.assign({}, id_list);
        // console.log(obj_data);
        var ids = "";
        for (var i = 0; i < checkStatus.data.length; i++) {
            ids += checkStatus.data[i].sc_id + ",";
        }
        switch (obj.event) {
            case 'add':
                // layer.msg('添加');
                layer.open({
                    type: 2,
                    content: 'insertcus.html',
                    area: ['90%', '70%'],
                    title: '添加客户'
                });
                break;
            case 'update':
                if (dataList.length === 0) {
                    layer.msg('请选择一行');
                } else if (dataList.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    // layer.alert('编辑 [id]:' + checkStatus.data[0].cus_id);
                    layer.open({
                        type: 2,
                        content: 'cusupdate.html',
                        area: ['90%', '70%'],
                        title: '修改客户'
                    });
                }
                break;
            case 'delete':
                if (id_list.length <= 0) {
                    layer.msg('请选择要删除的行', { icon: 5 })
                } else {
                    layer.confirm('真的要删除吗', function (index) {
                        layer.alert("向服务器发送删除指令")
                        $.ajax({
                            url: 'http://localhost:8080/customer/deleteCusList',
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