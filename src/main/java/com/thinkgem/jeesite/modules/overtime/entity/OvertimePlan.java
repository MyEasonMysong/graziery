/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.overtime.entity;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 加班计划申请Entity
 * @author Xuyanyan
 * @version 2019-05-14
 */
public class OvertimePlan extends ActEntity<OvertimePlan> {
	
	private static final long serialVersionUID = 1L;
	private String officeId;
	private String applyUserId;		// apply_user_id
	private String applyUserName;		// apply_user_name
	private Date startTime;		// start_time
	private Date endTime;		// end_time
	private String duration;		// duration
	private String overTimeType;		// over_time_type
	private String projectName;		// project_name
	private String projectCode;		// project_code
	private String acceptor;		// acceptor
	private String jobContent;		// job_content
	private String approvalStatus;		// approval_status
	
	public OvertimePlan() {
		super();
	}

	public OvertimePlan(String id){
		super(id);
	}

	@Length(min=0, max=4000, message="apply_user_id长度必须介于 0 和 4000 之间")
	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	
	@Length(min=0, max=4000, message="apply_user_name长度必须介于 0 和 4000 之间")
	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=4000, message="duration长度必须介于 0 和 4000 之间")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Length(min=0, max=10, message="over_time_type长度必须介于 0 和 10 之间")
	public String getOverTimeType() {
		return overTimeType;
	}

	public void setOverTimeType(String overTimeType) {
		this.overTimeType = overTimeType;
	}
	
	@Length(min=0, max=10, message="project_name长度必须介于 0 和 10 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=50, message="project_code长度必须介于 0 和 50 之间")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	@Length(min=0, max=10, message="acceptor长度必须介于 0 和 10 之间")
	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	
	@Length(min=0, max=500, message="job_content长度必须介于 0 和 500 之间")
	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	
	@Length(min=0, max=10, message="approval_status长度必须介于 0 和 10 之间")
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
}