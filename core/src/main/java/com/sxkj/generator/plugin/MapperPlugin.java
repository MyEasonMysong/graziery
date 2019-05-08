package com.sxkj.generator.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * com.sxkj.mybatis.generator.plugin.MapperPlugin
 *
 * @author zwd
 * @Description MapperPlugin
 * @Date Create in 2018-05-17 0017 10:45
 * @Modified
 */
public class MapperPlugin extends PluginAdapter {
    /** BaseMapper */
    private static final String BASE_MAPPER = "com.sxkj.base.BaseMapper";
    private String daoTargetDir;
    private String daoTargetPackage;
    private String daoRootInterface;

    private ShellCallback shellCallback = null;

    public MapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }
    @Override
    public boolean validate(List<String> list) {
        daoTargetDir = properties.getProperty("targetProject");
        boolean valid = stringHasValue(daoTargetDir);

        daoTargetPackage = properties.getProperty("targetPackage");
        boolean valid2 = stringHasValue(daoTargetPackage);
        JavaClientGeneratorConfiguration configuration = context.getJavaClientGeneratorConfiguration();
        daoRootInterface = configuration.getProperty("rootInterface");

        return valid && valid2;
    }


    //@Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles2(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
            CompilationUnit unit = javaFile.getCompilationUnit();
            FullyQualifiedJavaType baseModelJavaType = unit.getType();

            String shortName = baseModelJavaType.getShortName();

            GeneratedJavaFile mapperJavafile = null;

            if (!shortName.endsWith("Mapper")&&!shortName.endsWith("Example")) {
                Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");

                // 添加注释
                mapperInterface.addJavaDocLine("/**");
                mapperInterface.addJavaDocLine(" * 个性化的业务方法请自行手动添加，并与"+shortName+"Mapper.xml相对应！！！");
                mapperInterface.addJavaDocLine(" */");
                // 设置可见性
                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
                // 获取父级接口
                FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoRootInterface);
                // 添加泛型支持
                daoSuperType.addTypeArgument(baseModelJavaType);
                // 添加import
                mapperInterface.addImportedType(baseModelJavaType);
                //mapperInterface.addImportedType(daoSuperType);
                // 设置 root Interface
                mapperInterface.addSuperInterface(daoSuperType);
                mapperInterface.getMethods().clear();
                mapperInterface.getAnnotations().clear();
                mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
                mapperJavaFiles.add(mapperJavafile);

            }
        }
        return mapperJavaFiles;
    }
    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze,TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<" + introspectedTable.getBaseRecordType() + ">");
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType(BASE_MAPPER);

        interfaze.addSuperInterface(fqjt);
        interfaze.addImportedType(imp);

        /**
         * 方法不需要
         */
        interfaze.getMethods().clear();
        interfaze.getAnnotations().clear();
        return true;
    }


    /**
     * 自定义方法入口
     * @param args
     */
    /*public static void main(String[] args){
        try {
            File configFile = new File(MapperPlugin.class.getClassLoader().getResource("").getPath()+"generatorConfig.xml");
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
