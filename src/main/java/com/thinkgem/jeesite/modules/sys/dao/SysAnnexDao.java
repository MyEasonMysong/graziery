/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysAnnex;

/**
 * 附件生成DAO接口
 * @author ljx
 * @version 2018-07-27
 */
@MyBatisDao
public interface SysAnnexDao extends CrudDao<SysAnnex> {
	
	public  void  deleteBytableId(SysAnnex sysAnnex);
	
}