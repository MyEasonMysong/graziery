/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bus.entity.BusProgramme;
import com.thinkgem.jeesite.modules.bus.dao.BusProgrammeDao;

/**
 * 现场检查方案Service
 * @author wl
 * @version 2019-03-28
 */
@Service
@Transactional(readOnly = true)
public class BusProgrammeService extends CrudService<BusProgrammeDao, BusProgramme> {

	public BusProgramme get(String id) {
		return super.get(id);
	}
	
	public List<BusProgramme> findList(BusProgramme busProgramme) {
		return super.findList(busProgramme);
	}

	@Override
	public Page<BusProgramme> findPage(Page<BusProgramme> page, BusProgramme busProgramme) {
		return super.findPage(page, busProgramme);
	}
	
	@Transactional(readOnly = false)
	public void save(BusProgramme busProgramme) {
		super.save(busProgramme);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusProgramme busProgramme) {
		super.delete(busProgramme);
	}
	
}