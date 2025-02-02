package com.example.demo.Controllers;

import com.example.demo.Core.UserRepository;
import com.example.demo.Models.testtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/postgresqlController")
@Service
class PostgresqlControllers {
    final UserRepository userRepository;

    @Autowired
    public PostgresqlControllers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users/create")
    public ResponseEntity<Map<String , String>> createUser(@RequestBody testtable user) {
        Map<String , String> result = new HashMap<>();
        try
        {
            userRepository.save(user);
            result.put("status" , "success");
            result.put("message" , "User created successfully");
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/users/get")
    public ResponseEntity<Map<String , String>> getUser(@RequestParam String user)
    {
        Map<String , String> result = new HashMap<>();
        try {
            testtable foundUser = new testtable();
            foundUser.setName(userRepository.findById(user).get().getName());
            foundUser.setEmail(userRepository.findById(user).get().getEmail());
            foundUser.setAge(userRepository.findById(user).get().getAge());

            result.put("status" , "success");
            result.put("name" , foundUser.getName());
            result.put("email" , foundUser.getEmail());
            result.put("age" , String.valueOf(foundUser.getAge()));
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<Map<String,String>> deleteUser(@RequestParam String user)
    {
        Map<String , String> result = new HashMap<>();
        try {
            userRepository.deleteById(user);
            result.put("status" , "success");
            result.put("message" , "User " + user + " deleted successfully");
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/users/update")
    public ResponseEntity<Map<String , String>> updateUser(@RequestBody testtable user)
    {
        Map<String , String> result = new HashMap<>();
        try {
            userRepository.save(user);
            result.put("status" , "success");
            result.put("message" , "User updated successfully");
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/users/getAll")
    public ResponseEntity<Map<String , String>> getAllUsers(@RequestParam String authToken)
    {
        Map<String , String> result = new HashMap<>();
        try
        {
            List<testtable> users = userRepository.findAll().stream().toList();

            List<Map<String , String>> usersList = users.stream().map(user -> {
                Map<String , String> userMap = new HashMap<>();
                userMap.put("name" , user.getName());
                userMap.put("email" , user.getEmail());
                userMap.put("age" , String.valueOf(user.getAge()));
                return userMap;
            }).toList();

            result.put("status" , "success");
            result.put("users" , usersList.toString());
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            result.put("status" , "failed");
            result.put("message" , "Error fetching data " + e);
            return ResponseEntity.badRequest().body(result);
        }
    }
}