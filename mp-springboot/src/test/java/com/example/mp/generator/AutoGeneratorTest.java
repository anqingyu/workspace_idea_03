package com.example.mp.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 代码生成器使用示例
 */
public class AutoGeneratorTest {

    // 数据库地址
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mp?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    // 数据库用户名
    private static final String username = "root";
    // 数据库密码
    private static final String password = "123456";

    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
        tables.add("tb_user");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("xf")                // 作者
                            .outputDir(System.getProperty("user.dir")+"/mp-springboot/src/main/java/")    //输出路径(写到java目录)
                            .enableSwagger()            // 开启swagger
                            .commentDate("yyyy-MM-dd")  // 注释日期	默认值: yyyy-MM-dd
                            .fileOverride();            // 开启覆盖之前生成的文件
                })
                .packageConfig(builder -> {
                    builder.parent("com.example")           // 父包名
                            .moduleName("practice")         // 父包模块名 默认值:无
                            .entity("entity")               // Entity包名 默认值:entity
                            .service("service")             // Service包名	默认值:service
                            .serviceImpl("service.impl")    // ServiceImpl包名	默认值:service.impl
                            .controller("controller")       // Controller包名	默认值:controller
                            .mapper("mapper")               // Mapper包名	    默认值:mapper
                            .xml("mapper")                  // MapperXML包名  	默认值:mapper.xml
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/mp-springboot/src/main/resources/mapper")); // 路径配置信息
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)                          // 增加表匹配(内存过滤)	include 与 exclude 只能配置一项
                            .addTablePrefix("tb_")                      // 增加过滤表前缀
                            .serviceBuilder()                           // service 策略配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()                            // 实体策略配置
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()                        // controller 策略配置
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()                            // mapper 策略配置
                            //生成通用的resultMap
                            .enableBaseResultMap()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateConfig(new Consumer<TemplateConfig.Builder>() {
                    @Override
                    public void accept(TemplateConfig.Builder builder) {
                        // 实体类使用我们自定义模板
                        builder.entity("templates/myentity.java");
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
