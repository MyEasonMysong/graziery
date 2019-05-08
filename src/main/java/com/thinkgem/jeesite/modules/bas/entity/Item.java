/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * 
 * @ClassName Item
 * @Description item实体类
 * @author lihongtao
 * @date 2019年1月29日
 *
 */
public class Item extends DataEntity<Item> {
	
	private static final long serialVersionUID = 1L;
	private Item parent;		// 父id
	private String name;		// 指标名称
	private String itemTypeCode;		// 指标类型
	private String checkBasis;		// 检查依据
	private String checkMethod;		// 检查方法
	private Integer itemSubAmount;		// 检查项数量
	private String useFlag;		// 使用标示 0=未使用 1=已使用


	private List<Item> list = Lists.newArrayList();  //存子项
	private String checked;		// 回显单选框用 是否被选中
	private String checkSystemId;		// 回显单选框用 根据此ID查询

	public Item() {
		super();
	}

	public Item(String id){
		super(id);
	}

	@JsonBackReference
	public Item getParent() {
		return parent;
	}

	public void setParent(Item parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=50, message="指标名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="指标类型长度必须介于 0 和 50 之间")
	public String getItemTypeCode() {
		return itemTypeCode;
	}

	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}
	
	@Length(min=0, max=50, message="检查依据长度必须介于 0 和 50 之间")
	public String getCheckBasis() {
		return checkBasis;
	}

	public void setCheckBasis(String checkBasis) {
		this.checkBasis = checkBasis;
	}
	
	@Length(min=0, max=50, message="检查方法长度必须介于 0 和 50 之间")
	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	
	public Integer getItemSubAmount() {
		return itemSubAmount;
	}

	public void setItemSubAmount(Integer itemSubAmount) {
		this.itemSubAmount = itemSubAmount;
	}
	
	@Length(min=0, max=1, message="使用标示 0=未使用 1=已使用长度必须介于 0 和 1 之间")
	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	@JsonIgnore
	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}


	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getCheckSystemId() {
		return checkSystemId;
	}

	public void setCheckSystemId(String checkSystemId) {
		this.checkSystemId = checkSystemId;
	}
}