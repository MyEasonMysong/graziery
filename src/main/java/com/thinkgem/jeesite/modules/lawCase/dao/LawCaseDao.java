/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lawCase.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lawCase.entity.LawCase;

/**
 * 检查计划DAO接口
 * @author sr
 * @version 2019-03-27
 */
@MyBatisDao
public interface LawCaseDao extends CrudDao<LawCase> {
	
}