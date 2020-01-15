package com.myjdbc.datagrid.entity;

/**
 * @Author 陈文
 * @Date 2019/12/13  13:07
 * @Description EsayUI-DataGrid封装类
 */
public class TableColumn {

    public TableColumn() {
    }

    public TableColumn(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public TableColumn(String id, Integer width, String title) {
        this.id = id;
        this.width = width;
        this.title = title;
    }

    /**
     * 列ID
     */
    private String id;

    /**
     * 列名称
     */
    private String title;

    /**
     * Indicate how many rows a cell should take up.
     * 指示单元格应占用的行数。
     */
    private int rowspan = 1;

    /**
     * Indicate how many columns a cell should take up.
     * 指示单元格应占用的列数。
     */
    private int colspan = 1;

    /**
     * The width of column. If not defined, the width will auto expand to fit its contents.
     * No width definition will reduce performance.
     * 列的宽度。如果未定义，则宽度将自动展开以适合其内容。没有宽度定义将降低性能。
     */
    private Integer width = 100;


    /**
     * Indicate how to align the column data. 'left','right','center' can be used.
     * 指示如何对齐列数据可以使用“左”、“右”、“中”。
     */
    private String align = "center";

    /**
     * True to hide the column.
     * 如果为True，则隐藏列。
     */
    private Boolean hidden = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}
