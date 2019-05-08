/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bas.entity.Item;
import com.thinkgem.jeesite.modules.bas.dao.ItemDao;

/**
 *
 *
 * @ClassName ItemService
 * @Description 指标项管理service层
 * @author lihongtao
 * @date 2019年1月29日
 *
 */
@Service
@Transactional(readOnly = true)
public class ItemService extends CrudService<ItemDao, Item> {

	/**
	 *
	 *
	 * @Title get
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 根据id查询指标项
	 * @param id
	 * @return Item
	 * @throws
	 */
	public Item get(String id) {
		return super.get(id);
	}

	/**
	 *
	 *
	 * @Title findList
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 查询指标项列表
	 * @param item
	 * @return List<Item>
	 * @throws
	 */
	public List<Item> findList(Item item) {
		return super.findList(item);
	}

	/**
	 *
	 *
	 * @Title findPage
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 查询指标项列表(分页)
	 * @param page ， item
	 * @return Page<Item>
	 * @throws
	 */
	public Page<Item> findPage(Page<Item> page, Item item) {
		return super.findPage(page, item);
	}

	/**
	 *
	 *
	 * @Title save
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 保存指标项
	 * @param item
	 * @return void
	 * @throws
	 */
	@Transactional(readOnly = false)
	public void save(Item item) {
		boolean isNewRecord = item.getIsNewRecord();
		List<Item> list = item.getList();
		if ((null != list && !list.isEmpty())) {
			item.setItemSubAmount(list.size());
		} else {
			item.setItemSubAmount(0);
		}
		/** 保存父级 */
		item.setParent(new Item("0"));
		super.save(item);
		/** 保存子级 */
		if (0 < item.getItemSubAmount()) {
			List<String> exceptList = Lists.newArrayList();
			for (Item child : list) {
				child.setItemTypeCode(item.getItemTypeCode());
				child.setParent(item);
				super.save(child);
				exceptList.add(child.getId());
			}
			if (!isNewRecord) {
				this.dao.deleteByExcept(item.getId(), exceptList);
			}
		}
	}
	
	/**
	 *
	 *
	 * @Title delete
	 * @author lht
	 * @date 2019年1月24日
	 * @Description 删除指标项
	 * @param item
	 * @return void
	 * @throws
	 */
	@Transactional(readOnly = false)
	public void delete(Item item) {
		super.delete(item);
	}

	/**
	 *
		 *
		 * @Title findListByParentId
		 * @author lht
		 * @date 2019年1月24日
		 * @Description 查询指标项子级
		 * @param parentId
		 * @return List<Item>
		 * @throws
	 */
	public List<Item> findListByParentId(String parentId) {
		Item item=new Item();
		item.setParent(new Item(parentId));
		return this.dao.findList(item);
	}

    /**
     *
     * @Title: findAllList
     * @author: Three
     * @date: 2019/1/30 14:19
     * @Description: 查询所有 并将数据进行树形排列
     * @param: [item, checkSystemId]
     * @return: List<Item>
     * @throws: Exception
     */
    public List<Item> findAllList(Item item,String checkSystemId) {
        item.setCheckSystemId(checkSystemId);
        List<Item> itemsList = this.dao.findEditList(item);
        return sortList(itemsList);
    }

	/**
	 *
	 * @Title: findViewList
	 * @author: Three
	 * @date: 2019/1/30 14:20
	 * @Description: 根据id查询关联数据，并将查询数据进行树形排列
	 * @param: [item, checkSystemId]
	 * @return: List<Item>
	 * @throws: Exception
	 */
	public List<Item> findViewList(Item item,String checkSystemId) {
		item.setCheckSystemId(checkSystemId);
		List<Item> itemsList = this.dao.findViewList(item);
		return sortList(itemsList);

	}

	/**
	 *
	 * @Title: sortList
	 * @author: shirui
	 * @date: 2019/1/30 14:25
	 * @Description: 对itemsList进行树形排序
	 * @param: [itemsList]
	 * @return: List<Item>
	 * @throws: Exception
	 */
	public List<Item> sortList(List<Item> itemsList){

		List<Item> itemList =  Lists.newArrayList();
		//获取全部一级数据
		for(int i=0;i<itemsList.size();i++){
			if(itemsList.get(i).getParent().getId().equals("0")){
				itemList.add(itemsList.get(i));
			}
		}
		//遍历itemsList 向itemList中根据parent.Id插入相应二级数据
		for(int i=0;i<itemsList.size();i++){
			if(!itemsList.get(i).getParent().getId().equals("0")){
				for(int j=0;j<itemList.size();j++){
					if(itemsList.get(i).getParent().getId().equals(itemList.get(j).getId())){
						itemList.get(j).getList().add(itemsList.get(i));
					}
				}
			}
		}
		return itemList;
	}
}