package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用户
 */
@Data
@Component
public class User {
    private String username;
    private String password;
    private String vcode;
    private String phone;

}
