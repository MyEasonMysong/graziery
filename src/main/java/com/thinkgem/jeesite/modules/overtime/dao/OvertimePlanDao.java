/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.overtime.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.overtime.entity.OvertimePlan;

/**
 * 加班计划申请DAO接口
 * @author Xuyanyan
 * @version 2019-05-14
 */
@MyBatisDao
public interface OvertimePlanDao extends CrudDao<OvertimePlan> {
	
}