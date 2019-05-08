package com.sxkj.gaia.mapper;

import com.sxkj.gaia.model.RoleResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * com.sxkj.gaia.mapper.RoleResourceMapperTest
 *
 * @author zwd
 * @Description RoleResourceMapperTest
 * @Date Create in 2018-07-18 0018 11:41
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleResourceMapperTest {
    @Autowired
    RoleResourceMapper roleResourceMapper;
    @Test
    //@Transactional
    public void test(){
        long roleId = 123L;
        long createId = 321L;
        roleResourceMapper.deleteByRoleId(roleId);
        List<RoleResource> roleResources = new ArrayList<>(16);
        for(int i=0;i<20;i++){
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Long.parseLong(i+""));
            roleResource.insertInit(createId);
            roleResources.add(roleResource);
        }
        System.out.println(roleResourceMapper.batchInsert(roleResources));
    }
}
