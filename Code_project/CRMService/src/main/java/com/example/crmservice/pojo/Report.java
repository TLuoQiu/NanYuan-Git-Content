package com.example.crmservice.pojo;

import lombok.Data;

// @Data可以代替对应的get，set方法
@Data
// 用来装查询出来的统计数据
public class Report {
    //y轴对应的数据
    private Integer num;
    //x轴对应的数据项
    private Integer item;

    private String addr;
    // 创建键值对
    private String name;
    
    private Integer value;
    
    public Report(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

}
