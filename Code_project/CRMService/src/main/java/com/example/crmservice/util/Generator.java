package com.example.crmservice.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/crm?serverTimezone=Asia/Shanghai", "root", "20010219")
                .globalConfig(builder -> {
                    builder.author("JiTao")// 设置作者
                            .dateType(DateType.TIME_PACK)//生成的entity类中时间类型就是LocalDate或者LocalDateTime
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录,到java
                })
                .packageConfig(builder -> {//设置包名
                    builder.parent("com.example.crmservice") // 设置父包名
                            .entity("pojo")//设置实体类包名，默认值:entity
                            .mapper("dao");//默认值:mapper
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_dev_plan") // 设置需要生成的表名
                            .addTablePrefix("t_") // 设置过滤表前缀
                            .entityBuilder()//实体类策略
                            .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略：驼峰
                            .columnNaming(NamingStrategy.no_change)//数据库表字段映射到实体的命名策略: 不变
                            .mapperBuilder()//Dao层策略
                            .enableMapperAnnotation()//开启 @Mapper 注解
                            .formatMapperFileName("%sDao")//设置文件名称，%s适配，根据表名替换
                            .controllerBuilder()//控制层
                            .enableRestStyle()//开启生成@RestController 控制器
                            .formatFileName("%sController");

                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.SERVICE)//不生成service接口
                            .disable(TemplateType.SERVICEIMPL)
                            .disable(TemplateType.XML);

                })
                .execute();
    }
}
