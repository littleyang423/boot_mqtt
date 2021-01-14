package com.yhf.dao;

import com.yhf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface UserDao {

    User userExist(String username,String password,String phone);

    void addUser(@Param("user") User user);

    Integer getUserId(String username,String phone);

    void userLogin(Integer id);

    void userLogout(int id);

    Integer userStatus(int id);

    List<User> getAllLoginedUser();
}
