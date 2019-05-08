package com.sxkj.gaia.service;

import com.sxkj.gaia.model.Org;
import com.sxkj.gaia.model.User;
import com.sxkj.util.MD5;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * com.sxkj.gaia.service.OrgServiceTest
 *
 * @author zwd
 * @Description OrgServiceTest
 * @Date Create in 2018-07-13 0013 16:57
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgServiceTest {
    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception{
        Org org = orgService.findById(1531357307150L);
        Assert.assertTrue("true",org!=null);
    }
}
