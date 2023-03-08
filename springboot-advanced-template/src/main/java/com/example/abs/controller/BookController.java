package com.example.abs.controller;

import com.example.abs.common.Result;
import com.example.abs.common.exception.ServiceException;
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
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "获取全部书本", notes = "获取List<Book>")
    @GetMapping("/all")
    public Result getAll() {
        return Result.success().data("list", bookService.list());
    }

    @ApiOperation(value = "分页获取书本", notes = "需要传入当前页和页大小,可以选择传入条件[type, name, description]进行模糊查询")
    @GetMapping
    public Result getPage(int currentPage, int pageSize, Book book) {
        log.error(book.toString());
        return Result.success().message("表单数据获取成功").data("page", bookService.selectPage(currentPage, pageSize, book));
    }

    @ApiOperation(value = "按id获取书本", notes = "需要传入路径变量")
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        return Result.success().data("book", bookService.getById(id));
    }

    @ApiOperation(value = "新增书本", notes = "需要传入JSON")
    @PostMapping
    public Result addBook(@RequestBody Book book) {
        bookService.save(book);
        return Result.success().message("增加成功");
    }

    @ApiOperation(value = "修改书本", notes = "需要传入JSON")
    @PutMapping()
    public Result editBook(@RequestBody Book book) {
        bookService.updateById(book);
        return Result.success().message("修改成功");
    }

    @ApiOperation(value = "删除书本", notes = "需要传入路径变量id")
    @DeleteMapping("/{id}")
    public Result removeBook(@PathVariable int id) {
        bookService.removeById(id);
        return Result.success().message("删除成功");
    }

    @ApiOperation(value = "事务测试", notes = "必定抛异常")
    @Transactional
    @PostMapping("/transaction")
    public Result makeSomeException() {
        Book book = new Book();
        book.setName("好书");
        book.setType("哲学");
        bookService.save(book);
        if (true)
            throw new ServiceException("故意制造异常");
        bookService.save(book);
        return Result.success().message("不可能到达的return");
    }


    @ApiOperation(value = "hello", tags = "hello")
    @PostMapping("/hello")
    public Result hello() {
        log.error("hello");
        log.info("hello");
        log.warn("hello");
        log.debug("hello");
        return Result.success().data("hello", "hello: ");
    }

    @ApiOperation(value = "testConfigureMessageConverters", tags = "测试长数据转换")
    @PostMapping("/long")
    public Result testBigInteger(@RequestBody Long num) {
        System.out.println(num);
        return Result.success().data("number", num);
    }
}
