package com.example.demo.Core;

import com.example.demo.Models.usertable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<usertable, String> {} // testtable is our table name
