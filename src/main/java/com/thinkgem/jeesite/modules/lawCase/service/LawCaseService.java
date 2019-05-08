/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lawCase.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lawCase.dao.LawCaseDao;
import com.thinkgem.jeesite.modules.lawCase.entity.LawCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 案件Service
 * @author WangLe
 * @version 2019-03-27
 */
@Service
@Transactional(readOnly = true)
public class LawCaseService extends CrudService<LawCaseDao, LawCase> {

	public LawCase get(String id) {
		return super.get(id);
	}
	
	public List<LawCase> findList(LawCase lawCase) {
		return super.findList(lawCase);
	}
	
	public Page<LawCase> findPage(Page<LawCase> page, LawCase lawCase) {
		return super.findPage(page, lawCase);
	}
	
	@Transactional(readOnly = false)
	public void save(LawCase lawCase) {
		lawCase.setPlanStatus(1);
		super.save(lawCase);
	}
	
	@Transactional(readOnly = false)
	public void delete(LawCase lawCase) {
		super.delete(lawCase);
	}
	
}