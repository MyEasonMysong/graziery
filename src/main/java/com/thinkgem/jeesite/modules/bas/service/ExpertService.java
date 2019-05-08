/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bas.entity.Expert;
import com.thinkgem.jeesite.modules.bas.dao.ExpertDao;

/**
 * 专家库生成Service
 * @author sr
 * @version 2019-01-23
 */
@Service
@Transactional(readOnly = true)
public class ExpertService extends CrudService<ExpertDao, Expert> {

	public Expert get(String id) {
		return super.get(id);
	}
	
	public List<Expert> findList(Expert expert) {
		return super.findList(expert);
	}
	
	public Page<Expert> findPage(Page<Expert> page, Expert expert) {
		return super.findPage(page, expert);
	}
	
	@Transactional(readOnly = false)
	public void save(Expert expert) {
		super.save(expert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expert expert) {
		super.delete(expert);
	}
	
}