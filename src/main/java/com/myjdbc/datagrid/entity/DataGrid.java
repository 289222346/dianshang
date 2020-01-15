package com.myjdbc.datagrid.entity;

import java.util.List;

public class DataGrid {

    /**
     * 页面总数
     */
    private Integer total;

    /**
     * 页面数据（行）
     */
    private List rows;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
