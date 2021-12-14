package com.jlm.common.config;

import com.jlm.common.config.intercepors.AdminInterceptor;
import com.jlm.common.config.intercepors.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;

    @Value("${cbs.imagesPath}")
    private String imagesPath;
    @Value("${cbs.ckImgPath}")
    private String ckImgPath;
    @Value("${cbs.Path}")
    private String path;

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/staticfile/**").addResourceLocations(path);
        registry.addResourceHandler("/uploadImg/**").addResourceLocations(imagesPath);
        registry.addResourceHandler("/ckupload/**").addResourceLocations(ckImgPath);
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/css/**")
                .excludePathPatterns("/admin/images/**")
                .excludePathPatterns("/admin/js/**")
                .excludePathPatterns("/admin/login.html")
                .excludePathPatterns("/admin/loginAdmin");
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/cart/**")
                .addPathPatterns("/cart.html")
                .addPathPatterns("/userInf.html");
    }
}
