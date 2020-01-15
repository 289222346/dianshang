package com.jz.ds.service;
import com.jz.ds.entity.CommodityBaseInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CommodityBaseInfoServiceI extends CommonService{
	
 	public void delete(CommodityBaseInfoEntity entity) throws Exception;
 	
 	public Serializable save(CommodityBaseInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CommodityBaseInfoEntity entity) throws Exception;
 	
}
