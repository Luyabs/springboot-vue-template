package com.example.abs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.example.abs.common.Result;
import com.example.abs.common.exception.exception.NoAccessException;
import com.example.abs.common.base.BaseController;
import com.example.abs.entity.Book;
import com.example.abs.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"书本管理", "理管本书"})
@RestController
@RequestMapping("/book")
public class BookController extends BaseController<Book> {
    @Autowired
    private BookService bookService;

    @SaCheckLogin
    @ApiOperation(value = "分页获取[可带条件]", notes = "需要传入当前页和页大小,可以选择传入条件[type, name, description]进行模糊查询")
    @GetMapping("/page")
    public Result getPage(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, Book book) {
        return Result.success().data("page", bookService.selectPage(currentPage, pageSize, book));
    }

    @SaCheckRole("admin")
    @ApiOperation(value = "事务测试", notes = "必定抛异常")
    @Transactional
    @PostMapping("/transaction")
    public Result makeSomeException() {
        Book book = new Book();
        book.setName("好书");
        book.setType("哲学");
        bookService.save(book);
        if (true)
//            throw new ServiceException("故意制造异常");
            throw new NoAccessException(0, "书本 (这是故意制造的异常)");
        bookService.save(book);
        return Result.success().message("不可能到达的return");
    }

    @SaCheckLogin
    @ApiOperation(value = "hello", tags = "hello")
    @PostMapping("/hello")
    public Result hello() {
        log.error("hello");
        log.info("hello");
        log.warn("hello");
        log.debug("hello");
        return Result.success().data("hello", StpUtil.getLoginId());
    }

    @ApiOperation(value = "testConfigureMessageConverters", tags = "测试长数据转换")
    @PostMapping("/long")
    public Result testBigInteger(@RequestBody Long num) {
        System.out.println(num);
        return Result.success().data("number", num);
    }
}
