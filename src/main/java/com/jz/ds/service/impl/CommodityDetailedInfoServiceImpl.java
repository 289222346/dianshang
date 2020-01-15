package com.jz.ds.service.impl;
import com.jz.ds.entity.CommodityDetailedInfoEntity;
import com.jz.ds.service.CommodityDetailedInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("commodityDetailedInfoService")
@Transactional
public class CommodityDetailedInfoServiceImpl extends CommonServiceImpl implements CommodityDetailedInfoServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(CommodityDetailedInfoEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(CommodityDetailedInfoEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(CommodityDetailedInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}