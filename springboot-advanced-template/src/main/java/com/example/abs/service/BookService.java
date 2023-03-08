package com.example.abs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.abs.entity.Book;

public interface BookService extends IService<Book> {

    IPage<Book> selectPage(int currentPage, int pageSize, Book book);
}
