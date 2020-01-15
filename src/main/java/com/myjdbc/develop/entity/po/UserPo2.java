package com.myjdbc.develop.entity.po;

import com.myjdbc.datagrid.annotation.DatagridColumn;
import com.myjdbc.datagrid.annotation.DatagridTable;

@DatagridTable(index = 2)
public class UserPo2 {

    @DatagridColumn(title = "考勤", colspan = 2)
    private String kq;

    @DatagridColumn(title = "请假", colspan = 2)
    private String qj;

}
