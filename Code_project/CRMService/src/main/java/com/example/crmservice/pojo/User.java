package com.example.crmservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author TY
 * @since 2023-09-06
 */
@Data
@TableName("t_user")
public class User {

    private static final long serialVersionUID = 1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer u_id;

    private String u_name;

    private String u_password;

    private Integer r_id;

}
