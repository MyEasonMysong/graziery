package com.sxkj.gaia.mapper;

import com.sxkj.gaia.model.RoleOrg;
import com.sxkj.gaia.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.gaia.mapper.RoleOrgMapperTest
 *
 * @author zwd
 * @Description RoleOrgMapperTest
 * @Date Create in 2018-07-26 0026 8:47
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleOrgMapperTest {
    @Autowired
    private RoleOrgMapper roleOrgMapper;

    @Test
    public void test(){
        Long roleId = 123L;
        List<RoleOrg> list = new ArrayList<>(16);
        RoleOrg roleOrg2 = new RoleOrg();
        roleOrg2.insertInit(456L);
        System.out.println(roleOrg2.toString());
        User user = new User();
        user.insertInit(456L);
        System.out.println(user.toString());
        for(int i=0;i<10;i++){
            RoleOrg roleOrg = new RoleOrg();
            roleOrg.setRoleId(roleId);

            roleOrg.setOrgId(Long.parseLong(i+""));
            roleOrg.insertInit(222L);
            //roleOrg.setCreateId(222L);
            //roleOrg.setUpdateId(333L);
            list.add(roleOrg);
        }
        int i = roleOrgMapper.batchInsert(list);
        i = roleOrgMapper.deleteByRoleId(roleId);
    }
}
