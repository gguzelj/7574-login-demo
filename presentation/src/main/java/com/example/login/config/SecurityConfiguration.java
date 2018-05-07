package com.example.login.config;


import com.example.login.security.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private SecurityInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor);
    }

    public void setApiInterceptor(SecurityInterceptor apiInterceptor) {
        this.apiInterceptor = apiInterceptor;
    }
}
