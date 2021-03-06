package com.jz.ds.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jz.ds.entity.CommodityBaseInfoEntity;
import com.jz.ds.service.CommodityBaseInfoServiceI;
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

import java.util.List;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: commodity_base_info
 * @author onlineGenerator
 * @date 2020-01-09 00:00:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/commodityBaseInfoController")
public class CommodityBaseInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CommodityBaseInfoController.class);

	@Autowired
	private CommodityBaseInfoServiceI commodityBaseInfoService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * commodity_base_info列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jz/ds/commodityBaseInfoList");
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
	public void datagrid(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommodityBaseInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityBaseInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.commodityBaseInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除commodity_base_info
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		commodityBaseInfo = systemService.getEntity(CommodityBaseInfoEntity.class, commodityBaseInfo.getId());
		message = "commodity_base_info删除成功";
		try{
			commodityBaseInfoService.delete(commodityBaseInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_base_info删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除commodity_base_info
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_base_info删除成功";
		try{
			for(String id:ids.split(",")){
				CommodityBaseInfoEntity commodityBaseInfo = systemService.getEntity(CommodityBaseInfoEntity.class, 
				id
				);
				commodityBaseInfoService.delete(commodityBaseInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_base_info删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加commodity_base_info
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_base_info添加成功";
		try{
			commodityBaseInfoService.save(commodityBaseInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "commodity_base_info添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新commodity_base_info
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "commodity_base_info更新成功";
		CommodityBaseInfoEntity t = commodityBaseInfoService.get(CommodityBaseInfoEntity.class, commodityBaseInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(commodityBaseInfo, t);
			commodityBaseInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "commodity_base_info更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * commodity_base_info新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityBaseInfo.getId())) {
			commodityBaseInfo = commodityBaseInfoService.getEntity(CommodityBaseInfoEntity.class, commodityBaseInfo.getId());
			req.setAttribute("commodityBaseInfo", commodityBaseInfo);
		}
		return new ModelAndView("com/jz/ds/commodityBaseInfo-add");
	}
	/**
	 * commodity_base_info编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CommodityBaseInfoEntity commodityBaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(commodityBaseInfo.getId())) {
			commodityBaseInfo = commodityBaseInfoService.getEntity(CommodityBaseInfoEntity.class, commodityBaseInfo.getId());
			req.setAttribute("commodityBaseInfo", commodityBaseInfo);
		}
		return new ModelAndView("com/jz/ds/commodityBaseInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","commodityBaseInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CommodityBaseInfoEntity commodityBaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CommodityBaseInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityBaseInfo, request.getParameterMap());
		List<CommodityBaseInfoEntity> commodityBaseInfos = this.commodityBaseInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_base_info");
		modelMap.put(NormalExcelConstants.CLASS,CommodityBaseInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("commodity_base_info列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,commodityBaseInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CommodityBaseInfoEntity commodityBaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"commodity_base_info");
    	modelMap.put(NormalExcelConstants.CLASS,CommodityBaseInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("commodity_base_info列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CommodityBaseInfoEntity> listCommodityBaseInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CommodityBaseInfoEntity.class,params);
				for (CommodityBaseInfoEntity commodityBaseInfo : listCommodityBaseInfoEntitys) {
					commodityBaseInfoService.save(commodityBaseInfo);
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
	
	
}
