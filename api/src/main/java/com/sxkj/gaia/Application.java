package com.sxkj.gaia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * com.sxkj.gaia.Application
 *
 * @author zwd
 * @Description Application
 * @Date Create in 2018-06-11 0011 16:21
 * @Modified
 */
//@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
@ImportResource(locations = {"classpath:kaptcha.xml"})
@EnableSwagger2
@ComponentScan(basePackages = "com.sxkj")
@MapperScan(basePackages = "com.sxkj.gaia")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
