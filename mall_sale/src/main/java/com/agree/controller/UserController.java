package com.agree.controller;

import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserServer userServer;

    @ResponseBody
    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public String check_userName(T_MALL_USER_ACCOUNT user){
        String result = userServer.exist(user);
        return result;
    }

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public String registry_user(T_MALL_USER_ACCOUNT user){
        userServer.registry(user);
        return "redirect:/goto_login.do";
    }

}
