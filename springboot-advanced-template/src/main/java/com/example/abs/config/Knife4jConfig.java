package com.example.abs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 封装的Swagger文档
 * http://localhost:8080/doc.html
 */
@Configuration
public class Knife4jConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("abcdefg Api文档")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .contact(new Contact("abcdefg", "https://1", "1"))
                        .version("1.0")
                        .termsOfServiceUrl("http://localhost:8080/")
                        .build())
                //分组名称
                .groupName("dev")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.abs.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
