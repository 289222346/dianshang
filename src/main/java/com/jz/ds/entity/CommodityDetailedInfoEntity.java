package com.jz.ds.entity;

import java.util.Date;
import java.lang.String;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: commodity_detailed_info
 * @author onlineGenerator
 * @date 2020-01-09 00:00:18
 * @version V1.0
 *
 */
@Entity
@Table(name = "commodity_detailed_info", schema = "")
@SuppressWarnings("serial")
public class CommodityDetailedInfoEntity implements java.io.Serializable {
	/**id*/
	private String id;
	/**生产企业*/
	@Excel(name="生产企业",width=15)
	private String manufactor;
	/**产品规格*/
	@Excel(name="产品规格",width=15)
	private String specification;
	/**产品件装*/
	@Excel(name="产品件装",width=15)
	private String countInfo;
	/**产品效期*/
	@Excel(name="产品效期",width=15,format = "yyyy-MM-dd")
	private Date validityPeriod;
	/**批准文号*/
	@Excel(name="批准文号",width=15)
	private String approvalNumber;
	/**详情图片文件夹地址*/
	@Excel(name="详情图片文件夹地址",width=15)
	private String imagePath;
	/**说明书文件地址*/
	@Excel(name="说明书文件地址",width=15)
	private String instructionsUrl;

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
	 *@return: java.lang.String  生产企业
	 */

	@Column(name ="MANUFACTOR",nullable=true)
	public String getManufactor(){
		return this.manufactor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产企业
	 */
	public void setManufactor(String manufactor){
		this.manufactor = manufactor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品规格
	 */

	@Column(name ="SPECIFICATION",nullable=true)
	public String getSpecification(){
		return this.specification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品规格
	 */
	public void setSpecification(String specification){
		this.specification = specification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品件装
	 */

	@Column(name ="COUNT_INFO",nullable=true,length=128)
	public String getCountInfo(){
		return this.countInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品件装
	 */
	public void setCountInfo(String countInfo){
		this.countInfo = countInfo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  产品效期
	 */

	@Column(name ="VALIDITY_PERIOD",nullable=true)
	public Date getValidityPeriod(){
		return this.validityPeriod;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  产品效期
	 */
	public void setValidityPeriod(Date validityPeriod){
		this.validityPeriod = validityPeriod;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批准文号
	 */

	@Column(name ="APPROVAL_NUMBER",nullable=true)
	public String getApprovalNumber(){
		return this.approvalNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准文号
	 */
	public void setApprovalNumber(String approvalNumber){
		this.approvalNumber = approvalNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详情图片文件夹地址
	 */

	@Column(name ="IMAGE_PATH",nullable=true)
	public String getImagePath(){
		return this.imagePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详情图片文件夹地址
	 */
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  说明书文件地址
	 */

	@Column(name ="INSTRUCTIONS_URL",nullable=true)
	public String getInstructionsUrl(){
		return this.instructionsUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  说明书文件地址
	 */
	public void setInstructionsUrl(String instructionsUrl){
		this.instructionsUrl = instructionsUrl;
	}
}