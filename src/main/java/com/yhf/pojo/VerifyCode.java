package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 验证码类
 */
@Data
@Component
public class VerifyCode {
    private String code;
    private byte[] imgBytes;
    private long expireTime;
}
