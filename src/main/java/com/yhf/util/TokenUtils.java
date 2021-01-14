//package com.yhf.util;
//
//import cn.dev33.satoken.stp.StpUtil;
//import com.yhf.dao.UserDao;
//import com.yhf.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.zip.CheckedOutputStream;
//
///**
// * @author yhf
// * @date -
// */
//@Component
//public class TokenUtils {
//    @Autowired
//    private UserDao userDao;
//
//    private static Map<String,Integer> maps = new HashMap<>();
//    //过期时间，设置为1小时
//    private static Integer out_time = 1000*60*60;
//
//    static Integer SUCCESS = 1;
//    //登录信息过期
//    static Integer LOGIN_EXPIRED=2;
//    //未登录
//    static Integer NOT_LOGIN = 3;
//
//    public static int getUserIdByToken(String token){
//        return maps.get(token);
//    }
//
//    public String getToken(Integer id){
//        long times = System.currentTimeMillis();
//        String token = times + "";
//        maps.put(token,id);
//        //用户登录就将用户状态改为已登录
//        System.out.println("userid="+id);
//        System.out.println("userDao="+userDao);
//        userDao.userLogin(id);
//        StpUtil.setLoginId(token);
//
//        return token;
//    }
//
//    public void removeToken(String token){
//        //用户退出就将用户状态改为未登录
//        userDao.userLogout(getUserIdByToken(token));
//        maps.remove(token);
//    }
//
//    public int checkToken(HttpServletRequest req){
//        long now_times = System.currentTimeMillis();
//        for(String str: maps.keySet()){
//            long old_times = Long.parseLong(str);
//            if(now_times - old_times >= out_time){
//                //token超时就删除token
//                removeToken(str);
//            }
//        }
//
//        String token = (String) req.getHeader("token");
//        if(token == null){
//            return NOT_LOGIN;
//        }
//        Integer id = maps.get(token);
//        if(id == null){
//            System.out.println("登录信息已过期,请重新登录");
//            return LOGIN_EXPIRED;
//        }
//        return SUCCESS;
//    }
//
//}
