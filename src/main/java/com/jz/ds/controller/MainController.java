package com.jz.ds.controller;

import com.jz.ds.entity.CommodityCategoryEntity;
import com.jz.ds.entity.bo.MainCategory;
import com.myjdbc.core.util.StringUtil;
import com.myjdbc.jdbc.constants.OrderType;
import com.myjdbc.jdbc.core.service.BaseService;
import com.myjdbc.jdbc.core.service.CriteriaQuery;
import com.myjdbc.jdbc.core.service.impl.CriteriaQueryFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    BaseService baseService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(params = "main")
    public ModelAndView list(HttpServletRequest request) {
        //商品种类导航
        List<MainCategory> mainCategoryList = new ArrayList<>();

        CriteriaQuery criteriaQuery = CriteriaQueryFactory.creatCriteriaQuery(CommodityCategoryEntity.class);
        criteriaQuery.setOrder(OrderType.ASC, "id");
        List<CommodityCategoryEntity> list = baseService.findAll(criteriaQuery);
        for (CommodityCategoryEntity commodityCategoryEntity : list) {
            String type = commodityCategoryEntity.getId().substring(2);
            if (type.equals("0000")) {
                //一级
                MainCategory mainCategory1 = new MainCategory(commodityCategoryEntity.getId(), commodityCategoryEntity.getCategoryName());//一条新的一级种类
                mainCategoryList.add(mainCategory1);
                continue;
            } else if (type.substring(2).equals("00")) {
                //二级
                MainCategory mainCategory1 = mainCategoryList.get(mainCategoryList.size() - 1);//取最新一条
                MainCategory mainCategory2 = new MainCategory(commodityCategoryEntity.getId(), commodityCategoryEntity.getCategoryName());
                if (mainCategory1.getChild() == null) {
                    mainCategory1.setChild(new ArrayList<MainCategory>());
                }
                mainCategory1.getChild().add(mainCategory2);
            } else {
                //三级
                MainCategory mainCategory1 = mainCategoryList.get(mainCategoryList.size() - 1);//取最新一条
                MainCategory mainCategory2 = mainCategory1.getChild().get(mainCategory1.getChild().size() - 1);//取最新一条
                MainCategory mainCategory3 = new MainCategory(commodityCategoryEntity.getId(), commodityCategoryEntity.getCategoryName());
                if (mainCategory2.getChild() == null) {
                    mainCategory2.setChild(new ArrayList<MainCategory>());
                }
                mainCategory2.getChild().add(mainCategory3);
            }
        }

        request.setAttribute("mainCategoryList", mainCategoryList);
        return new ModelAndView("com/myjdbc/dsshow/index");
    }

    /**
     * 商品搜索页跳转
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(params = "varieties")
    public ModelAndView varieties(String id, HttpServletRequest request) {
        //搜索种类
        CriteriaQuery criteriaQuery = CriteriaQueryFactory.creatCriteriaQuery(CommodityCategoryEntity.class);
        criteriaQuery.in("id", StringUtil.leftSubstring(id, 2) + "0000", StringUtil.leftSubstring(id, 4) + "00", id);
        criteriaQuery.setOrder(OrderType.ASC, "id");
        List<CommodityCategoryEntity> searchTypeList = baseService.findAll(criteriaQuery);
        request.setAttribute("searchTypeList", searchTypeList);


        return new ModelAndView("com/myjdbc/dsshow/search");
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


}
