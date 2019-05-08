/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 现场检查方案Entity
 * @author wl
 * @version 2019-03-28
 */
public class BusProgramme extends DataEntity<BusProgramme> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 计划表id
	private String taskId;		// 任务表id
	private String itemId;		// 指标表id
	private Integer planYear;	//所属月份
	private String name;		// 方案名称
	private String number;		// 方案编号
	private String industry;	//所属行业
	private String companyName;		// 被检查对象名称
	private String address;		// 地址
	private String linkman;		// 联系人
	private Date checkdate;		// 检查时间
	private String lawyer;		// 行政执法人员
	private String contents;		// 检查内容
	private String checkState;		//检查状态（0未检查，1已检查）
	private String checkType;		// 检查方式
	private String checkModel;		// 检查类型（0:日常 1：专项）
	private String examineOpinion;		// 审核意见
	private String approvalOpinion;		// 审批意见
	private String experts;		// 拟聘专家
	private String state;		// 状态值（0：未提交 10：未审核 20：未审批 30 ：通过）
	
	public BusProgramme() {
		super();
	}

	public BusProgramme(String id){
		super(id);
	}

	@Length(min=0, max=50, message="计划表id长度必须介于 0 和 50 之间")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=50, message="任务表id长度必须介于 0 和 50 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=50, message="指标表id长度必须介于 0 和 50 之间")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Length(min=0, max=4, message="指标表月份长度必须介于 0 和 4 之间")
	public Integer getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Integer planYear) {
		this.planYear = planYear;
	}

	@Length(min=0, max=200, message="方案名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="方案编号长度必须介于 0 和 50 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Length(min=0, max=50, message="方案编号长度必须介于 0 和 50 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Length(min=0, max=200, message="被检查对象名称长度必须介于 0 和 200 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=20, message="联系人长度必须介于 0 和 20 之间")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	
	@Length(min=0, max=50, message="行政执法人员长度必须介于 0 和 50 之间")
	public String getLawyer() {
		return lawyer;
	}

	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Length(min=0, max=50, message="检查方式长度必须介于 0 和 1 之间")
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	@Length(min=0, max=50, message="检查方式长度必须介于 0 和 50 之间")
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	@Length(min=0, max=1, message="检查类型（0:日常 1：专项）长度必须介于 0 和 1 之间")
	public String getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(String checkModel) {
		this.checkModel = checkModel;
	}
	
	@Length(min=0, max=200, message="审核意见长度必须介于 0 和 200 之间")
	public String getExamineOpinion() {
		return examineOpinion;
	}

	public void setExamineOpinion(String examineOpinion) {
		this.examineOpinion = examineOpinion;
	}
	
	@Length(min=0, max=200, message="审批意见长度必须介于 0 和 200 之间")
	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	
	@Length(min=0, max=50, message="拟聘专家长度必须介于 0 和 50 之间")
	public String getExperts() {
		return experts;
	}

	public void setExperts(String experts) {
		this.experts = experts;
	}
	
	@Length(min=0, max=2, message="状态值（0：未提交 10：未审核 20：未审批 30 ：通过）长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}