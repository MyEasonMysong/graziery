/**
 * RoleResourceMapper.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.mapper;

import com.sxkj.base.BaseMapper;
import com.sxkj.gaia.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    /**
     * 批量绑定角色和资源
     * @param roleResources
     * @return
     */
    int batchInsert(List<RoleResource> roleResources);

    /**
     * 批量删除与roleId关联的数据
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);

}