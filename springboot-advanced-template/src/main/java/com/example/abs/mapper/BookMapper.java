package com.example.abs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abs.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
