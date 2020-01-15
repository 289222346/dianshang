package com.jz.ds.controller;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jz.ds.entity.CommodityDetailedInfoEntity;
import com.jz.ds.service.CommodityDetailedInfoServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;


import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: commodity_detailed_info
 * @author onlineGenerator
 * @date 2020-01-09 00:00:18
 * @version V1.0   
 *
 */
@Api(value="CommodityDetailedInfo",description="commodity_detailed_info",tags="commodityDetailedInfoController")
@Controller
@RequestMapping("/commodityDetailedInfoController")
public class CommodityDetailedInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CommodityDetailedInfoController.class);

	@Autowired
	private CommodityDetailedInfoServiceI commodityDetailedInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * commodity_detailed_info列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jz/ds/commodityDetailedInfoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityDetailedInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityDetailedInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.commodityDetailedInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除commodity_detailed_info
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		commodityDetailedInfo = systemService.getEntity(CommodityDetailedInfoEntity.class, commodityDetailedInfo.getId());
		message = "commodity_detailed_info删除成功";
		try{
			commodityDetailedInfoService.delete(commodityDetailedInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_detailed_info删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除commodity_detailed_info
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_detailed_info删除成功";
		try{
			for(String id:ids.split(",")){
				CommodityDetailedInfoEntity commodityDetailedInfo = systemService.getEntity(CommodityDetailedInfoEntity.class, 
				id
				);
				commodityDetailedInfoService.delete(commodityDetailedInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_detailed_info删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加commodity_detailed_info
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_detailed_info添加成功";
		try{
			commodityDetailedInfoService.save(commodityDetailedInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_detailed_info添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新commodity_detailed_info
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_detailed_info更新成功";
		CommodityDetailedInfoEntity t = commodityDetailedInfoService.get(CommodityDetailedInfoEntity.class, commodityDetailedInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(commodityDetailedInfo, t);
			commodityDetailedInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "commodity_detailed_info更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * commodity_detailed_info新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityDetailedInfo.getId())) {
			commodityDetailedInfo = commodityDetailedInfoService.getEntity(CommodityDetailedInfoEntity.class, commodityDetailedInfo.getId());
			req.setAttribute("commodityDetailedInfo", commodityDetailedInfo);
		}
		return new ModelAndView("com/jz/ds/commodityDetailedInfo-add");
	}
	/**
	 * commodity_detailed_info编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CommodityDetailedInfoEntity commodityDetailedInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityDetailedInfo.getId())) {
			commodityDetailedInfo = commodityDetailedInfoService.getEntity(CommodityDetailedInfoEntity.class, commodityDetailedInfo.getId());
			req.setAttribute("commodityDetailedInfo", commodityDetailedInfo);
		}
		return new ModelAndView("com/jz/ds/commodityDetailedInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","commodityDetailedInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CommodityDetailedInfoEntity commodityDetailedInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CommodityDetailedInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityDetailedInfo, request.getParameterMap());
		List<CommodityDetailedInfoEntity> commodityDetailedInfos = this.commodityDetailedInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_detailed_info");
		modelMap.put(NormalExcelConstants.CLASS,CommodityDetailedInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("commodity_detailed_info列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,commodityDetailedInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CommodityDetailedInfoEntity commodityDetailedInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_detailed_info");
    	modelMap.put(NormalExcelConstants.CLASS,CommodityDetailedInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("commodity_detailed_info列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<CommodityDetailedInfoEntity> listCommodityDetailedInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CommodityDetailedInfoEntity.class,params);
				for (CommodityDetailedInfoEntity commodityDetailedInfo : listCommodityDetailedInfoEntitys) {
					commodityDetailedInfoService.save(commodityDetailedInfo);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="commodity_detailed_info列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<CommodityDetailedInfoEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(CommodityDetailedInfoEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<CommodityDetailedInfoEntity> listCommodityDetailedInfos = this.commodityDetailedInfoService.getListByCriteriaQuery(query,true);
		return Result.success(listCommodityDetailedInfos);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取commodity_detailed_info信息",notes="根据ID获取commodity_detailed_info信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		CommodityDetailedInfoEntity task = commodityDetailedInfoService.get(CommodityDetailedInfoEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取commodity_detailed_info信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建commodity_detailed_info")
	public ResponseMessage<?> create(@ApiParam(name="commodity_detailed_info对象")@RequestBody CommodityDetailedInfoEntity commodityDetailedInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CommodityDetailedInfoEntity>> failures = validator.validate(commodityDetailedInfo);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			commodityDetailedInfoService.save(commodityDetailedInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("commodity_detailed_info信息保存失败");
		}
		return Result.success(commodityDetailedInfo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新commodity_detailed_info",notes="更新commodity_detailed_info")
	public ResponseMessage<?> update(@ApiParam(name="commodity_detailed_info对象")@RequestBody CommodityDetailedInfoEntity commodityDetailedInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CommodityDetailedInfoEntity>> failures = validator.validate(commodityDetailedInfo);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			commodityDetailedInfoService.saveOrUpdate(commodityDetailedInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新commodity_detailed_info信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新commodity_detailed_info信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除commodity_detailed_info")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			commodityDetailedInfoService.deleteEntityById(CommodityDetailedInfoEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("commodity_detailed_info删除失败");
		}

		return Result.success();
	}
}
