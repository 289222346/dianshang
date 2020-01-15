package com.jz.ds.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import org.hibernate.annotations.GenericGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: commodity_category
 * @date 2020-01-10 21:13:27
 */
@Entity
@Table(name = "commodity_category", schema = "")
@SuppressWarnings("serial")
public class CommodityCategoryEntity implements java.io.Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 种类名称
     */
    @Excel(name = "种类名称", width = 15)
    private String categoryName;

    /**
     * 上级种类
     */
    private String pname;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  id
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  种类名称
     */

    @Column(name = "CATEGORY_NAME", nullable = true, length = 50)
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  种类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Transient
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}