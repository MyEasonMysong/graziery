package com.sxkj.base;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * com.sxkj.base.BaseMapper
 * mapper基类，存储公共方法
 * @author zwd
 * @Description BaseMapper
 * @Date Create in 2018-05-17 0017 11:10
 * @Modified
 */
@Repository
public interface BaseMapper<T extends BaseModel> {
    /** 新增,返回id */
    long insert(T record);

    /** 删除 */
    int deleteByPrimaryKey(Long id);

    /** 修改 */
    int updateByPrimaryKey(T t);

    /** 查找 */
    T selectByPrimaryKey(Long id);

    /** 查找多条记录 */
    List<T> selectAll();
    /** 根据条件查找多条记录 */
    List<T> findList(T t);
}
