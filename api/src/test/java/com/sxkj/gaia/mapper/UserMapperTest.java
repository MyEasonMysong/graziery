package com.sxkj.gaia.mapper;

import com.sxkj.gaia.model.Org;
import com.sxkj.gaia.model.User;
import com.sxkj.util.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * com.sxkj.gaia.mapper.UserMapperTest
 *
 * @author zwd
 * @Description UserMapperTest
 * @Date Create in 2018-07-13 0013 17:09
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Test
    public void test() throws Exception{
        User user = userMapper.findByLoginName("tom");
        System.out.println(user.toString());
        String pwd = "2";
        User u = new User();
        u.setLoginName("tom");
        u.setLoginPwd("2");
        userMapper.updatePassword(u);
        user = userMapper.selectByPrimaryKey(user.getId());
        System.out.println(user.toString());
    }
}
