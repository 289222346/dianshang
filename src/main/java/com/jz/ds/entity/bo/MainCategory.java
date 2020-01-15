package com.jz.ds.entity.bo;

import java.util.List;

/**
 * 首页分类实体
 */
public class MainCategory {

    public MainCategory(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    private String categoryId;

    private String categoryName;

    private List<MainCategory> child;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MainCategory> getChild() {
        return child;
    }

    public void setChild(List<MainCategory> child) {
        this.child = child;
    }
}
