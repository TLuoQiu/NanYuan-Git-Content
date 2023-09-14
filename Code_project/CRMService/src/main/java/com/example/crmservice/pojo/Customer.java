package com.example.crmservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
// t_customer的数据实体类，用来作为它的数据库表的映射
@TableName("t_customer")
public class Customer {
    //数据库t_customer表对应类型及表头
    //说明主键，并设置为自增
    @TableId(value = "cus_id",type = IdType.AUTO)
    private Integer cus_id;
    private String cus_name;
    private Integer cus_region;
    private Integer cus_industry;
    private String cus_level;
    private Integer cus_satisfy;
    private Integer cus_credit;
    private String cus_addr;
    private Integer cus_zipcode;
    private String cus_phone;
    private String cus_fax;
    private String cus_url;
    private Integer user_id;
    private Integer cus_status;
    //前端 需要填充的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime cus_createtime;
}
