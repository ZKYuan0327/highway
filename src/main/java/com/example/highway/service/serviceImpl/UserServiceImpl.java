package com.example.highway.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.highway.mapper.UserMapper;
import com.example.highway.pojo.User;
import org.springframework.stereotype.Service;
import com.example.highway.service.UserService;

/**
 * @title UserServiceImpl
 * @Author: ZKY
 * @CreateTime: 2023-03-07  11:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
