package com.example.demo.Core;

import com.example.demo.Models.testtable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<testtable, String> {} // testtable is our table name
