package com.example.demo.Controllers;

import com.example.demo.Core.AccountRepository;
import com.example.demo.Models.usertable;
import com.example.demo.Services.JWTTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/userController")
public class UserController {

    final AccountRepository accountRepository;

    public UserController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> login(String username , String password) {
        Map<String , String> result = new HashMap<>();
        try {
            usertable user = new usertable();
            user.setName(username);
            user.setPassword(password);
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                result.put("status" , "success");
                result.put("message" , "Login successful");
                result.put("token" , JWTTokenService.generateToken(username));
                return ResponseEntity.ok().body(result);
            } else {
                result.put("status" , "failed");
                result.put("message" , "Invalid credentials");
                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data: " + e);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String , String>> register(@RequestBody usertable user) {
        Map<String , String> result = new HashMap<>();
        try {
            accountRepository.save(user);
            result.put("status" , "success");
            result.put("message" , "User created successfully");
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data");
            return ResponseEntity.badRequest().body(result);
        }
    }
}