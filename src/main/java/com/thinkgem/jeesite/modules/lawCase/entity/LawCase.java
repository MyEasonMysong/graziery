/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lawCase.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 案件Entity
 * @author sr
 * @version 2019-03-27
 */
public class LawCase extends DataEntity<LawCase> {

	private static final long serialVersionUID = 1L;
	private Integer planYear;		// 年度
	private Integer checkNum;		// 计划检查企业数量
	private Office office;		// 计划单位主键
	private Integer planStatus;		// 状态  1.进行中  0.已结束
	private String noCheckNum;		// 未检查家数
	public LawCase() {
		super();
	}

	public LawCase(String id){
		super(id);
	}

	public Integer getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Integer planYear) {
		this.planYear = planYear;
	}
	
	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}

	public String getNoCheckNum() {
		return noCheckNum;
	}

	public void setNoCheckNum(String noCheckNum) {
		this.noCheckNum = noCheckNum;
	}
}