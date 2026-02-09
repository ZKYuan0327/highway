package com.example.highway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.highway.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title UserMapper
 * @Author: ZKY
 * @CreateTime: 2023-03-07  11:36
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
