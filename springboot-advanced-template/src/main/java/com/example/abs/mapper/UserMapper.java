package com.example.abs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abs.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
