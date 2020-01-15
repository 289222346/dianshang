package com.myjdbc.develop.controller;

import com.myjdbc.datagrid.entity.DataGrid;
import com.myjdbc.develop.entity.po.UserPo;
import com.myjdbc.datagrid.entity.GridTable;
import com.myjdbc.datagrid.util.DatagridUtil;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/develop")
public class DevelopController extends BaseController {

    @RequestMapping(params = "dataGrid")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("com/myjdbc/core/datagrid");
        GridTable easyuiDatagridBo = new GridTable("list", "用户展示");

        easyuiDatagridBo.setColumns(DatagridUtil.getColumns(UserPo.class));

        easyuiDatagridBo.setPagination(true);
        easyuiDatagridBo.setFitColumns(true);
        modelAndView.addObject("datagrid", easyuiDatagridBo);
        return modelAndView;  //视图重定向hello.jsp
    }

    @RequestMapping(params = "list")
    public void hello(HttpServletResponse response, String[] fields) {
        List<UserPo> userPoList = new ArrayList<>();
        {
            UserPo po = new UserPo();
            po.setId("1001");
            po.setName("张三");
            po.setDept("财务部");
            userPoList.add(po);
        }

        {
            UserPo po = new UserPo();
            po.setId("1002");
            po.setName("李四");
            po.setDept("运维部");
            userPoList.add(po);
        }

        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(userPoList.size());
        dataGrid.setRows(userPoList);

        //输出数据表格
        DatagridUtil.printDataGrid(response, dataGrid);
    }

}
