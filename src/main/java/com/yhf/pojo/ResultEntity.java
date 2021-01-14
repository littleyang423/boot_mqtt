package com.yhf.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用返回数据类

 */
@Data
@ToString
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 1575880832415420562L;
    private String code;
    private String msg;
    private Object data;
}
