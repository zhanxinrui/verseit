package com.screwmachine55open.verseit.config;
/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 18:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.screwmachine55open.verseit.VerseitApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 增加静态资源的映射目录，否则浏览器不能直接访问静态资源
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image");

    }



    //拦截器的加载时间在spring上下文之前加载,所以需要提前注册拦截器，否则会出现service 在拦截器中无法使用的情况
    @Bean
    public HandlerInterceptor getUserInterceptor(){
        return new VerseitInterception();
    }

    /**
     * 添加一个拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login").excludePathPatterns("/user/logout");

//        registry.addInterceptor(new VerseitInterception()).addPathPatterns("/**");
//        super.addInterceptors(registry);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationInterceptor())
//                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//    }


    /**
     * 动态过滤器，不懂。。
     * */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect
        );


        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastConverter);
    }


    @Bean
    public FastJsonViewResponseBodyAdvice FastJsonViewResponseBodyAdvice() {
        FastJsonViewResponseBodyAdvice advice = new FastJsonViewResponseBodyAdvice();
        return advice;
    }
}
