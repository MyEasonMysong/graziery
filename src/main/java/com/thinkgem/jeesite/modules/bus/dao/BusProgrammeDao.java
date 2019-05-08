/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bus.entity.BusProgramme;

/**
 * 现场检查方案DAO接口
 * @author wl
 * @version 2019-03-28
 */
@MyBatisDao
public interface BusProgrammeDao extends CrudDao<BusProgramme> {
	
}