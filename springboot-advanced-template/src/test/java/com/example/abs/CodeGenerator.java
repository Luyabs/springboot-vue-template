package com.example.abs;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.example.abs.common.base.BaseService;
import com.example.abs.common.base.BaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * mybatis-plus代码生成器
 */
@SpringBootTest
public class CodeGenerator {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;     // 数据库url

    @Value("${spring.datasource.username}")
    private String jdbcUsername;    // 数据库用户名

    @Value("${spring.datasource.password}")
    private String jdbcPassword;      // 数据库密码

    @Value("abstraction")
    private String authorName;  // 作者名

    @Value("com.example.abs")
    private String packageName;    // 包名

    private final List<String> tableName = List.of("t_order");//"book", "t_user");   // 表名

    private final String tablePrefix = "t_"; //"t_";  // 忽略的表名前缀


    @Test
    public void run() {
        try {
            FastAutoGenerator.create(jdbcUrl, jdbcUsername, jdbcPassword)
                    // 全局配置
                    .globalConfig(builder -> {
                        builder.disableOpenDir() //禁止打开输出目录，默认打开
                                .outputDir("src/main/java") // 指定输出目录
                                .author(authorName) // 设置作者
//                            .enableSwagger()    // 开启swagger模式
                                .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期格式
                        ;
                    })
                    // 包配置
                    .packageConfig(builder -> {
                        builder.parent(packageName); // 设置父包名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mappers")); // 设置mapperXml生成路径
                    })
                    // 策略配置
                    .strategyConfig(builder -> {
                        builder.addInclude(tableName) // 设置需要生成的表名
                                .addTablePrefix(tablePrefix) // 设置过滤表前缀
                                // Entity 策略配置
                                .entityBuilder()
                                .disableSerialVersionUID()  // 禁止序列化生成
                                .enableLombok() //开启 Lombok
                                .enableChainModel() // 链式模型
                                //.enableActiveRecord()   // 开启ActiveRecord模式
                                .idType(IdType.ASSIGN_ID)   // 主键雪花算法
                                .enableFileOverride() // 覆盖已生成文件
                                .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                                .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                                // Mapper 策略配置
                                .mapperBuilder()
                                .enableMapperAnnotation()   // 开启Mapper注解
                                .enableFileOverride() // 覆盖已生成文件
                                // Service 策略配置
                                .serviceBuilder()
                                .superServiceClass(BaseService.class)   // service父接口名
                                .superServiceImplClass(BaseServiceImpl.class)   // service父实现类名
                                .enableFileOverride() // 覆盖已生成文件
                                .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                                .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                                // Controller 策略配置
                                .controllerBuilder()
                                // .superClass(BaseController.class)   // controller父类名
                                .enableRestStyle()  // RestController
                                .enableFileOverride() // 覆盖已生成文件
                        ;
                    })
                    .execute();
        } catch (IllegalStateException e) {
            System.out.println("去pom.xml把swagger相关的配置全部注释掉，此处错误由springfox-boot-starter与mybatis-generator版本冲突引起");
        }
    }
}
