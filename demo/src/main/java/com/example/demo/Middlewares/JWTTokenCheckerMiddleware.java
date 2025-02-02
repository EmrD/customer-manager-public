package com.example.demo.Middlewares;

import com.example.demo.Services.JWTTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTTokenCheckerMiddleware implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (request.getRequestURI().startsWith("/api/v1/userController/login") || request.getRequestURI().startsWith("/api/v1/userController/register") || request.getRequestURI().startsWith("/swagger-ui") || request.getRequestURI().startsWith("/v3/api-docs") || request.getRequestURI().startsWith("/healthz")) {
            return true;
        }
        else {
            String token = request.getHeader("Authorization");
            if (token == null) {
                response.setStatus(401);
                return false;
            }
            String username = JWTTokenService.validateToken(token.replaceAll("Bearer " , ""));
            if (username == null) {
                response.setStatus(401);
                return false;
            }
            return true;
        }
    }
}