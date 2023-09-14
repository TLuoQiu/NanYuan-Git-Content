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
 * @since 2023-09-08
 */
@Data
@TableName("t_dev_plan")
public class DevPlan {

    private static final long serialVersionUID = 1L;

    /**
     * 开发计划编号
     */
    @TableId(value = "dp_id", type = IdType.AUTO)
    private Integer dp_id;

    /**
     * 计划日期
     */
    //前端 需要填充的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime dp_plandate;

    /**
     * 计划内容
     */
    private String dp_value;

    /**
     * 执行日期
     */
    //前端 需要填充的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime dp_exedate;

    /**
     * 执行结果
     */
    private String dp_result;

    /**
     * 执行人
     */
    private String dp_exeperson;

    /**
     * 销售机会编号
     */
    private Integer sc_id;

}
