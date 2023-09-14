package com.example.crmservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmservice.dao.DevPlanDao;
import com.example.crmservice.pojo.DevPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevPlanService {

    // 自动注入对象
    final DevPlanDao devPlanDao;

    /*
    创建java方法
    查询dev_plan表的所有数据
     */

    public List<DevPlan> getCusList() {
        //执行后，将t_customer表中的所有数据放入到customers中
        return devPlanDao.selectList(null);
    }

    /*
     * 分页查询数据
     */

    public IPage<DevPlan> pageCus(Integer page, Integer limit) {
        // 创建page对象，页面页数，每个页面多少条数据
        Page<DevPlan> devPlanPage = new Page<>(page, limit);
        // 利用devPlanDao调用方法，查询数据
        devPlanDao.selectPage(devPlanPage, null);
        // 返回Page对象
        return devPlanPage;
    }

    public Boolean insertDev(DevPlan devPlan) {

        int insert = devPlanDao.insert(devPlan);
        // 如果i>0,说明有数据被插入了，-- 成功；否则失败
        return insert > 0;
    }

    /*
     *根据客户修改客户信息
     */

    public Boolean updateDev(DevPlan devPlan) {

        //updateById返回的是改变的数据条数
        int i = devPlanDao.updateById(devPlan);
        //如果i>0,说明有数据被修改了，-- 成功；否则失败
        return i > 0;

    }
    /*
     * 根据选择的数据的dp_id查询出当前数据
     */
    public DevPlan returnOne(Integer id) {
        // 返回查询结果
        return devPlanDao.selectById(id);
    }

}
