/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.service;

import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bus.entity.BusTask;
import com.thinkgem.jeesite.modules.bus.dao.BusTaskDao;

/**
 * 专项任务Service
 * @author zmy
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class BusTaskService extends CrudService<BusTaskDao, BusTask> {

	public BusTask get(String id) {
		return super.get(id);
	}
	
	public List<BusTask> findList(BusTask busTask) {
		return super.findList(busTask);
	}
	
	public Page<BusTask> findPage(Page<BusTask> page, BusTask busTask) {
		return super.findPage(page, busTask);
	}
	
	@Transactional(readOnly = false)
	public void save(BusTask busTask) {
		if(StringUtils.isNotBlank(busTask.getId())){
			BusTask busTaskOld = super.get(busTask.getId());
			if(null==busTaskOld){
				busTask.setIsNewRecord(true);
			}
		}
		super.save(busTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusTask busTask) {
		super.delete(busTask);
	}
	
}