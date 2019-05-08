/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.entity;

import com.thinkgem.jeesite.modules.bas.entity.CheckSystem;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专项任务Entity
 * @author zmy
 * @version 2019-03-27
 */
public class BusTask extends DataEntity<BusTask> {
	
	private static final long serialVersionUID = 1L;
	private String taskName;		// 任务名称
	private Integer taskYear;		// 年度
	private String taskLevel;		// 专项任务级别
	private Date beginDate;		// 任务开始时间
	private Date endDate;		// 任务结束时间
	private String chargeOfficeId;		// 负责科室
	private String partakeOfficeId;		// 参与科室
	private Integer checkNum;		// 计划检查企业数量
	private String checkSystemId;		// 检查体系id
	private Office charge;		// 负责科室
	private Office partake;		// 参与科室
	private CheckSystem checkSystem;
	public BusTask() {
		super();
	}

	public BusTask(String id){
		super(id);
	}

	@Length(min=0, max=50, message="任务名称长度必须介于 0 和 50 之间")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public Integer getTaskYear() {
		return taskYear;
	}

	public void setTaskYear(Integer taskYear) {
		this.taskYear = taskYear;
	}
	
	@Length(min=0, max=50, message="专项任务级别长度必须介于 0 和 50 之间")
	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=50, message="负责科室长度必须介于 0 和 50 之间")
	public String getChargeOfficeId() {
		return chargeOfficeId;
	}

	public void setChargeOfficeId(String chargeOfficeId) {
		this.chargeOfficeId = chargeOfficeId;
	}
	
	@Length(min=0, max=50, message="参与科室长度必须介于 0 和 50 之间")
	public String getPartakeOfficeId() {
		return partakeOfficeId;
	}

	public void setPartakeOfficeId(String partakeOfficeId) {
		this.partakeOfficeId = partakeOfficeId;
	}
	
	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	
	@Length(min=0, max=50, message="检查体系id长度必须介于 0 和 50 之间")
	public String getCheckSystemId() {
		return checkSystemId;
	}

	public void setCheckSystemId(String checkSystemId) {
		this.checkSystemId = checkSystemId;
	}

	public Office getCharge() {
		return charge;
	}

	public void setCharge(Office charge) {
		this.charge = charge;
	}

	public Office getPartake() {
		return partake;
	}

	public void setPartake(Office partake) {
		this.partake = partake;
	}

	public CheckSystem getCheckSystem() {
		return checkSystem;
	}

	public void setCheckSystem(CheckSystem checkSystem) {
		this.checkSystem = checkSystem;
	}
}