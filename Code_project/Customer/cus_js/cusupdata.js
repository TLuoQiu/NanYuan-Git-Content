//Demo
layui.use(['form', 'laydate', 'jquery'], function () {
    var form = layui.form;

    //从父页面获取数据
    const data = parent.dataRow;

    var $ = layui.jquery;

    form.val("formTest", data); //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值

    //选择日期与时间
    var laydate = layui.laydate;
    //日期时间选择器
    laydate.render({
        elem: '#datatime',
        type: 'datetime'
    });

    //监听提交
    form.on('submit(formDemo)', function (data) {
        // layer.msg(JSON.stringify(data.field));

        //ajax提交异步请求
        $.ajax({
            type: "POST", //请求方式
            url: "http://localhost:8080/customer/updateCus", //请求后端的url
            data: data.field, //传输到后端的数据
            dataType: "JSON",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.msg);
                    //操作成功返回主页面
                    // parent.location.reload();
                    // console.log(res.msg);
                    //时间延迟函数，第一个参数是要晚一点执行的代码函数，第二个参数是要延迟的时间（单位毫秒）
                    setTimeout(() => {
                        //  //当你在iframe页面关闭自身时
                        //  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //  parent.layer.close(index);
                        // 关闭页面，刷新数据
                        parent.location.reload();
                        //再执行关闭
                    }, 1200)

                } else {
                    //否则返回错误信息
                    layer.msg(res.msg);
                }
            }
        });
        return false;
    });
});