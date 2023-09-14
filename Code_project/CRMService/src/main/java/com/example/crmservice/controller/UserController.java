package com.example.crmservice.controller;

import com.example.crmservice.pojo.User;
import com.example.crmservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiTao
 * @since 2023-09-06
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    //自动注入对象
    final UserService userService;
    /*
     *处理登录请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String name, String password) {
        User user = userService.getUser(name,password);
        HashMap<String, Object> map = new HashMap<>();
        if(user != null){
            map.put("msg",true);
            map.put("user",user);
        }else {
            map.put("msg", false);
        }
        return map;
    }
}
