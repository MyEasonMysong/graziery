package com.sxkj.gaia.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sxkj.gaia.model.Org;
import com.sxkj.gaia.service.OrgService;
import com.sxkj.util.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * com.sxkj.gaia.api.OrgControllerTest
 *
 * @author zwd
 * @Description OrgControllerTest
 * @Date Create in 2018-07-26 0026 10:44
 * @Modified
 */
public class OrgControllerTest extends BaseControllerTest{
    @Autowired
    OrgService orgService;

    @Test
    @Transactional
    public void insert() throws Exception {
        // 1、参数准备
        Map<String,Object> param = new HashMap<>(16);
        param.put("name","MockMvc test");
        param.put("shortName","mock");
        param.put("code","mockMvc");
        param.put("parentId",0L);
        param.put("parentsId","0");
        // 2、执行请求
        RequestBuilder request = MockMvcRequestBuilders.post("/api/org")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new Gson().toJson(param))
                .accept(MediaType.APPLICATION_JSON);
        // 3、获取请求结果
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andReturn();
        // 4、获取请求响应编码，进行判断
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
        // 5、获取请求响应结果，进一步判断
        String content = result.getResponse().getContentAsString();
        Result re = new Gson().fromJson(content,Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

    @Test
    public void delete() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/org/1531357307149");
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true", status == 200);
        String content = result.getResponse().getContentAsString();
        Result re = new Gson().fromJson(content, Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

    @Test
    public void update() throws Exception {
        // 1、参数准备
        Map<String,Object> param = new HashMap<>(16);
        param.put("name","MockMvc test");
        param.put("shortName","mock");
        param.put("code","mockMvc");
        param.put("parentId",0L);
        param.put("parentsId","0");
        // 2、执行请求
        RequestBuilder request = MockMvcRequestBuilders.put("/api/org")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new Gson().toJson(param))
                .accept(MediaType.APPLICATION_JSON);
        // 3、获取请求结果
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andReturn();
        // 4、获取请求响应编码，进行判断
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
        // 5、获取请求响应结果，进一步判断
        String content = result.getResponse().getContentAsString();
        Result re = new Gson().fromJson(content,Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

    @Test
    public void select() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/org/1531357307150")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        Result re = new Gson().fromJson(content, Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

    @Test
    public void selectAll() throws Exception {
    }

    @Test
    public void findList() throws Exception {
        // 1、参数准备
        Map<String,Object> param = new HashMap<>(16);
        param.put("name","MockMvc test");
        param.put("shortName","mock");
        //param.put("code","mockMvc");
        //param.put("parentId",0L);
        //param.put("parentsId","0");
        // 2、执行请求
        RequestBuilder request = MockMvcRequestBuilders.post("/api/org/1/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new Gson().toJson(param))
                .accept(MediaType.APPLICATION_JSON);
        // 3、获取请求结果
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andReturn();
        // 4、获取请求响应编码，进行判断
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
        // 5、获取请求响应结果，进一步判断
        String content = result.getResponse().getContentAsString();
        Result re = new Gson().fromJson(content,Result.class);
        Assert.assertTrue("true",1==re.getStatus());
    }

}