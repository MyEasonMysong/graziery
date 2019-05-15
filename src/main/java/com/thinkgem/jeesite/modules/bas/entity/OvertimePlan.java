/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 加班申请Entity
 * @author lichao
 * @version 2019-05-15
 */
public class OvertimePlan extends DataEntity<OvertimePlan> {
	
	private static final long serialVersionUID = 1L;
	private String applyBy;		// 申请人
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String duration;		// 加班时长
	private String overtimeType;		// 加班类型
	private String project;		// 加班项目
	private String content;		// 加班内容
	private String approvalStatus;		// 申请状态
	
	public OvertimePlan() {
		super();
	}

	public OvertimePlan(String id){
		super(id);
	}

	@Length(min=0, max=64, message="申请人长度必须介于 0 和 64 之间")
	public String getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
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
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Length(min=0, max=20, message="加班类型长度必须介于 0 和 20 之间")
	public String getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(String overtimeType) {
		this.overtimeType = overtimeType;
	}
	
	@Length(min=0, max=64, message="加班项目长度必须介于 0 和 64 之间")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	@Length(min=0, max=255, message="加班内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="申请状态长度必须介于 0 和 1 之间")
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}