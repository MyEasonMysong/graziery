package com.sxkj.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxkj.gaia.model.User;
import com.sxkj.property.DataStatusEnum;
import com.sxkj.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * com.sxkj.base.BaseService
 *
 * @author zwd
 * @Description BaseService
 * @Date Create in 2018-05-21 0021 15:59
 * @Modified
 */
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class BaseService<T extends BaseModel>{
    /** 默认的列表页行数 */
    private static final int DEFAULT_PAGE_SIZE = 20;
    @Autowired
    private BaseMapper<T> baseMapper;

    /**
     * 新增方法
     * @param t pojo对象实例
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public long insert(T t) throws Exception{
        Long createId = (Long) Constants.getRequest().getAttribute(Constants.SESSION_USER_ID);
        t.insertInit(createId);
        return baseMapper.insert(t);
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) throws Exception{
        T t = baseMapper.selectByPrimaryKey(id);
        t.setStatusFlag(DataStatusEnum.DELETE.getIdnex());
        return baseMapper.updateByPrimaryKey(t);
    }

    /**
     * 根据id修改数据
     * @param t
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(T t) throws Exception{
        Long updateId = (Long) Constants.getRequest().getAttribute(Constants.SESSION_USER_ID);

        t.updateInit(updateId);
        return baseMapper.updateByPrimaryKey(t);
    }

    /**
     * 根据id获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public T selectByPrimaryKey(Long id) throws Exception{
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有数据
     * @return
     * @throws Exception
     */
    public List<T> selectAll() throws Exception{
        return baseMapper.selectAll();
    }

    /**
     * 根据条件查询
     * @param t
     * @param pageNumber 当前页号
     * @param pageSize   每页记录数
     * @return
     * @throws Exception
     */
    public PageInfo findList(T t,int pageNumber,int pageSize) throws Exception{
        PageHelper.startPage(pageNumber<=0?1:pageNumber,pageSize<=0?DEFAULT_PAGE_SIZE:pageSize);
        return new PageInfo(baseMapper.findList(t));
    }
}
