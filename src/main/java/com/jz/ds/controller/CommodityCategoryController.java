package com.jz.ds.controller;

import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jz.ds.entity.CommodityCategoryEntity;
import com.jz.ds.service.CommodityCategoryServiceI;
import com.myjdbc.jdbc.core.service.BaseService;
import com.myjdbc.jdbc.core.service.CriteriaQuery;
import com.myjdbc.jdbc.core.service.impl.CriteriaQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
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
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: commodity_category
 * @date 2020-01-08 23:59:56
 */
@Api(value = "CommodityCategory", description = "commodity_category", tags = "commodityCategoryController")
@Controller
@RequestMapping("/commodityCategoryController")
public class CommodityCategoryController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommodityCategoryController.class);

    @Autowired
    private CommodityCategoryServiceI commodityCategoryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @Autowired
    private BaseService baseService;


    /**
     * commodity_category列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jz/ds/commodityCategoryList");
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
    public void datagrid(CommodityCategoryEntity commodityCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = CriteriaQueryFactory.creatCriteriaQuery(CommodityCategoryEntity.class, commodityCategory);

//            List<CommodityCategoryEntity> list = baseService.findAll(cq);


//        this.commodityCategoryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除commodity_category
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(CommodityCategoryEntity commodityCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        commodityCategory = systemService.getEntity(CommodityCategoryEntity.class, commodityCategory.getId());
        message = "commodity_category删除成功";
        try {
            commodityCategoryService.delete(commodityCategory);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "commodity_category删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除commodity_category
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "commodity_category删除成功";
        try {
            for (String id : ids.split(",")) {
                CommodityCategoryEntity commodityCategory = systemService.getEntity(CommodityCategoryEntity.class,
                        id
                );
                commodityCategoryService.delete(commodityCategory);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "commodity_category删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加commodity_category
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(CommodityCategoryEntity commodityCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "commodity_category添加成功";
        try {
            commodityCategoryService.save(commodityCategory);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "commodity_category添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新commodity_category
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(CommodityCategoryEntity commodityCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "commodity_category更新成功";
        CommodityCategoryEntity t = commodityCategoryService.findById(CommodityCategoryEntity.class, commodityCategory.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(commodityCategory, t);
            commodityCategoryService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "commodity_category更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * commodity_category新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(CommodityCategoryEntity commodityCategory, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(commodityCategory.getId())) {
            commodityCategory = commodityCategoryService.findById(CommodityCategoryEntity.class, commodityCategory.getId());
            req.setAttribute("commodityCategory", commodityCategory);
        }
        return new ModelAndView("com/jz/ds/commodityCategory-add");
    }

    /**
     * commodity_category编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(CommodityCategoryEntity commodityCategory, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(commodityCategory.getId())) {
            commodityCategory = commodityCategoryService.findById(CommodityCategoryEntity.class, commodityCategory.getId());
            req.setAttribute("commodityCategory", commodityCategory);
        }
        return new ModelAndView("com/jz/ds/commodityCategory-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "commodityCategoryController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
//    @RequestMapping(params = "exportXls")
//    public String exportXls(CommodityCategoryEntity commodityCategory, HttpServletRequest request, HttpServletResponse response
//            , DataGrid dataGrid, ModelMap modelMap) {
//        CriteriaQuery cq = new CriteriaQuery(CommodityCategoryEntity.class, dataGrid);
//        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, commodityCategory, request.getParameterMap());
//        List<CommodityCategoryEntity> commodityCategorys = this.commodityCategoryService.getListByCriteriaQuery(cq, false);
//        modelMap.put(NormalExcelConstants.FILE_NAME, "commodity_category");
//        modelMap.put(NormalExcelConstants.CLASS, CommodityCategoryEntity.class);
//        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("commodity_category列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
//                "导出信息"));
//        modelMap.put(NormalExcelConstants.DATA_LIST, commodityCategorys);
//        return NormalExcelConstants.JEECG_EXCEL_VIEW;
//    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(CommodityCategoryEntity commodityCategory, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put(NormalExcelConstants.FILE_NAME, "commodity_category");
        modelMap.put(NormalExcelConstants.CLASS, CommodityCategoryEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("commodity_category列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
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
                List<CommodityCategoryEntity> listCommodityCategoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(), CommodityCategoryEntity.class, params);
                for (CommodityCategoryEntity commodityCategory : listCommodityCategoryEntitys) {
                    commodityCategoryService.save(commodityCategory);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }


//    @RequestMapping(value = "/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation(value = "commodity_category列表信息", produces = "application/json", httpMethod = "GET")
//    public ResponseMessage<List<CommodityCategoryEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
//        if (pageSize > Globals.MAX_PAGESIZE) {
//            return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
//        }
//        CriteriaQuery query = new CriteriaQuery(CommodityCategoryEntity.class);
//        query.setCurPage(pageNo <= 0 ? 1 : pageNo);
//        query.setPageSize(pageSize < 1 ? 1 : pageSize);
//        List<CommodityCategoryEntity> listCommodityCategorys = this.commodityCategoryService.getListByCriteriaQuery(query, true);
//        return Result.success(listCommodityCategorys);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID获取commodity_category信息", notes = "根据ID获取commodity_category信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        CommodityCategoryEntity task = commodityCategoryService.findById(CommodityCategoryEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取commodity_category信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "创建commodity_category")
    public ResponseMessage<?> create(@ApiParam(name = "commodity_category对象") @RequestBody CommodityCategoryEntity commodityCategory, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<CommodityCategoryEntity>> failures = validator.validate(commodityCategory);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }

        //保存
        try {
            commodityCategoryService.save(commodityCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("commodity_category信息保存失败");
        }
        return Result.success(commodityCategory);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "更新commodity_category", notes = "更新commodity_category")
    public ResponseMessage<?> update(@ApiParam(name = "commodity_category对象") @RequestBody CommodityCategoryEntity commodityCategory) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<CommodityCategoryEntity>> failures = validator.validate(commodityCategory);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }

        //保存
        try {
            commodityCategoryService.saveOrUpdate(commodityCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新commodity_category信息失败");
        }

        //按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return Result.success("更新commodity_category信息成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "删除commodity_category")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]", id);
        // 验证
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
//            commodityCategoryService.delete(CommodityCategoryEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("commodity_category删除失败");
        }

        return Result.success();
    }
}
