package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yhf
 * @data -
 */
@Data
@Component
public class Person {
    private String username;
    private String passwd;
}
