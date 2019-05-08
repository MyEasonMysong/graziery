/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.service;

import java.util.Arrays;
import java.util.List;

import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bas.entity.CheckSystem;
import com.thinkgem.jeesite.modules.bas.dao.CheckSystemDao;

/**
 * 检查体系表生成Service
 * @author sr
 * @version 2019-01-23
 */
@Service
@Transactional(readOnly = true)
public class CheckSystemService extends CrudService<CheckSystemDao, CheckSystem> {

	@Autowired
	private CheckSystemSubService checkSystemSubService;

	@Autowired
	private UserDao userDao;

	public CheckSystem get(String id) {
		return super.get(id);
	}
	
	public List<CheckSystem> findList(CheckSystem checkSystem) {
		return super.findList(checkSystem);
	}
	
	public Page<CheckSystem> findPage(Page<CheckSystem> page, CheckSystem checkSystem) {
		Page<CheckSystem> pageList = super.findPage(page, checkSystem);
		//根据Id查询编辑人名字
		for(int i=0;i<pageList.getList().size();i++){
			pageList.getList().get(i).getUpdateBy().setName(userDao.get(pageList.getList().get(i).getUpdateBy().getId()).getName());
		}
		return pageList;
	}

	/**
	 *
	 * @Title: save
	 * @author: shirui
	 * @date: 2019/1/30 14:15
	 * @Description: 保存数据以及计算相应字段
	 * @param: [checkSystem]
	 * @return: void
	 * @throws: Exception
	 */
	@Transactional(readOnly = false)
	public void save(CheckSystem checkSystem) {
		String str = checkSystem.getItemBody()+","+checkSystem.getItemHead()+",";
		List itemHeadList = Arrays.asList(checkSystem.getItemHead().split(","));
		Integer amount = itemHeadList.size()/3;
		List itemsList = Arrays.asList((str).split(","));
		Integer subAmount = itemsList.size()/3 - amount;
		checkSystem.setItemAmount(amount.toString());
		checkSystem.setItemSubAmount(subAmount.toString());
		if(checkSystem.getId() != null && !checkSystem.getId().equals("")){
			delete(checkSystem);
			checkSystem.setId(null);
		}
		checkSystem.setUseFlag("0");
		super.save(checkSystem);
		//保存被选中的数据
		checkSystemSubService.saveList(itemsList,checkSystem.getId());
	}
	
	@Transactional(readOnly = false)
	public void delete(CheckSystem checkSystem) {
		super.delete(checkSystem);
	}
	
}