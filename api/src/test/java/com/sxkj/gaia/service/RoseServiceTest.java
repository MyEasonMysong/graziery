package com.sxkj.gaia.service;

import com.sxkj.gaia.model.Resource;
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
 * com.sxkj.gaia.service.RoseServiceTest
 *
 * @author zwd
 * @Description RoseServiceTest
 * @Date Create in 2018-07-26 0026 10:24
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoseServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    @Transactional
    public void test() throws Exception{
        Long roleId = 222L;
        List<Long> ids = new ArrayList<>(16);
        for(int i=0;i<5;i++){
            ids.add(Long.parseLong(i+""));
        }
        // bind org
        int r = roleService.bindOrg(roleId, ids);
        Assert.assertTrue("true",r==5);
        // bind user
        r = roleService.bindUsers(roleId, ids);
        Assert.assertTrue("true",r==5);
        // bind resource
        r = roleService.bindResources(roleId, ids);
        Assert.assertTrue("true",r==5);
    }
}
