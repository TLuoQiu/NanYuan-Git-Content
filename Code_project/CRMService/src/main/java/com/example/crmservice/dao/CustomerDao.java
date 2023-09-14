package com.example.crmservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crmservice.pojo.Customer;
import com.example.crmservice.pojo.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerDao extends BaseMapper<Customer> {

    // 按客户地区统计客户数据
    @Select("SELECT cus_region AS item, COUNT(*) AS num FROM t_customer GROUP BY cus_region")
    List<Report> countByRegion();

    @Select("SELECT cus_industry AS item, COUNT(*) AS num FROM t_customer GROUP BY cus_industry")
    List<Report> countByIndustry();

    @Select("SELECT cus_addr AS addr, COUNT(*) AS num FROM t_customer GROUP BY cus_addr")
    List<Report> countByAddr();

}
