package com.example.crmservice;

import com.example.crmservice.pojo.Customer;
import com.example.crmservice.pojo.Role;
import com.example.crmservice.pojo.SaleChance;
import com.example.crmservice.service.CustomerService;
import com.example.crmservice.service.RoleService;
import com.example.crmservice.service.SaleChanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    CustomerService customerService;

    @Test
    void TestCustomers() {

        List<Customer> cusList = customerService.getCusList();

        System.out.println(cusList.get(0));
    }

    @Autowired
    RoleService roleService;

    @Test
    void TestRole(){

        List<Role> rolList = roleService.getRolList();

        System.out.println(rolList.get(0));
    }




    @Autowired
    SaleChanceService saleChanceService;

    @Test
    void TestsaleChance(){
        List<SaleChance> saleChanceList = saleChanceService.getSaleChanceList();

        System.out.println(saleChanceList.get(0));
    }
}
