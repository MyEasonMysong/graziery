package com.sxkj.gaia.api;

import com.google.gson.Gson;
import com.sxkj.gaia.service.UserService;
import com.sxkj.util.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * com.sxkj.gaia.api.UserControllerTest
 *
 * @author zwd
 * @Description UserControllerTest
 * @Date Create in 2018-07-26 0026 15:07
 * @Modified
 */
public class UserControllerTest extends BaseControllerTest {
    @Autowired
    UserService userService;

    @Test
    public void updatePassword() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/user/editPwd")
                .param("loginName","tom")
                .param("loginPwd","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
        String content = result.getResponse().getContentAsString();
        Result re = new Gson().fromJson(content, Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

    @Test
    public void bindRoles() throws Exception {
        Map<String,Object> map = new HashMap<>(16);
        List<Long> list = new ArrayList<Long>(16){{
            add(1L);
            add(11L);
            add(111L);
        }};

        map.put("userId",1234L);
        map.put("roleIds",list);

        RequestBuilder builder = MockMvcRequestBuilders.post("/api/user/bindRole")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
               // .content(new Gson().toJson(map))
                .param("userId","123456")
                .param("roleIds","2,3,4")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(builder).andReturn();

        int status = result.getResponse().getStatus();
        Assert.assertTrue("true", 200 == status);
        String content = result.getResponse().getContentAsString();
        Result r = new Gson().fromJson(content, Result.class);
        Assert.assertTrue("true",1==r.getStatus());
    }

}