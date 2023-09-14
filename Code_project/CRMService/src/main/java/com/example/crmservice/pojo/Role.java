package com.example.crmservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_role")
public class Role {
    //数据库t_role表对应类型及表头
    //说明主键，并设置为自增
    @TableId(value = "r_id",type = IdType.AUTO)
    private Integer r_id;
    private String r_name;
    private String r_desc;
}
