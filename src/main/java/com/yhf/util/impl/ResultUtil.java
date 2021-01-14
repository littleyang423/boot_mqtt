package com.yhf.util.impl;


import com.yhf.pojo.ResultEntity;

/**
 * 通用返回工具类
 * @author ${author}
 * @since 2020-11-25
 */
public class ResultUtil {

    /**
     * 成功且带数据
     **/
    public static ResultEntity success(Object object) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.SUCCESS.getCode());
        resultEntity.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        resultEntity.setData(object);
        return resultEntity;
    }

    /**
     * 成功但不带数据
     **/
    public static ResultEntity success() {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.SUCCESS.getCode());
        resultEntity.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 失败
     **/
    public static ResultEntity error(String code, String msg) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(code);
        resultEntity.setMsg(msg);
        return resultEntity;
    }

    //注册失败，验证码错误
    public static ResultEntity vcodeError(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.VCODE_ERROR.getCode());
        resultEntity.setMsg(ResultCodeEnum.VCODE_ERROR.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }
    //注册失败，用户已存在
    public static ResultEntity existError(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.USER_IS_EXISTS.getCode());
        resultEntity.setMsg(ResultCodeEnum.USER_IS_EXISTS.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    //登录失败，密码错误
    public static ResultEntity pwdError(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.PWD_ERROR.getCode());
        resultEntity.setMsg(ResultCodeEnum.PWD_ERROR.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }
    //登录失败，用户不存在
    public static ResultEntity notExist(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.USER_NOT_EXIST.getCode());
        resultEntity.setMsg(ResultCodeEnum.USER_NOT_EXIST.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }


    /**
     * 未登录
     */
    public static ResultEntity loginExpired(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.LOGIN_EXPIRED.getCode());
        resultEntity.setMsg(ResultCodeEnum.LOGIN_EXPIRED.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 未提供token
     */
    public static ResultEntity noToken(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.NO_TOKEN.getCode());
        resultEntity.setMsg(ResultCodeEnum.NO_TOKEN.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * token无效
     */
    public static ResultEntity invalidToken(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.INVALID_TOKEN.getCode());
        resultEntity.setMsg(ResultCodeEnum.INVALID_TOKEN.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * token过期
     */
    public static ResultEntity expiredToken(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.EXPIRED_TOKEN.getCode());
        resultEntity.setMsg(ResultCodeEnum.EXPIRED_TOKEN.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * token被顶下线
     */
    public static ResultEntity replaceToken(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.REPLACE_TOKEN.getCode());
        resultEntity.setMsg(ResultCodeEnum.REPLACE_TOKEN.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 数据为空
     */
    public static ResultEntity dataIsNull(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.DATA_IS_NULL.getCode());
        resultEntity.setMsg(ResultCodeEnum.DATA_IS_NULL.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }

    /**
     * 参数错误
     */
    public static ResultEntity ParamError(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(ResultCodeEnum.PARAM_ERROR.getCode());
        resultEntity.setMsg(ResultCodeEnum.PARAM_ERROR.getMsg());
        resultEntity.setData(null);
        return resultEntity;
    }
}
