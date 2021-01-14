package com.yhf;

import cn.dev33.satoken.SaTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootMqttApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqttApplication.class, args);
        System.out.println("启动成功：sa-token配置如下：" + SaTokenManager.getConfig());
    }

}
