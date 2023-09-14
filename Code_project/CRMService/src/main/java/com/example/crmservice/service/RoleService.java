package com.example.crmservice.service;

import com.example.crmservice.dao.RoleDao;
import com.example.crmservice.pojo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    //自动注入对象
    private final RoleDao roleDao;

    /*
    创建java方法
    查询t_role表的所有数据
     */

    public List<Role> getRolList(){
        //执行后，将t_role表中的所有数据放入到roles中
        List<Role> roles = roleDao.selectList(null);

        return roles;

    }
}
