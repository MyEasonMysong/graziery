/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.service;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bas.entity.CheckSystemSub;
import com.thinkgem.jeesite.modules.bas.dao.CheckSystemSubDao;

/**
 * 检查体系-子表生成Service
 * @author sr
 * @version 2019-01-23
 */
@Service
@Transactional(readOnly = true)
public class CheckSystemSubService extends CrudService<CheckSystemSubDao, CheckSystemSub> {

	public CheckSystemSub get(String id) {
		return super.get(id);
	}
	
	public List<CheckSystemSub> findList(CheckSystemSub checkSystemSub) {
		return super.findList(checkSystemSub);
	}
	
	public Page<CheckSystemSub> findPage(Page<CheckSystemSub> page, CheckSystemSub checkSystemSub) {
		return super.findPage(page, checkSystemSub);
	}
	
	@Transactional(readOnly = false)
	public void save(CheckSystemSub checkSystemSub) {
		super.save(checkSystemSub);
	}
	
	@Transactional(readOnly = false)
	public void delete(CheckSystemSub checkSystemSub) {
		super.delete(checkSystemSub);
	}

	/**
	 *
	 * @Title: saveList
	 * @author: shirui
	 * @date: 2019/1/30 14:17
	 * @Description: 存入被选中的数据
	 * @param: [itemsList, checkSystemId]
	 * @return: void
	 * @throws: Exception
	 */
	@Transactional(readOnly = false)
	public void saveList(List itemsList,String checkSystemId) {
		List<CheckSystemSub> checkSystemSubList = Lists.newArrayList();
		if(itemsList.size()>0){
			for (int i=0;i< itemsList.size();i=i+3){
				CheckSystemSub checkSystemSub = new CheckSystemSub();
				//变量初始化
				checkSystemSub.setId(IdGen.uuid());
				User user = UserUtils.getUser();
				checkSystemSub.setCreateBy(user);
				checkSystemSub.setUpdateBy(user);
				checkSystemSub.setCreateDate(new Date());
				checkSystemSub.setUpdateDate(new Date());
				//变脸赋值
				checkSystemSub.setCheckSystemId(checkSystemId);
				checkSystemSub.setItemId(itemsList.get(i).toString());
				checkSystemSub.setItemParentId(itemsList.get(i+1).toString());
				checkSystemSub.setCheckMethod(itemsList.get(i+2).toString());
				//插入List
				checkSystemSubList.add(checkSystemSub);
			}
		}
		this.dao.saveList(checkSystemSubList);
	}

}