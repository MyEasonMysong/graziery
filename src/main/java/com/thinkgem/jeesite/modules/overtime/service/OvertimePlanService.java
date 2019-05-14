/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.overtime.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.overtime.entity.OvertimePlan;
import com.thinkgem.jeesite.modules.overtime.dao.OvertimePlanDao;

/**
 * 加班计划申请Service
 * @author Xuyanyan
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly = true)
public class OvertimePlanService extends CrudService<OvertimePlanDao, OvertimePlan> {

	public OvertimePlan get(String id) {
		return super.get(id);
	}
	
	public List<OvertimePlan> findList(OvertimePlan overtimePlan) {
		return super.findList(overtimePlan);
	}
	
	public Page<OvertimePlan> findPage(Page<OvertimePlan> page, OvertimePlan overtimePlan) {
		return super.findPage(page, overtimePlan);
	}
	
	@Transactional(readOnly = false)
	public void save(OvertimePlan overtimePlan) {
		super.save(overtimePlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(OvertimePlan overtimePlan) {
		super.delete(overtimePlan);
	}
	
}