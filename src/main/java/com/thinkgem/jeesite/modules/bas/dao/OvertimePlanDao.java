/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bas.entity.OvertimePlan;

/**
 * 加班申请DAO接口
 * @author lichao
 * @version 2019-05-15
 */
@MyBatisDao
public interface OvertimePlanDao extends CrudDao<OvertimePlan> {
	
}