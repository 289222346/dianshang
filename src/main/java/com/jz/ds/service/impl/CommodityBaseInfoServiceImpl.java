package com.jz.ds.service.impl;
import com.jz.ds.entity.CommodityBaseInfoEntity;
import com.jz.ds.service.CommodityBaseInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("commodityBaseInfoService")
@Transactional
public class CommodityBaseInfoServiceImpl extends CommonServiceImpl implements CommodityBaseInfoServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(CommodityBaseInfoEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(CommodityBaseInfoEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(CommodityBaseInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}