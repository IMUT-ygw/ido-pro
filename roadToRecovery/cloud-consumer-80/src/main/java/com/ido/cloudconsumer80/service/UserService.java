package com.ido.cloudconsumer80.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ido.cloudconsumer80.mapper.UserLoginMapper;
import com.ido.cloudconsumer80.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserLoginMapper userLoginMapper;


    public User login(User user){
        User u = userLoginMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()).eq("pwd", user.getPwd()));
        if(u != null){
            return u;
        }
        throw new RuntimeException("登录失败！");
    }
}
