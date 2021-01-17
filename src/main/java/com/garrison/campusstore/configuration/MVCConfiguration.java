package com.garrison.campusstore.configuration;

import com.garrison.campusstore.interceptor.local.userLoginInterceptor;
import com.garrison.campusstore.interceptor.shopadmin.ShopLoginInterceptor;
import com.garrison.campusstore.interceptor.shopadmin.ShopPermissionInterceptor;
import com.garrison.campusstore.interceptor.superadmin.SuperAdminLoginInterceptor;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;

@Configuration
//@EnableWebMvc
public class MVCConfiguration implements WebMvcConfigurer {


    /**
     * 创建viewResolver
     */
    /**
     * 文件上传解析器
     */
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver createMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }
    /**
     * 定义kaptcha
     */
    @Value("${kaptcha.border}")
    private String border;

    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;

    @Value("${kaptcha.image.width}")
    private String width;

    @Value("${kaptcha.textproducer.char.string}")
    private String cString;

    @Value("${kaptcha.image.height}")
    private String height;

    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;

    @Value("${kaptcha.noise.color}")
    private String nColor;

    @Value("${kaptcha.textproducer.char.length}")
    private String clength;

    @Value("${kaptcha.textproducer.font.names}")
    private String fnames;

    /**
     * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws ServletException {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
        servlet.addInitParameter("kaptcha.border", border);// 无边框
        servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor); // 字体颜色
        servlet.addInitParameter("kaptcha.image.width", width);// 图片宽度
        servlet.addInitParameter("kaptcha.textproducer.char.string", cString);// 使用哪些字符生成验证码
        servlet.addInitParameter("kaptcha.image.height", height);// 图片高度
        servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);// 字体大小
        servlet.addInitParameter("kaptcha.noise.color", nColor);// 干扰线的颜色
        servlet.addInitParameter("kaptcha.textproducer.char.length", clength);// 字符个数
        servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);// 字体
        return servlet;
    }
    /**
     *添加addResourceHandler，代替SSM中 修改tomcat docBase的功能
     */
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/jiahaoge/Personal/面试资料/项目笔记/校园商铺项目资料/image/upload/");
	}
    /**
     * 添加拦截器
     */

    public void addInterceptors(InterceptorRegistry registry) {
        /** 登陆验证拦截部分**/
        String userInterceptPath = "/frontend/**";
        InterceptorRegistration userLoginIR = registry.addInterceptor(new userLoginInterceptor());
        userLoginIR.addPathPatterns(userInterceptPath);

        /** 店家管理系统拦截部分 **/
        String interceptPath = "/shopadmin/**";
        // 注册拦截器
        InterceptorRegistration shoploginIR = registry.addInterceptor(new ShopLoginInterceptor());
        // 配置拦截的路径
        shoploginIR.addPathPatterns(interceptPath);

//        /** 超级管理员系统拦截部分 **/
//        interceptPath = "/superadmin/**";
//        // 注册拦截器
//        InterceptorRegistration superadminloginIR = registry.addInterceptor(new SuperAdminLoginInterceptor());
//        // 配置拦截的路径
//        superadminloginIR.addPathPatterns(interceptPath);
//        superadminloginIR.excludePathPatterns("/superadmin/login");
//        superadminloginIR.excludePathPatterns("/superadmin/logincheck");
//        superadminloginIR.excludePathPatterns("/superadmin/main");
//        superadminloginIR.excludePathPatterns("/superadmin/top");
//        superadminloginIR.excludePathPatterns("/superadmin/clearcache4area");
//        superadminloginIR.excludePathPatterns("/superadmin/clearcache4headline");
//        superadminloginIR.excludePathPatterns("/superadmin/clearcache4shopcategory");
    }





}
