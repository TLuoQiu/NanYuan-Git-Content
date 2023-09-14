package com.example.crmservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmservice.dao.CustomerDao;
import com.example.crmservice.pojo.Customer;
import com.example.crmservice.pojo.Report;
import com.example.crmservice.pojo.SaleChance;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerService {

    //自动注入对象
    private final CustomerDao customerDao;

    /*
    创建java方法
    查询t_customer表的所有数据
     */

    public List<Customer> getCusList() {
        //执行后，将t_customer表中的所有数据放入到customers中
        return customerDao.selectList(null);
    }

    /*
     *根据客户ID修改客户信息
     */

    public Boolean updateCus(Customer customer) {

        //updateById返回的是改变的数据条数
        int i = customerDao.updateById(customer);
        //如果i>0,说明有数据被修改了，-- 成功；否则失败
        return i > 0;

    }

    /*
     * 创建insertCus方法，插入一条数据，返回操作结果，并修改到customer中
     */

    public Boolean insertCus(Customer customer) {

        int insert = customerDao.insert(customer);
        // 如果i>0,说明有数据被插入了，-- 成功；否则失败
        return insert > 0;
    }

    /*
     * 删除一行客户数据
     */

    public Boolean deleteCus(Customer customer) {
        // 1.先根据id删除一行数据，然后再删除一行主键
        int i = customerDao.deleteById(customer);

        // 如果i>0,说明删除了一行数据，-- 成功；否则失败
        return i > 0;
    }

    /*
     * 根据sc_id列表删除多行客户数据
     */

    public Boolean deleteCusByCusIds(Integer ids) {

        int i = customerDao.deleteById(ids);
        // 如果i>0,说明删除了一行数据，-- 成功；否则失败
        return i > 0;
    }

    /*
     * 分页查询数据
     */

    public IPage<Customer> pageCus(Integer page, Integer limit) {
        // 创建page对象，页面页数，每个页面多少条数据
        Page<Customer> cusPage = new Page<>(page, limit);
        // 利用customerDao调用方法，查询数据
        customerDao.selectPage(cusPage, null);
        // 返回cusPage对象
        return cusPage;
    }

    /*
     * 获取客户地区及其对应的客户数量
     */

    public List<Report> getCusRegion() {
        // 返回获取到的客户地区数据
        return customerDao.countByRegion();
    }

    public List<Report> getCusIndustry(){
        // 返回获取到的客户行业数据
        return customerDao.countByIndustry();
    }
    public List<Report> getCusAddr(){
        // 返回获取到的客户行业数据
        return customerDao.countByAddr();
    }
}
