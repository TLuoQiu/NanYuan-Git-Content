package com.example.crmservice.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crmservice.pojo.SaleChance;
import com.example.crmservice.service.SaleChanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiTao
 * @since 2023-09-04
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/saleChance")
public class SaleChanceController {

    //自动注入对象
    final SaleChanceService saleChanceService;

    @RequestMapping(value = "/getSale", method = RequestMethod.GET)
    public Map<String, Object> getSaleChance() {

        List<SaleChance> saleChanceList = saleChanceService.getSaleChanceList();

        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", saleChanceList.size());//cusList.size(),查询出来的数据条数
        map.put("data", saleChanceList);//查询出来的所有对象
        return map;
    }

    /*
     *处理前端发回的客户修改数据的请求，并返回成功与否的信息
     */
    @RequestMapping(value = "/updateSale", method = RequestMethod.POST)
    public Map<String, Object> updateSale(SaleChance saleChance) {

        // 1.用CustomerService的对象调用修改方法

        Boolean flag = saleChanceService.updateSale(saleChance);
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
    @RequestMapping(value = "/insertSale", method = RequestMethod.POST)
    public Map<String, Object> insertSale(SaleChance saleChance) {

        // 1.用CustomerService的对象调用修改方法

        Boolean flag = saleChanceService.insertSale(saleChance);
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
    @RequestMapping(value = "/deleteSale", method = RequestMethod.POST)
    public Map<String, Object> deleteSale(SaleChance saleChance) {
        // 1.用CustomerService的对象调用修改方法
        Boolean flag = saleChanceService.deleteSale(saleChance);
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
     * 点击分配按钮，进行客户的分配，添加指派给谁，指派时间，状态
     */
    @RequestMapping(value = "/assignSale", method = RequestMethod.POST)
    public Map<String,Object> assignSale(Integer sc_id,Integer sc_giveuserid){
        //通过sc_id查询出原来的salechance
        SaleChance one = saleChanceService.getOne(sc_id);
        //获取到salechance中的指派给谁，指派时间，状态数据
        one.setSc_giveuserid(sc_giveuserid);
        one.setSc_status(1);//设置为已分配 状态
        one.setSc_givetime(LocalDateTime.now());
        //将这个数据作为参数
        Boolean aBoolean = saleChanceService.updateSale(one);
        Map<String, Object> map = new HashMap<>();
        if (aBoolean) {
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
     * 根据id列表删除客户数据，并返回删除成功与否的提示信息
     */

    @ResponseBody
    @RequestMapping(value = "/deleteSaleList" ,method = RequestMethod.POST)
    public String deleteSaleList(@RequestParam(required = false, value = "ids") Object ids) {
        String datas = ids.toString();
        //获取到object中的值
        //切割前端传递的选择拼接成的字符串
//        System.out.println(entity);
        String[] str = datas.split(",");
        System.out.println(Arrays.toString(str));

        List<Integer> id = new ArrayList<>();
        for (int i = 0;i<= str.length -1; i++){
            // 一个信息进行单独处理
            int data = Integer.parseInt(str[i]);
            id.add(data);
        }
        System.out.println(id);
//        Boolean flag = saleChanceService.deleteSaleBySaleIds(id);
        String msg = "";
        for (Integer integer : id) {
            if (saleChanceService.deleteSaleBySaleIds(integer)) {
                msg = "success";
            } else {
                msg = "fail";
            }
        }
        return msg;
    }

    @RequestMapping(value = "/getManagerSale", method = RequestMethod.GET)
    public Map<String, Object> getManagerSale(Integer u_id) {
        // 通过saleChanceService调用方法
        List<SaleChance> managerSale = saleChanceService.getManagerSale(u_id);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", managerSale.size());//cusList.size(),查询出来的数据条数
        map.put("data", managerSale);//查询出来的所有对象
        return map;
    }

    /*
     * 分页查询销售数据
     */
    @RequestMapping(value = "/getSearchPageSale", method = RequestMethod.GET)
    public Map<String, Object> getSearchPageSale(Integer page, Integer limit) {
        IPage<SaleChance> saleChanceIPage = saleChanceService.pageSale(page, limit);
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", saleChanceIPage.getTotal());// saleChanceIPage.getTotal(),查询出来的数据条数
        map.put("data", saleChanceIPage.getRecords());// 查询出来的所有对象
        return map;
    }

}
