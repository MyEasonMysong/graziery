/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bus.entity.BusPlanDetail;
import com.thinkgem.jeesite.modules.bus.dao.BusPlanDetailDao;

/**
 * 检查计划子表Service
 * @author sr
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class BusPlanDetailService extends CrudService<BusPlanDetailDao, BusPlanDetail> {

	public BusPlanDetail get(String id) {
		return super.get(id);
	}
	
	public List<BusPlanDetail> findList(BusPlanDetail busPlanDetail) {
		return super.findList(busPlanDetail);
	}
	
	public Page<BusPlanDetail> findPage(Page<BusPlanDetail> page, BusPlanDetail busPlanDetail) {
		return super.findPage(page, busPlanDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(BusPlanDetail busPlanDetail) {
		super.save(busPlanDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusPlanDetail busPlanDetail) {
		super.delete(busPlanDetail);
	}

	@Transactional(readOnly = false)
	public void batchInsert(List<BusPlanDetail> busPlanDetailList) {
		this.dao.batchInsert(busPlanDetailList);
	}
	
}