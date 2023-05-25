package com.example.abs.common.base;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * Service基类 基于MP的IService二次开发
 * 当select获取空对象时将立刻抛掷异常
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 当查询为空时会报NotExist异常
     */
    T getByIdNotNull(Serializable id);

    /**
     * 当查询为空时会报NotExist异常
     * 请保证 id 与 entity中主键属性一致
     */
    boolean updateByIdNotNull(Serializable id, T entity);

    /**
     * 当查询为空时会报NotExist异常
     */
    boolean removeByIdNotNull(Serializable id);

}
