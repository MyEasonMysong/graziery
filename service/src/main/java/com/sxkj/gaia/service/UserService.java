package com.sxkj.gaia.service;

import com.sxkj.base.BaseService;
import com.sxkj.gaia.mapper.UserMapper;
import com.sxkj.gaia.mapper.UserRoleMapper;
import com.sxkj.gaia.model.User;
import com.sxkj.gaia.model.UserRole;
import com.sxkj.util.Constants;
import com.sxkj.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.gaia.service.UserServiceImpl
 *
 * @author zwd
 * @Description UserServiceImpl
 * @Date Create in 2018-07-13 0013 17:12
 * @Modified
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService extends BaseService<User> {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * 根据用户登录名查找用户
     * @param loginName 登录名
     * @return 用户
     * @throws Exception
     */
    public User findByLoginName(String loginName)throws Exception{
        return userMapper.findByLoginName(loginName);
    }

    /**
     * 根据用户id获取信息（用户基本信息、机构、角色、功能权限等）
     * @param id
     * @return
     * @throws Exception
     */
    public User findById(long id)throws Exception{
        return userMapper.findById(id);
    }
    /**
     * 新增用户，方法内处理password加密
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(User user) throws Exception{
        user.setLoginPwd(MD5.EncoderByMd5(user.getLoginName()+user.getLoginPwd()));
        return userMapper.insert(user);
    }

    /**
     * 修改用户登录密码
     * @param loginName 登录名
     * @param loginPwd 登录密码
     * @return
     * @throws Exception
     */
    public int updatePassword(String loginName,String loginPwd) throws Exception{
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPwd(MD5.EncoderByMd5(loginName+loginPwd));
        return userMapper.updatePassword(user);
    }

    /**
     * 用户绑定角色
     * @param userId 带绑定用户id
     * @param roleIds 当绑定角色id List
     * @return 成功绑定记录数
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public int bindRoles(Long userId, List<Long> roleIds) throws Exception{
        // 删除与userId关联的数据
        userRoleMapper.deleteByUserId(userId);
        // 获取操作人id
        Long createId = Constants.getUserId();
        // 批量添加
        UserRole userRole;
        List<UserRole> userRoles = new ArrayList<>(16);
        for(Long roleId : roleIds){
            userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRole.insertInit(createId);
            userRoles.add(userRole);
        }
        return userRoleMapper.batchInsert(userRoles);
    }
}
