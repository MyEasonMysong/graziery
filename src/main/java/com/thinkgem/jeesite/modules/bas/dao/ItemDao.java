/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bas.entity.Item;

/**
 * 
 * 
 * @ClassName Item
 * @Description item数据操作层
 * @author lihongtao
 * @date 2019年1月29日
 *
 */
@MyBatisDao
public interface ItemDao extends CrudDao<Item> {

	/**
	 *
	 *
	 * @Title deleteByExcept
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 编辑时删除页面删掉的检查项
	 * @param parentId , exceptList
	 * @return void
	 * @throws
	 */
	public void deleteByExcept(@Param("parentId") String parentId, @Param("list") List<String> exceptList);


    public List<Item> findEditList(Item item);

    public List<Item> findViewList(Item item);

}