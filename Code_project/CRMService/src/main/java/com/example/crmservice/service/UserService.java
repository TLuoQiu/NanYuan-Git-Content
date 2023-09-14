package com.example.crmservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmservice.dao.UserDao;
import com.example.crmservice.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    //自动注入对象
    final UserDao userDao;

    /*
     *通过用户名和密码查询到一个user的信息
     */
    public User getUser(String name, String password) {
        //构建查询条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("u_name", name).eq("u_password", password);
        //用查询条件为参数传入查询方法
        //返回查询结果 --user对象
        return userDao.selectOne(userQueryWrapper);
    }
}
