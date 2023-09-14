package com.example.crmservice.dao;

import com.example.crmservice.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TY
 * @since 2023-09-06
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
