package com.example.abs;

import com.example.abs.common.Result;
import com.example.abs.controller.BookController;
import com.example.abs.entity.Book;
import com.example.abs.service.BookService;
import com.example.abs.service.impl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NormalTest {
    @Autowired
    private BookController bookController;

    @Test
    void test01() {
        Result result = bookController.getById(111);
        System.out.println(result);
    }
}
