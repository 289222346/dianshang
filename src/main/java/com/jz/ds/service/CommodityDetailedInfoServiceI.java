package com.jz.ds.service;
import com.jz.ds.entity.CommodityDetailedInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CommodityDetailedInfoServiceI extends CommonService{
	
 	public void delete(CommodityDetailedInfoEntity entity) throws Exception;
 	
 	public Serializable save(CommodityDetailedInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CommodityDetailedInfoEntity entity) throws Exception;
 	
}
