/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专家库生成Entity
 * @author sr
 * @version 2019-01-23
 */
public class Expert extends DataEntity<Expert> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String specialty;		// 专业
	private String professionalTitle;		// 职称
	private String tel;		// 联系电话
	private String company;		// 工作单位
	
	public Expert() {
		super();
	}

	public Expert(String id){
		super(id);
	}

	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="专业长度必须介于 0 和 50 之间")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Length(min=0, max=50, message="职称长度必须介于 0 和 50 之间")
	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=50, message="工作单位长度必须介于 0 和 50 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}