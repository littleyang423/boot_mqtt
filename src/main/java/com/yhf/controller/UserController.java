package com.yhf.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.User;
import com.yhf.pojo.VerifyCode;
import com.yhf.service.impl.UserServiceImpl;
import com.yhf.util.IVerifyCodeGen;
import com.yhf.util.impl.ResultUtil;
import com.yhf.util.impl.SimpleCharVerifyCodeGenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


/**
 * @author yhf
 * @date -
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/login")
    public ResultEntity login(@RequestBody User user,HttpServletRequest req){
        String verifyCode = (String) req.getSession().getAttribute("VerifyCode");
        String vcode = user.getVcode();
        if(userService.vcodeIsTrue(verifyCode, vcode)){
            System.out.println("验证码正确");;
        }else{
            System.out.println("验证码错误!原验证码"+verifyCode+"用户输入验证码:"+vcode);
            return ResultUtil.vcodeError();
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        ResultEntity resultEntity = userService.userLogin(user);
        return resultEntity;
    }

    @PostMapping("/registry")
    public ResultEntity registry(@RequestBody User user, HttpServletRequest req){
        String verifyCode = (String) req.getSession().getAttribute("VerifyCode");
        String vcode = user.getVcode();
        if(userService.vcodeIsTrue(verifyCode, vcode)){
            System.out.println("验证码正确");;
        }else{
            System.out.println("验证码错误!原验证码"+verifyCode+"用户输入验证码:"+vcode);
            return ResultUtil.vcodeError();
        }
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("username :"+username+"  password:"+password);
        if(!userService.userRegistry(user,req)){
            return ResultUtil.existError();
        }
        return ResultUtil.success();
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request){
        userService.userLogout(request);
    }

   // @ApiOperation(value = "验证码")
    @GetMapping("/verify_code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();

        if(StpUtil.isLogin()){
            System.out.println("已登录");
        }else{
            System.out.println("未登录");
        }

        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();

            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            System.out.println("code="+code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();

        } catch (IOException e) {

        }
        //return code;
    }

}
