/**
 * UserRoleMapper.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.mapper;

import com.sxkj.base.BaseMapper;
import com.sxkj.gaia.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 批量添加用户和角色的关系
     * @param userRoles
     * @return
     */
    int batchInsert(List<UserRole> userRoles);

    /**
     * 根据用户id删除用户和角色的关系数据
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 根据角色id删除用户和角色的关系数据
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
}