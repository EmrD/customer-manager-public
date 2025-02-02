package com.example.demo.Middlewares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.example.demo.Middlewares")
public class InterceptorConfig implements WebMvcConfigurer
{
    @Autowired
    private LoggerMiddleware loggerMiddleware;

    @Autowired
    private JWTTokenCheckerMiddleware jwtTokenCheckerMiddleware;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(loggerMiddleware);
        registry.addInterceptor(jwtTokenCheckerMiddleware);
    }
}
