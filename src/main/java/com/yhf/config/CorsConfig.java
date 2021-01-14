package com.yhf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedOrigins("http://192.168.100.116:8080","http://192.168.100.164:8080","http://localhost:8080")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3600);
    }
//    @Bean
//    public CookieSerializer httpSessionIdResolver(){
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setSameSite(null);
//        return cookieSerializer;
//    }
}