package com.sxkj.gaia.service;

import com.sxkj.base.BaseService;
import com.sxkj.gaia.mapper.RoleOrgMapper;
import com.sxkj.gaia.mapper.RoleResourceMapper;
import com.sxkj.gaia.mapper.UserRoleMapper;
import com.sxkj.gaia.model.Role;
import com.sxkj.gaia.model.RoleOrg;
import com.sxkj.gaia.model.RoleResource;
import com.sxkj.gaia.model.UserRole;
import com.sxkj.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.gaia.service.RoleService
 *
 * @author zwd
 * @Description RoleService
 * @Date Create in 2018-07-18 0018 10:05
 * @Modified
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private RoleOrgMapper roleOrgMapper;

    /**
     * 角色绑定用户
     * @param roleId
     * @param userIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int bindUsers(Long roleId, List<Long> userIds) throws Exception{
        // 删除与roleId关联的数据
        userRoleMapper.deleteByRoleId(roleId);
        // 操作人id
        Long createId = Constants.getUserId();
        // 批量添加
        UserRole userRole;
        List<UserRole> userRoles = new ArrayList<>(16);
        for(Long userId : userIds){
            userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRole.insertInit(createId);
            userRoles.add(userRole);
        }
        return userRoleMapper.batchInsert(userRoles);
    }

    /**
     * 角色绑定资源
     * @param roleId 角色id
     * @param resourceIds 资源id
     * @return
     */
    public int bindResources( Long roleId,  List<Long> resourceIds) throws Exception{
        roleResourceMapper.deleteByRoleId(roleId);
        Long createId = Constants.getUserId();
        RoleResource roleResource ;
        List<RoleResource> roleResources = new ArrayList<>(16);
        for (Long resourceId : resourceIds) {
            roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceId);
            roleResource.insertInit(createId);
            roleResources.add(roleResource);
        }
        return roleResourceMapper.batchInsert(roleResources);
    }

    /**
     * 绑定角色和机构，为角色的明细数据权限分配机构
     * @param roleId 角色id
     * @param orgIds 机构id
     * @return
     * @throws Exception
     */
    public int bindOrg(Long roleId,List<Long> orgIds) throws Exception {
        roleOrgMapper.deleteByRoleId(roleId);
        Long createId = Constants.getUserId();
        List<RoleOrg> roleOrgs = new ArrayList<>(16);
        RoleOrg roleOrg ;
        for (Long orgId : orgIds) {
            roleOrg = new RoleOrg();
            roleOrg.insertInit(createId);
            roleOrg.setRoleId(roleId);
            roleOrg.setOrgId(orgId);
            roleOrgs.add(roleOrg);
        }
        return roleOrgMapper.batchInsert(roleOrgs);
    }
}
