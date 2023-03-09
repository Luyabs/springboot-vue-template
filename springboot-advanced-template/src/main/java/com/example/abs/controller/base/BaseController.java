package com.example.abs.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.abs.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller公共类
 * @param <T> 实体类型
 * 这个公共类会占用
 * GET的 /all /{id} /page_without_condition
 * POST PUT的 /
 * DELETE的 /{id}
 * 因此在继承此类后 不该为这些路径设置对应的请求
 * 可以不加@RequestMapping重写这些方法
 */
public abstract class BaseController<T> {
    @SuppressWarnings("all")  // 会报找不到bean 但是子类继承后就可以正常完成依赖注入 可忽略此报错
    @Autowired
    private IService<T> baseService;

    @ApiOperation(value = "获取全部", notes = "无需参数")
    @GetMapping("/all")
    public Result getAll() {
        return Result.success().data("list", baseService.list());
    }

    @ApiOperation(value = "分页获取", notes = "需要传入当前页和页大小")
    @GetMapping("/page_without_condition")
    public Result getPage(int currentPage, int pageSize) {
        return Result.success().data("page", baseService.page(new Page<>(currentPage, pageSize)));
    }

    @ApiOperation(value = "按id获取", notes = "需传入id")
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        T entity = baseService.getById(id);
        return entity != null ? Result.success().data("one", entity) : Result.error().message("不存在id: " + id);
    }

    @ApiOperation(value = "新增", notes = "需要传入JSON")
    @PostMapping
    public Result add(@RequestBody T entity) {
        boolean flag = baseService.save(entity);
        return flag ? Result.success().message("增加成功") : Result.error().message("增加失败");
    }

    @ApiOperation(value = "修改", notes = "需要传入JSON")
    @PutMapping
    public Result edit(@RequestBody T entity) {
        boolean flag = baseService.updateById(entity);
        return flag ? Result.success().message("修改成功") : Result.error().message("修改失败");
    }

    @ApiOperation(value = "删除", notes = "需要传入路径变量id")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable int id) {
        boolean flag = baseService.removeById(id);
        return flag ? Result.success().message("删除成功") : Result.error().message("不存在id: " + id);
    }
}
