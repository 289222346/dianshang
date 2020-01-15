package com.jz.ds.entity;

import java.util.Date;
import java.lang.String;
import java.lang.Double;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: commodity_base_info
 * @author onlineGenerator
 * @date 2020-01-09 00:00:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "commodity_base_info", schema = "")
@SuppressWarnings("serial")
public class CommodityBaseInfoEntity implements java.io.Serializable {
	/**id*/
	private String id;
	/**商品名称*/
	@Excel(name="商品名称",width=15)
	private String commodityName;
	/**商品种类ID*/
	@Excel(name="商品种类ID",width=15)
	private String categoryId;
	/**价格*/
	@Excel(name="价格",width=15)
	private Double price;
	/**主要图片地址*/
	@Excel(name="主要图片地址",width=15)
	private String imageUrl;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private Date createDate;
	/**更新人名称*/
	@Excel(name="更新人名称",width=15)
	private String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private String updateBy;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品名称
	 */

	@Column(name ="COMMODITY_NAME",nullable=true)
	public String getCommodityName(){
		return this.commodityName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setCommodityName(String commodityName){
		this.commodityName = commodityName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品种类ID
	 */

	@Column(name ="CATEGORY_ID",nullable=true,length=32)
	public String getCategoryId(){
		return this.categoryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品种类ID
	 */
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  价格
	 */

	@Column(name ="PRICE",nullable=true,length=22)
	public Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  价格
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主要图片地址
	 */

	@Column(name ="IMAGE_URL",nullable=true)
	public String getImageUrl(){
		return this.imageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主要图片地址
	 */
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
}