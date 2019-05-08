/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 检查计划子表Entity
 * @author sr
 * @version 2019-03-27
 */
public class BusPlanDetail extends DataEntity<BusPlanDetail> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 计划表主键
	private Integer planYear;		// 计划年份
	private Integer planMonth;		// 计划月份
	private String entId;		// 检查对象主键
	private String items;		// 检查主要事项
	private String checkTypeCode;		// 检查主要事项
	private String dutyTypeCode;		// 职责分工
	
	public BusPlanDetail() {
		super();
	}

	public BusPlanDetail(String id){
		super(id);
	}

	@Length(min=0, max=50, message="计划表主键长度必须介于 0 和 50 之间")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public Integer getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Integer planYear) {
		this.planYear = planYear;
	}
	
	public Integer getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(Integer planMonth) {
		this.planMonth = planMonth;
	}
	
	@Length(min=0, max=50, message="检查对象主键长度必须介于 0 和 50 之间")
	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}
	
	@Length(min=0, max=200, message="检查主要事项长度必须介于 0 和 200 之间")
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
	@Length(min=0, max=200, message="检查主要事项长度必须介于 0 和 200 之间")
	public String getCheckTypeCode() {
		return checkTypeCode;
	}

	public void setCheckTypeCode(String checkTypeCode) {
		this.checkTypeCode = checkTypeCode;
	}
	
	@Length(min=0, max=200, message="职责分工长度必须介于 0 和 200 之间")
	public String getDutyTypeCode() {
		return dutyTypeCode;
	}

	public void setDutyTypeCode(String dutyTypeCode) {
		this.dutyTypeCode = dutyTypeCode;
	}
	
}