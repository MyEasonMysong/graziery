/**
 * RoleOrgMapper.java
 * @author sxkj
 * 2018-07-23 14:37:09
 */
package com.sxkj.gaia.mapper;

import com.sxkj.base.BaseMapper;
import com.sxkj.gaia.model.RoleOrg;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleOrgMapper extends BaseMapper<RoleOrg> {
    /**
     * 批量添加
     * @param roleOrgs
     * @return
     */
    int batchInsert(List<RoleOrg> roleOrgs);

    /**
     * 删除roleId关联的机构数据
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);
}