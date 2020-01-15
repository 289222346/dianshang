package com.jz.ds.service;

import com.jeecg.myjeecg.service.MyBaseServiceI;
import com.jz.ds.entity.CommodityCategoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CommodityCategoryServiceI extends MyBaseServiceI {

    public void delete(CommodityCategoryEntity entity) throws Exception;

    public Serializable save(CommodityCategoryEntity entity) throws Exception;

    public void saveOrUpdate(CommodityCategoryEntity entity) throws Exception;

}
