package com.sxkj.gaia.mapper;

import com.sxkj.gaia.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.gaia.mapper.UserRoleMapperTest
 *
 * @author zwd
 * @Description UserRoleMapperTest
 * @Date Create in 2018-07-18 0018 13:10
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleMapperTest {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Test
    public void test(){
        List<UserRole> userRoles = new ArrayList<>(16);
        UserRole userRole;
        Long userId = 123L;
        userRoleMapper.deleteByUserId(userId);
        for(int i=0;i<10;i++){
            userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.insertInit(123L);
            userRole.setRoleId(Long.parseLong(i+""));

            userRoles.add(userRole);
        }
        Long roleId = 123L;
        userRoleMapper.deleteByRoleId(roleId);
        for(int i=0;i<10;i++){
            userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.insertInit(123L);
            userRole.setUserId(Long.parseLong(i+""));

            userRoles.add(userRole);
        }
        userRoleMapper.batchInsert(userRoles);
    }
}
