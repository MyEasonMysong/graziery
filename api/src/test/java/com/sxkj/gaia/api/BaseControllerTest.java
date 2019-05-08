package com.sxkj.gaia.api;

import com.sxkj.exception.ExceptionHandle;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * com.sxkj.gaia.api.BaseControllerTest
 *
 * @author zwd
 * @Description BaseControllerTest
 * @Date Create in 2018-07-24 0024 19:36
 * @Modified
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseControllerTest {
    @Autowired
    private ExceptionHandle exceptionHandle;

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
}
