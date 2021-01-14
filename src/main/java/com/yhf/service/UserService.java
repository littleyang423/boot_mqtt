package com.yhf.service;

import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 */

public interface UserService {
    boolean userRegistry(User user, HttpServletRequest request);

    boolean vcodeIsTrue(String vcode,String userVcode);

    ResultEntity userLogin(User user);

    void userLogout(HttpServletRequest request);

    int getUserIdByName(String name);



}
