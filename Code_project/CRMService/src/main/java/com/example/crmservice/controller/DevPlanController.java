package com.example.crmservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crmservice.pojo.DevPlan;
import com.example.crmservice.service.DevPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author JiTao
 * @since 2023-09-08
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value ="/devPlan")
public class DevPlanController {

    //自动注入对象
    final DevPlanService devPlanService;


    @RequestMapping(value = "/getDev", method = RequestMethod.GET)
    public Map<String, Object> getDev(Integer page, Integer limit) {

        IPage<DevPlan> devPlanIPage = devPlanService.pageCus(page, limit);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", devPlanIPage.getTotal());// devPlanIPage.getTotal(),查询出来的数据条数
        map.put("data", devPlanIPage.getRecords());// 查询出来的所有对象
        return map;
    }

    /*
     *处理前端发回的添加客户数据的请求，并返回成功与否的信息
     */
    @RequestMapping(value = "/insertDev", method = RequestMethod.POST)
    public Map<String, Object> insertDev(DevPlan devPlan) {

        // 1.用CustomerService的对象调用修改方法

        Boolean flag = devPlanService.insertDev(devPlan);
        // 2.设置返回的提示信息
        Map<String, Object> map = new HashMap<>();

        if (flag) {
            //返回成功状态码
            map.put("code", 200);
            map.put("msg", "计划制定成功");
        } else {
            //返回访问失败状态码
            map.put("code", 500);
            map.put("msg", "计划制定失败");
        }
        return map;
    }

    @RequestMapping(value = "/updateDev", method = RequestMethod.POST)
    public Map<String, Object> updateDev(DevPlan devPlan) {

        // 1.用devPlanService的对象调用修改方法

        Boolean flag = devPlanService.updateDev(devPlan);
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
     * 点击按钮，对计划进行开发
     */
    @RequestMapping(value = "/devPlanChance", method = RequestMethod.POST)
    public Map<String, Object> devPlanChance(Integer dp_id, String dp_result) {
        //通过dp_id查询出原来的devPlan
        DevPlan dev = devPlanService.returnOne(dp_id);
        //设置开发状态
        dev.setDp_result(dp_result);
        //将这个数据作为参数
        Boolean aBoolean = devPlanService.updateDev(dev);
        Map<String, Object> map = new HashMap<>();
        if (aBoolean) {
            //返回成功状态码
            map.put("code", 200);
            map.put("msg", "操作成功");
        } else {
            //返回访问失败状态码
            map.put("code", 500);
            map.put("msg", "操作失败");
        }
        return map;
    }

}
