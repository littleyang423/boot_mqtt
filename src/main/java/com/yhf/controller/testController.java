package com.yhf.controller;

import com.yhf.dao.UserDao;
import com.yhf.service.impl.SensorServiceImpl;
import com.yhf.service.impl.UserServiceImpl;
//import com.yhf.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yhf
 * @date -
 */
@RestController
public class testController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SensorServiceImpl sensorUtil;
//    @Autowired
//    private TokenUtils tokenUtils;

    @RequestMapping("/test/testRequest")
    public String userExist(@RequestParam("data")String data, HttpServletRequest req, HttpServletResponse resp){

        System.out.println(data);
//        String vcode2 = (String) req.getAttribute("vcode");

//        System.out.println("vcode = "+vcode);
//        System.out.println("vcode = "+vcode2);
//        tokenUtils.checkToken(req);
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String vcode = req.getParameter("vcode");
//        userService.UserRegistry(username,password,req);

        return "success!";
    }


    @Autowired
    UserDao userDao;

    @RequestMapping("/login1")
    public String login1(){
        String themes = sensorUtil.getThemes("111.229.163.181", "1883");
        return themes;
    }


}
