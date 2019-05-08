/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.service;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thinkgem.jeesite.modules.bus.entity.BusPlanDetail;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bus.entity.BusPlan;
import com.thinkgem.jeesite.modules.bus.dao.BusPlanDao;

/**
 * 检查计划Service
 * @author sr
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class BusPlanService extends CrudService<BusPlanDao, BusPlan> {

	@Autowired
	private BusPlanDetailService busPlanDetailService;
	public BusPlan get(String id) {
		return super.get(id);
	}
	
	public List<BusPlan> findList(BusPlan busPlan) {
		return super.findList(busPlan);
	}
	
	public Page<BusPlan> findPage(Page<BusPlan> page, BusPlan busPlan) {
		return super.findPage(page, busPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(BusPlan busPlan) {
		busPlan.setPlanStatus(1);
		User user = UserUtils.getUser();
		if(null!=user){
			busPlan.setOffice(user.getOffice());
		}
		super.save(busPlan);
		List<BusPlanDetail> busPlanDetailList = busPlan.getList();
		if(null!=busPlanDetailList && busPlanDetailList.size()>0){
			for (BusPlanDetail busPlanDetail : busPlanDetailList){
				busPlanDetail.setId(UUID.randomUUID().toString());
				busPlanDetail.setPlanId(busPlan.getId());
				busPlanDetail.setPlanYear(busPlan.getPlanYear());
				busPlanDetail.preInsert();
			}
			busPlanDetailService.batchInsert(busPlanDetailList);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusPlan busPlan) {
		super.delete(busPlan);
	}
	
}