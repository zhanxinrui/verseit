package com.screwmachine55open.verseit.config;

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
 * @author ：xrzhan
 * @date ：Created in 2019/4/29 19:25
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))//扫描com路径下的api文档
                .paths(PathSelectors.any())//路径判断
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("《Verseit 前后端接口描述》")//标题
                .description("项目主页：http://verseit.top 不要点")//描述
                .termsOfServiceUrl("http://www.google.com.hk")//（不可见）条款地址
                .contact(new Contact("verseit", "verseit", "verseit@qq.com"))//作者信息
                .version("6.6.6")//版本号
                .build();
    }
}
