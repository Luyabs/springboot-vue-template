package com.example.abs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.abs.entity.Book;
import com.example.abs.mapper.BookMapper;
import com.example.abs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> selectPage(int currentPage, int pageSize, Book book) {
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>()
                .like(book.getType() != null, "type", book.getType())
                .like(book.getName() != null, "name", book.getName())
                .like(book.getDescription() != null, "description", book.getDescription());
        return bookMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
    }
}
