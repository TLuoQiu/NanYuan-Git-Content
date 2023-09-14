package com.example.crmservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JiTao
 * @since 2023-09-04
 */
@Data
@TableName("t_sale_chance")
public class SaleChance {

    @TableId(value = "sc_id", type = IdType.AUTO)
    private Integer sc_id;

    /**
     * 客户名称
     */
    private String sc_cusname;

    /**
     * 机会来源（0促销、1广告、2搜索引擎、3线上咨询、4电话咨询、5邮件咨询、6客户介绍）
     */
    private Integer sc_comming;

    /**
     * 联系人
     */
    private String sc_name;

    /**
     * 联系电话
     */
    private String sc_phone;

    /**
     * 成功几率(%)
     */
    private String sc_success;

    /**
     * 概要
     */
    private String sc_message;

    /**
     * 机会描述
     */
    private String sc_desc;

    /**
     * 创建人
     */
    private Integer sc_createuserid;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sc_createtime;

    /**
     * 指派给谁
     */
    private Integer sc_giveuserid;

    /**
     * 指派时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sc_givetime;

    /**
     * 状态（0未分配、1已分配、2开发成功、3开发失败、4开发中）
     */
    private Integer sc_status;

}
