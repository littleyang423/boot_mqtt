package com.yhf.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.yhf.dao.UserDao;
import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.User;
import com.yhf.service.UserService;
//import com.yhf.util.TokenUtils;
import com.yhf.util.impl.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
//    @Autowired
//    private TokenUtils tokenUtils;

    /**
     * 注册
     */
    @Override
    public boolean userRegistry(User user1, HttpServletRequest request) {
        String username = user1.getUsername();
        String password = user1.getPassword();
        String phone = user1.getPhone();
        User user = userDao.userExist(username,null,phone);
        if(user == null){
            userDao.addUser(user1);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean vcodeIsTrue(String vcode, String userVcode) {
//        String vcode2 = vcode.toLowerCase();
//        String userVcode2 = userVcode.toLowerCase();
        if(vcode.toLowerCase().equals(userVcode.toLowerCase())){
            return true;
        }
        return false;
    }

    @Override
    public ResultEntity userLogin(User user2) {
        String username = user2.getUsername();
        String password = user2.getPassword();
        String phone = user2.getPhone();
//        System.out.println("username="+username);
//        System.out.println("password="+password);
//        System.out.println("phone="+phone);
        User user =null;
        if(username == null || "".equals(username)){
            user = userDao.userExist(null, null,phone);
//            System.out.println("username == null");
        }
        if(phone==null || "".equals(phone)){
            user = userDao.userExist(username, null,null);
//            System.out.println("phone == null");
        }
        //用户不存在，登录失败
        if(user == null){
            return ResultUtil.notExist();
        }
        if("".equals(username)){
            user = userDao.userExist(null, password, phone);
        }
        if("".equals(phone)){
            user = userDao.userExist(username, password, null);
        }
//        System.out.println("================username="+username);
        //密码错误，登录失败
        if(user == null){
            return ResultUtil.pwdError();
        }
        String username1 = user.getUsername();
        int userId = userDao.getUserId(username1,null);

        /**
         * 标记用户已登录
         * 同时将生成的token传给前端
         */
        StpUtil.setLoginId(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        ResultEntity success = ResultUtil.success(tokenInfo);
        success.setMsg(username1);

//        System.out.println("username"+user.getUsername());
        //生成token传给客户端
        return success;
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        StpUtil.logout();
//        String token = request.getHeader("token");
//        tokenUtils.removeToken(token);
    }

    @Override
    public int getUserIdByName(String name) {
        int userId = userDao.getUserId(name,null);
        return userId;
    }


}
