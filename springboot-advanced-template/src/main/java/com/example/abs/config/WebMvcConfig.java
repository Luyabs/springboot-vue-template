package com.example.abs.config;

import com.example.abs.common.interceptor.JwtInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${spring.profiles.active}")
    private String environment;  // 仅开发环境下关闭JWT拦截器

    /**
     * 设置静态资源映射
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("backend/**").addResourceLocations("classpath:/static/backend/");
        registry.addResourceHandler("front/**").addResourceLocations("classpath:/static/front/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        // knife4j
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 跨域
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "POST", "DELETE", "PUT")
                .allowCredentials(false)
                .maxAge(3600);
    }

    /**
     * 拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        if (!environment.equals("dev")) {   // 非开发环境下禁止访问 knife4j 或 在未登录的情况下使用其他api
            registry.addInterceptor(new JwtInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/user/login", "/user/info", "/user/logout");
        }
    }

    /**
     * 过长的数据类型在JSON中转换成String
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String DEFAULT_TIME_FORMAT = "HH:mm:ss";

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(
                new ObjectMapper().registerModule(
                        new SimpleModule()
                                // 数据 -> String
                                .addSerializer(Long.class, ToStringSerializer.instance)
                                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                                .addSerializer(BigDecimal.class, ToStringSerializer.instance)
                                // 时间 序列化与反序列化
                                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                ));
        converters.add(0, jackson2HttpMessageConverter);
    }

}
