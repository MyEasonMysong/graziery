/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 检查体系-子表生成Entity
 * @author sr
 * @version 2019-01-23
 */
public class CheckSystemSub extends DataEntity<CheckSystemSub> {
	
	private static final long serialVersionUID = 1L;
	private String checkSystemId;		// 检查体系id
	private String itemId;		// 指标项id
	private String itemParentId;		// 指标项父id
	private String checkMethod;		// 检查方法
	
	public CheckSystemSub() {
		super();
	}

	public CheckSystemSub(String id){
		super(id);
	}

	@Length(min=0, max=50, message="检查体系id长度必须介于 0 和 50 之间")
	public String getCheckSystemId() {
		return checkSystemId;
	}

	public void setCheckSystemId(String checkSystemId) {
		this.checkSystemId = checkSystemId;
	}
	
	@Length(min=0, max=50, message="指标项id长度必须介于 0 和 50 之间")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Length(min=0, max=50, message="指标项父id长度必须介于 0 和 50 之间")
	public String getItemParentId() {
		return itemParentId;
	}

	public void setItemParentId(String itemParentId) {
		this.itemParentId = itemParentId;
	}
	
	@Length(min=0, max=200, message="检查方法长度必须介于 0 和 200 之间")
	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	
}