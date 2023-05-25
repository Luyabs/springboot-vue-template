package com.example.abs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.abs.entity.Book;
import com.example.abs.common.base.BaseService;

public interface BookService extends BaseService<Book> {
    IPage<Book> selectPage(int currentPage, int pageSize, Book book);
}
