# 基础框架
    基础框架由前端项目和服务端项目组成，本项目为服务端项目。
    主要实现与业务无关的核心功能和常用功能。
    项目使用maven完成multi module构建，最大程度的实现代码复用。
    涉及到的相关技术包括（但不限于）spring boot、mybatis、druid、jdk1.8、tomcat8、mysql5.7、jwt、swagger、redis等。
### 模块、功能
    1. 服务端
    用户管理、机构管理、应用管理、角色管理、资源管理、字典项管理、行政区划管理、日志管理
    用户认证、鉴权，文件上传、下载，多数据源切换
    2. 前端
    动态导航、组件化（tree、table。。。）、权限控制
    
   
### 其他：
   1. 使用禅道作为项目文档管理工具
   
   2. 使用git作为代码管理工具
   
   3. 使用EZDML（PowerDesigner）作为数据库设计工具
   
   4. 使用idea作为后端开发工具
   
   5. 使用vs code作为前端开发工具             

### module创建方法
1. 鼠标在项目名称上右键，new-module
2. 新窗口右侧选择maven，左下角next，新窗口填写artifactId，然后next，finish
### 说明
1. 原则上公用的依赖和配置写在parent项目的pom.xml中
2. 特殊的特殊的依赖、配置写在module的pom.xml中
3. 子模块说明
> core：基类、工具类、日志配置、数据库配置、代码生成  
> model：POJO对象   
> mapper：dao interface   
> service：service class  
> api：RESTful api  

### 代码生成
1. 依赖mysql数据库驱动jar文件
2. 修改generatorConfig.xml文件
> 修改table生成指定数据表的model和mapper

3. 执行MapperPlugin.java的main方法


### 要求
1. 接口列表 swagger
2. 安全认证 jwt、oAuth
3. 单元测试，覆盖dao、service、Controller

### swagger 接口列表
    http://localhost:8080/swagger-ui.html
    
### 启动
    开发环境下run Application.java    
    
### 开发环境、工具
    JDK1.8
    tomcat8及以上
    MySQL5.7
    IntelliJ IDEA
    PowerDesigner
    EZDML
    
    node8.11.x
    vue2.x
    vue-router
    axios
    vuex
    element-ui
    vs code

### 数据库系统字段
    
        CREATE TABLE  `sys_field`
        (
          `id`              BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
          `create_id`       BIGINT COMMENT '创建人id',
          `update_id`       BIGINT COMMENT '修改人id',
          `create_time`     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
          `update_time`     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
          `status`          TINYINT COMMENT '数据状态',
          PRIMARY KEY (id)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='系统字段';
        
### postman 设置环境变量
1. 添加环境变量

    manage environments、add
    
    设置key、value
2. Authorization

    type：Bearer Token
    
        pm.test("State code is 200",function(){
           pm.response.to.have.status(200) ;
        });
        var data = JSON.parse(responseBody);
        pm.environment.set("token",data.msg);
       

### shiro自定义注解
1.  创建自定义的注解 Permissions    1
2.  资源管理器，继承AuthorizationAttributeSourceAdvisor，添加新注解支持     ShiroAdvisor    5
3.  AOP拦截器，继承AopAllianceAnnotationsAuthorizingMethodInterceptor     PermissionAopInterceptor    4
4.  方法拦截器，继承AuthorizingAnnotationMethodInterceptor      PermissionMethodInterceptor     3
5.  权限处理器，继承AuthorizingAnnotationHandler，校验权限   PermissionHandler   2      
6.  配置shiro，替换shiro配置中的AuthorizationAttributeSourceAdvisor 为 ShiroAdvisor

### mybatis 自增主键插入时返回id配置
    keyProperty="id" useGeneratedKeys="true"  