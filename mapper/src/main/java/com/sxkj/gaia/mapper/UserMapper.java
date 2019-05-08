/**
 * UserMapper.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.mapper;

import com.sxkj.base.BaseMapper;
import com.sxkj.gaia.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户登录名获取用户信息
     * @param loginName 登录名
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 根据用户id获取信息（用户基本信息、机构、角色、功能权限等）
     * @param id
     * @return
     */
    User findById(long id);

    /**
     * 修改用户登录密码
     * @param user
     * @return
     */
    int updatePassword(User user);
}