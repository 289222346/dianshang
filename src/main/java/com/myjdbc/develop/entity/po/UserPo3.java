package com.myjdbc.develop.entity.po;

import com.myjdbc.datagrid.annotation.DatagridColumn;
import com.myjdbc.datagrid.annotation.DatagridTable;

@DatagridTable(index = 1)
public class UserPo3 extends UserPo2 {

    @DatagridColumn(title = "唯一标识", rowspan = 3)
    private String id;

    @DatagridColumn(title = "基本信息", rowspan = 2, colspan = 3)
    private String jbxx;

    @DatagridColumn(title = "职位", rowspan = 2, colspan = 2)
    private String zw;

    @DatagridColumn(title = "考勤登记", colspan = 4)
    private String kqdj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
