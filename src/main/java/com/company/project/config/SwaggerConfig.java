package com.company.project.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.company.project"))
                .apis(Predicates.and(
                        RequestHandlerSelectors.basePackage("com.company.project"),
                        RequestHandlerSelectors.withClassAnnotation(Api.class))
                )
                .build()
                .useDefaultResponseMessages(false)
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("application/json")));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题：SpringBoot-Swagger学习")
                .description("描述：文档构造器")
                .termsOfServiceUrl("http://localhost:9002")
                .contact("Cory")
                .version("版本号：1.0")
                .build();
    }
}