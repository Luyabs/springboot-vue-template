package com.example.abs.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.abs.common.exception.exception.NotExistException;

import java.io.Serializable;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T>{
    @Override
    public T getByIdNotNull(Serializable id) {
        T t = getBaseMapper().selectById(id);
        if (t == null) {
            NotExistException.throwException(id, getEntityClass().getSimpleName());
        }
        return t;
    }

    @Override
    public boolean updateByIdNotNull(Serializable id, T entity) {
        this.getByIdNotNull(id);
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    @Override
    public boolean removeByIdNotNull(Serializable id) {
        this.getByIdNotNull(id);
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }
}
