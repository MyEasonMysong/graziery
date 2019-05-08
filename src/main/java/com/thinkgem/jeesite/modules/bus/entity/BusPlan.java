/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查计划Entity
 * @author sr
 * @version 2019-03-27
 */
public class BusPlan extends DataEntity<BusPlan> {
	
	private static final long serialVersionUID = 1L;
	private Integer planYear;		// 年度
	private Integer checkNum;		// 计划检查企业数量
	private Office office;		// 计划单位主键
	private Integer planStatus;		// 状态  1.进行中  0.已结束
	private String noCheckNum;		// 未检查家数
	private List<BusPlanDetail> list = new ArrayList<BusPlanDetail>();

	public List<BusPlanDetail> getList() {
		return list;
	}

	public void setList(List<BusPlanDetail> list) {
		this.list = list;
	}
	public BusPlan() {
		super();
	}

	public BusPlan(String id){
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