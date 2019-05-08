package com.sxkj.gaia.mapper;

import com.sxkj.gaia.model.Org;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * com.sxkj.gaia.mapper.OrgMapperTest
 *
 * @author zwd
 * @Description OrgMapperTest
 * @Date Create in 2018-07-13 0013 16:48
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgMapperTest {
    @Autowired
    private OrgMapper orgMapper;

    @Test
    public void test() throws Throwable{
        Org org = orgMapper.selectByPrimaryKey(1531357277662L);
        System.out.println(org.getName());
        org = orgMapper.findById(1531357277662L);
        System.out.println(org.getUsers().size());
    }
}
