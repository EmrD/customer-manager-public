package com.example.demo.Middlewares;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LoggerMiddleware implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime nowUnformatted = LocalDateTime.now();
        String now = dateFormatter.format(nowUnformatted);

        switch (request.getRequestURI()){
            case "/api/v1/postgresqlController/users/getAll":
                System.out.println("Request: Get All Users: " + request.getRemoteAddr() + " - " + now);
                break;
            case "/api/v1/postgresqlController/users/update":
                System.out.println("Request: Update User: " + request.getRemoteAddr() + " - " + now);
                break;
            case "/api/v1/postgresqlController/users/delete":
                System.out.println("Request: Delete User: " + request.getRemoteAddr() + " - " + now);
                break;
            case "/api/v1/postgresqlController/users/create":
                System.out.println("Request: Create User: " + request.getRemoteAddr() + " - " + now);
                break;
            case "/api/v1/postgresqlController/users/get":
                System.out.println("Request: Get User: " + request.getRemoteAddr() + " - " + now);
                break;
        }
        return true;
    }
}