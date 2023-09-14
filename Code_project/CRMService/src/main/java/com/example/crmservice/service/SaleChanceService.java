package com.example.crmservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmservice.dao.SaleChanceDao;
import com.example.crmservice.pojo.Customer;
import com.example.crmservice.pojo.SaleChance;
import com.example.crmservice.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//自动注入对象
@RequiredArgsConstructor
public class SaleChanceService {

    //自动注入对象
    private final SaleChanceDao saleChanceDao;

    /*
     *创建java方法
     *查询sale表的所有数据
     */
    public List<SaleChance> getSaleChanceList() {

        //执行后，将sale_chance表中的所有数据放入到saleChanceList中
        List<SaleChance> saleChanceList = saleChanceDao.selectList(null);

        return saleChanceList;
    }

    /*
     *根据客户ID修改客户信息
     */

    public Boolean updateSale(SaleChance saleChance) {

        //updateById返回的是改变的数据条数
        int i = saleChanceDao.updateById(saleChance);
        //如果i>0,说明有数据被修改了，-- 成功；否则失败
        return i > 0;

    }

    /*
     * 创建insertCus方法，插入一条数据，返回操作结果，并修改到customer中
     */

    public Boolean insertSale(SaleChance saleChance) {

        int insert = saleChanceDao.insert(saleChance);
        // 如果i>0,说明有数据被插入了，-- 成功；否则失败
        return insert > 0;
    }

    public SaleChance getOne(Integer id) {

        return saleChanceDao.selectById(id);
    }

    /*
     * 删除一行客户数据
     */

    public Boolean deleteSale(SaleChance saleChance) {
        // 1.先根据id删除一行数据，然后再删除一行主键
        int i = saleChanceDao.deleteById(saleChance);

        // 如果i>0,说明删除了一行数据，-- 成功；否则失败
        return i > 0;
    }

    /*
     * 根据sc_id列表删除多行客户数据
     */

    public Boolean deleteSaleBySaleIds(Integer ids) {

        int i = saleChanceDao.deleteById(ids);
        // 如果i>0,说明删除了一行数据，-- 成功；否则失败
        return i > 0;
    }

    /*
     * 根据登录用户的u_id = 指派给谁的字段，查询指派机会
     * u_id是前端传来的参数
     */

    public List<SaleChance> getManagerSale(Integer u_id) {
        // 构造查询条件
        QueryWrapper<SaleChance> saleChanceQueryWrapper = new QueryWrapper<>();
        // 根据已登录用户的当前ID，查询出当前登录用户
        saleChanceQueryWrapper.eq("sc_giveuserid", u_id);
        // 返回查询结果列表
        return saleChanceDao.selectList(saleChanceQueryWrapper);
    }
    /*
     * 分页查询数据
     */
    public IPage<SaleChance> pageSale(Integer page, Integer limit) {
        // 创建page对象，页面页数，每个页面多少条数据
        Page<SaleChance> saleChancePage = new Page<>(page, limit);
        // 利用saleChanceDao调用方法，查询数据
        saleChanceDao.selectPage(saleChancePage, null);
        // 返回saleChancePage对象
        return saleChancePage;
    }

}

