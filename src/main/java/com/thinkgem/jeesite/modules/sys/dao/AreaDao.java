/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
    /**
     * 
    * @Title: findAllByParentId
    * lxy
    * 2019年1月23日14:41:14
    * @Description: 通过父id获取集合
    * @param @param area
    * @param @return    参数
    * @return List<Area>    返回类型
    * @throws
     */
    public List<Area> findAllByParentId(Area area);
}
