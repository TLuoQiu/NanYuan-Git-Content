package com.example.crmservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crmservice.pojo.Customer;
import com.example.crmservice.pojo.Report;
import com.example.crmservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    //自动注入对象
    final CustomerService customerService;

    /*

     */
    @RequestMapping(value = "/getCus", method = RequestMethod.GET)
    public Map<String, Object> getCustomer(Integer page, Integer limit) {

        IPage<Customer> customerIPage = customerService.pageCus(page, limit);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", customerIPage.getTotal());// customerIPage.getTotal(),查询出来的数据条数
        map.put("data", customerIPage.getRecords());// 查询出来的所有对象
        return map;
    }

    /*
     *处理前端发回的客户修改数据的请求，并返回成功与否的信息
     */
    @RequestMapping(value = "/updateCus", method = RequestMethod.POST)
    public Map<String, Object> updateCus(Customer customer) {

        // 1.用CustomerService的对象调用修改方法

        Boolean flag = customerService.updateCus(customer);
        // 2.设置返回的提示信息
        Map<String, Object> map = new HashMap<>();

        if (flag) {
            //返回成功状态码
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            //返回访问失败状态码
            map.put("code", 500);
            map.put("msg", "修改失败");
        }

        return map;
    }

    /*
     *处理前端发回的添加客户数据的请求，并返回成功与否的信息
     */
    @RequestMapping(value = "/insertCus", method = RequestMethod.POST)
    public Map<String, Object> insertCus(Customer customer) {

        // 1.用CustomerService的对象调用修改方法

        Boolean flag = customerService.insertCus(customer);
        // 2.设置返回的提示信息
        Map<String, Object> map = new HashMap<>();

        if (flag) {
            //返回成功状态码
            map.put("code", 200);
            map.put("msg", "添加成功");
        } else {
            //返回访问失败状态码
            map.put("code", 500);
            map.put("msg", "添加失败");
        }
        return map;
    }

    /*
     * 根据id删除客户数据，并返回删除成功与否的提示信息
     */
    @RequestMapping(value = "/deleteCus", method = RequestMethod.POST)
    public Map<String, Object> deleteCus(Customer customer) {
        // 1.用CustomerService的对象调用修改方法
        Boolean flag = customerService.deleteCus(customer);
        // 2.设置返回的提示信息
        Map<String, Object> map = new HashMap<>();

        if (flag) {
            //返回成功状态码
            map.put("code", 200);
            map.put("msg", "删除成功");
        } else {
            //返回访问失败状态码
            map.put("code", 500);
            map.put("msg", "删除失败");
        }
        return map;
    }

    /*
     * 根据id列表删除客户数据，并返回删除成功与否的提示信息
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCusList", method = RequestMethod.POST)
    public String deleteCusList(@RequestParam(required = false, value = "ids") Object ids) {
        String datas = ids.toString();
        //切割前端传递的选择拼接成的字符串
        String[] str = datas.split(",");
        System.out.println(Arrays.toString(str));

        List<Integer> id = new ArrayList<>();
        for (int i = 0; i <= str.length - 1; i++) {
            // 一个信息进行单独处理
            id.add(Integer.parseInt(str[i]));
        }
        System.out.println(id);
        String msg = "";
        for (Integer integer : id) {
            if (customerService.deleteCusByCusIds(integer)) {
                msg = "success";
            } else {
                msg = "fail";
            }
        }
        return msg;
    }

    /*
     * 处理请求，返回客户地区数据
     */
    @RequestMapping(value = "/getCusRegion", method = RequestMethod.GET)
    public Map<String, Object> getCusRegion() {
        List<Report> cusRegion = customerService.getCusRegion();
        // 利用for循环，将cusRegion中的数据读出，将数据分类
        ArrayList<String> xdata = new ArrayList<>();
        ArrayList<Integer> ydata = new ArrayList<>();
        // 将数据分类后添加到对应的列表中
        // 设置地区对应列表的信息
        String[] lis = {"东北", "华北", "西北", "西南", "华南", "华中", "华东"};
        for (Report report : cusRegion) {
            // 根据地区列表的下标获取到地区信息，由于列表从0开始 ，因此需要减1
            xdata.add(lis[report.getItem() - 1]);
            ydata.add(report.getNum());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xdata", xdata);
        map.put("ydata", ydata);
        return map;
    }

    /*
     * 处理地区数据，返回查询的数据[{name:地区，value：个数},.....]形式
     * 便于为前端数据可视化图形赋值
     */
    @RequestMapping(value = "/showReport", method = RequestMethod.GET)
    public List<Report> showReport() {
        // 获取客户地区列表
        List<Report> cusRegion = customerService.getCusRegion();
        List<Report> list = new ArrayList<>();
        String[] lis = {"东北", "华北", "西北", "西南", "华南", "华中", "华东"};
        // 遍历列表，添加name，value值，返回list列表
        for (Report report : cusRegion) {
            list.add(new Report(lis[report.getItem() - 1], report.getNum()));
        }
        return list;
    }

    @RequestMapping(value = "/getCusIndustry", method = RequestMethod.GET)
    public Map<String, Object> getCusIndustry() {
        List<Report> cusRegion = customerService.getCusIndustry();
        // 利用for循环，将cusRegion中的数据读出，将数据分类
        ArrayList<String> xdata = new ArrayList<>();
        ArrayList<Integer> ydata = new ArrayList<>();
        // 将数据分类后添加到对应的列表中
        // 设置地区对应列表的信息
        String[] list = {"金融", "房地产", "商业服务", "运输物流", "生产", "政府", "文化传媒", "其它"};
        for (Report report : cusRegion) {
            // 根据地区列表的下标获取到地区信息，由于列表从0开始 ，因此需要减1
            xdata.add(list[report.getItem() - 1]);
            ydata.add(report.getNum());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xdata", xdata);
        map.put("ydata", ydata);
        map.put("max", Collections.max(ydata) + 1);
        return map;
    }
    @RequestMapping(value = "/showAddr", method = RequestMethod.GET)
    public List<Report> showAddr() {
        // 获取客户地区列表
        List<Report> cusAddr = customerService.getCusAddr();
        List<Report> list = new ArrayList<>();
        // 遍历列表，添加name，value值，返回list列表
        for (Report report : cusAddr) {
            list.add(new Report(report.getAddr(), report.getNum()));
        }
        return list;
    }
}
