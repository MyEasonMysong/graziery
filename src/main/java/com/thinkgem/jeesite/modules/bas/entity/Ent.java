/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;

/**
 * 企业表生成Entity
 * @author sr
 * @version 2019-01-23
 */
public class Ent extends DataEntity<Ent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 指标名称
	private String industryTypeCode;		// 适用行业
	private String creditCode;		// 企业统一信用代码
	private String areaCode;		// 属地
	private String postCode;		// 邮政编码
	private String address;		// 地址
	private String legalPersonName;		// 法人
	private String legalPersonDuty;		// 职务
	private String legalPersonTel;		// 联系电话
	private String principalPersonName;		// 负责人
	private String principalPersonDuty;		// 职务
	private String principalPersonTel;		// 联系电话
	private Area area;
	private String ids;//
	
	public String getIds() {
	    return ids;
	}

	public void setIds(String ids) {
	    this.ids = ids;
	}

	public Area getArea() {
	    return area;
	}

	public void setArea(Area area) {
	    this.area = area;
	}

	public Ent() {
		super();
	}

	public Ent(String id){
		super(id);
	}

	@Length(min=0, max=50, message="指标名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="适用行业长度必须介于 0 和 50 之间")
	public String getIndustryTypeCode() {
		return industryTypeCode;
	}

	public void setIndustryTypeCode(String industryTypeCode) {
		this.industryTypeCode = industryTypeCode;
	}
	
	@Length(min=0, max=50, message="企业统一信用代码长度必须介于 0 和 50 之间")
	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	@Length(min=0, max=50, message="属地长度必须介于 0 和 50 之间")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Length(min=0, max=50, message="邮政编码长度必须介于 0 和 50 之间")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Length(min=0, max=50, message="地址长度必须介于 0 和 50 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="法人长度必须介于 0 和 50 之间")
	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	
	@Length(min=0, max=50, message="职务长度必须介于 0 和 50 之间")
	public String getLegalPersonDuty() {
		return legalPersonDuty;
	}

	public void setLegalPersonDuty(String legalPersonDuty) {
		this.legalPersonDuty = legalPersonDuty;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getLegalPersonTel() {
		return legalPersonTel;
	}

	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}
	
	@Length(min=0, max=50, message="负责人长度必须介于 0 和 50 之间")
	public String getPrincipalPersonName() {
		return principalPersonName;
	}

	public void setPrincipalPersonName(String principalPersonName) {
		this.principalPersonName = principalPersonName;
	}
	
	@Length(min=0, max=50, message="职务长度必须介于 0 和 50 之间")
	public String getPrincipalPersonDuty() {
		return principalPersonDuty;
	}

	public void setPrincipalPersonDuty(String principalPersonDuty) {
		this.principalPersonDuty = principalPersonDuty;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getPrincipalPersonTel() {
		return principalPersonTel;
	}

	public void setPrincipalPersonTel(String principalPersonTel) {
		this.principalPersonTel = principalPersonTel;
	}
	
}