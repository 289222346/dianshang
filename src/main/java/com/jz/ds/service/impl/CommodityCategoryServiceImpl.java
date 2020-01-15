package com.jz.ds.service.impl;

import com.jeecg.myjeecg.service.MyBaseServiceI;
import com.jeecg.myjeecg.service.impl.MyBaseServiceImpl;
import com.jz.ds.entity.CommodityCategoryEntity;
import com.jz.ds.service.CommodityCategoryServiceI;
import com.myjdbc.jdbc.core.service.BaseService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("commodityCategoryService")
@Transactional
public class CommodityCategoryServiceImpl extends MyBaseServiceImpl implements CommodityCategoryServiceI {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void delete(CommodityCategoryEntity entity) throws Exception {
        super.delete(entity);
    }

    public Serializable save(CommodityCategoryEntity entity) throws Exception {
        Serializable t = super.save(entity);
        return t;
    }

    public void saveOrUpdate(CommodityCategoryEntity entity) throws Exception {
        super.save(entity);
    }

}