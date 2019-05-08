/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 检查体系表生成Entity
 * @author sr
 * @version 2019-01-23
 */
public class CheckSystem extends DataEntity<CheckSystem> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 指标体系名称
	private String checkTypeCode;		// 检查类型 日常、专项
	private String industryTypeCode;		// 适用行业
	private String itemAmount;		// 检查项数量
	private String itemSubAmount;		// 检查内容数量
	private String useFlag;		// 使用标示 0=未使用 1=已使用

	// 临时字段
	private String itemHead;		// 树形结构第一层数据 （父）
	private String itemBody;		// 树形结构第二层数据	（子）

	public CheckSystem() {
		super();
	}

	public CheckSystem(String id){
		super(id);
	}

	@Length(min=0, max=50, message="指标体系名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="检查类型 日常、专项长度必须介于 0 和 50 之间")
	public String getCheckTypeCode() {
		return checkTypeCode;
	}

	public void setCheckTypeCode(String checkTypeCode) {
		this.checkTypeCode = checkTypeCode;
	}
	
	@Length(min=0, max=50, message="适用行业长度必须介于 0 和 50 之间")
	public String getIndustryTypeCode() {
		return industryTypeCode;
	}

	public void setIndustryTypeCode(String industryTypeCode) {
		this.industryTypeCode = industryTypeCode;
	}
	
	@Length(min=0, max=11, message="检查项数量长度必须介于 0 和 11 之间")
	public String getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}
	
	@Length(min=0, max=11, message="检查内容数量长度必须介于 0 和 11 之间")
	public String getItemSubAmount() {
		return itemSubAmount;
	}

	public void setItemSubAmount(String itemSubAmount) {
		this.itemSubAmount = itemSubAmount;
	}
	
	@Length(min=0, max=1, message="使用标示 0=未使用 1=已使用长度必须介于 0 和 1 之间")
	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}


	public String getItemHead() {
		return itemHead;
	}

	public void setItemHead(String itemHead) {
		this.itemHead = itemHead;
	}


	public String getItemBody() {
		return itemBody;
	}

	public void setItemBody(String itemBody) {
		this.itemBody = itemBody;
	}
	
}