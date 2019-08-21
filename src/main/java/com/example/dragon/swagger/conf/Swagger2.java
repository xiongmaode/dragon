package com.example.dragon.swagger.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swaggger2配置文件
 * http://localhost:8888/swagger-ui.html
 *
 * @Author: Xiongmao
 * @Date: 2019-4-13
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    private static final String CONTROLLLER = "com.example.dragon.redis.controller";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLLER))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 APIs")
                .description("使用标准REST API,支持对象JSON和XML.")
                .version("1.0")
                .contact(new Contact("Songxinwei","","songxinwei@sinosoft.com.cn"))
                .build();
    }

}
