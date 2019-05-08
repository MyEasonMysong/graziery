package com.sxkj.gaia.service;

import com.sxkj.gaia.model.User;
import com.sxkj.gaia.model.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.gaia.service.UserServiceTest
 *
 * @author zwd
 * @Description UserServiceTest
 * @Date Create in 2018-07-16 0016 11:23
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void test() throws Exception{
        // insert
        User user = new User();
        user.insertInit(222L);
        user.setLoginName("laowang3");
        user.setLoginPwd("1");
        user.setName("老王");
        user.setOrgId(1531357307150L);
        Long userId = userService.insert(user);
        // findByLoginName
        user = userService.findByLoginName(user.getLoginName());
        //Assert.assertTrue("true","laowang".equals(user.getLoginName()));
        // update password
        int r = userService.updatePassword(user.getLoginName(),"2");
        //Assert.assertEquals("true",r==1L);
        // bind roles
        List<Long> userRoles = new ArrayList<>(16);
        for (int i=0;i<5;i++) {
            userRoles.add(Long.parseLong(i+""));
        }
        r = userService.bindRoles(userId,userRoles);
        //Assert.assertEquals("true",r==5);
    }
}
