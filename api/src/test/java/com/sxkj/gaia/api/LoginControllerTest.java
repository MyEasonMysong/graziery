package com.sxkj.gaia.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sxkj.exception.ExceptionHandle;
import com.sxkj.gaia.service.UserService;
import io.github.swagger2markup.*;
import io.github.swagger2markup.markup.builder.LineSeparator;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.github.swagger2markup.model.PathOperation;
import io.swagger.models.parameters.Parameter;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * com.sxkj.gaia.api.LoginControllerTest
 *
 * @author zwd
 * @Description LoginControllerTest
 * @Date Create in 2018-07-24 0024 18:30
 * @Modified
 */
public class LoginControllerTest extends BaseControllerTest{

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private UserService userService;

    /**
     * 测试通过
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        // 设置验证码，保存在session中
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("verifyCode","1234");
        RequestBuilder request = MockMvcRequestBuilders.get("/login/tom/1/1234")
                .session(session);

        MvcResult result = mvc.perform(get("/login/laowang2/2/1234").session(session))
                //.andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertTrue("true",status==200);
//        String content = result.getResponse().getContentAsString();
//        System.out.println(status);
//        System.out.println(content);
    }

    @Test
    public void logout() throws Exception {
        // todo 未测试
    }

    @Test
    public void validate() throws Exception {
        // todo 测试未能如愿进行，主要是测试方法还存在问题
//        RequestBuilder request = MockMvcRequestBuilders.get("/validate")
//                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mvc.perform(get("/validate")).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        System.out.println(status);
        System.out.println(content);
    }

    private HttpSession getVrifyCode() throws Exception{
        MvcResult result = mvc.perform(get("/validate"))
                .andExpect(status().isOk())
                .andReturn();
        return result.getRequest().getSession();
    }


    @Test
    public void generateAsciiDocs() throws Exception{
        Swagger2MarkupConfig config = new Swagger2MarkupConfig() {
            @Override
            public MarkupLanguage getMarkupLanguage() {
                return MarkupLanguage.ASCIIDOC;
            }

            @Override
            public MarkupLanguage getSwaggerMarkupLanguage() {
                return null;
            }

            @Override
            public boolean isGeneratedExamplesEnabled() {
                return false;
            }

            @Override
            public boolean isBasePathPrefixEnabled() {
                return false;
            }

            @Override
            public boolean isSeparatedDefinitionsEnabled() {
                return false;
            }

            @Override
            public boolean isSeparatedOperationsEnabled() {
                return false;
            }

            @Override
            public GroupBy getPathsGroupedBy() {
                return null;
            }

            @Override
            public Language getOutputLanguage() {
                return null;
            }

            @Override
            public boolean isInlineSchemaEnabled() {
                return false;
            }

            @Override
            public OrderBy getTagOrderBy() {
                return null;
            }

            @Override
            public Pattern getHeaderPattern() {
                return null;
            }

            @Override
            public Comparator<String> getTagOrdering() {
                return null;
            }

            @Override
            public OrderBy getOperationOrderBy() {
                return null;
            }

            @Override
            public Comparator<PathOperation> getOperationOrdering() {
                return null;
            }

            @Override
            public OrderBy getDefinitionOrderBy() {
                return null;
            }

            @Override
            public Comparator<String> getDefinitionOrdering() {
                return null;
            }

            @Override
            public OrderBy getParameterOrderBy() {
                return null;
            }

            @Override
            public Comparator<Parameter> getParameterOrdering() {
                return null;
            }

            @Override
            public OrderBy getPropertyOrderBy() {
                return null;
            }

            @Override
            public Comparator<String> getPropertyOrdering() {
                return null;
            }

            @Override
            public OrderBy getResponseOrderBy() {
                return null;
            }

            @Override
            public Comparator<String> getResponseOrdering() {
                return null;
            }

            @Override
            public boolean isInterDocumentCrossReferencesEnabled() {
                return false;
            }

            @Override
            public String getInterDocumentCrossReferencesPrefix() {
                return null;
            }

            @Override
            public boolean isFlatBodyEnabled() {
                return false;
            }

            @Override
            public boolean isPathSecuritySectionEnabled() {
                return false;
            }

            @Override
            public String getAnchorPrefix() {
                return null;
            }

            @Override
            public String getOverviewDocument() {
                return null;
            }

            @Override
            public String getPathsDocument() {
                return null;
            }

            @Override
            public String getDefinitionsDocument() {
                return null;
            }

            @Override
            public String getSecurityDocument() {
                return null;
            }

            @Override
            public String getSeparatedOperationsFolder() {
                return null;
            }

            @Override
            public String getSeparatedDefinitionsFolder() {
                return null;
            }

            @Override
            public LineSeparator getLineSeparator() {
                return null;
            }

            @Override
            public Character getListDelimiter() {
                return null;
            }

            @Override
            public boolean isListDelimiterEnabled() {
                return false;
            }

            @Override
            public Swagger2MarkupProperties getExtensionsProperties() {
                return null;
            }

            @Override
            public List<PageBreakLocations> getPageBreakLocations() {
                return null;
            }
        };
        Swagger2MarkupConverter.from(new URL("http://localhost:8090/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("/src/docs/asciidoc/generated"));
    }
}