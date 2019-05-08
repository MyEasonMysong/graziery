package com.sxkj.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * com.sxkj.swagger.Swagger2
 *
 * @author zwd
 * @Description Swagger2
 * @Date Create in 2018-06-20 0020 8:40
 * @Modified
 */
@Configuration
@ComponentScan(basePackages = "com.sxkj.gaia")
public class Swagger2 {
    @Value("${swagger.enable}")
    private boolean enable;
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sxkj.gaia"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("吉林省森祥科技有限公司","http://www.jlsenxiang.com","dev@jlsenxiang.com");
        return new ApiInfoBuilder()
                .title("森祥科技基础框架API文档")
                .description("森祥科技基础框架API文档")
                //.termsOfServiceUrl("http://localhost:8080/gaia")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
