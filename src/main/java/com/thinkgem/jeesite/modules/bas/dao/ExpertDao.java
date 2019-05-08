/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bas.entity.Expert;

/**
 * 专家库生成DAO接口
 * @author sr
 * @version 2019-01-23
 */
@MyBatisDao
public interface ExpertDao extends CrudDao<Expert> {
	
}