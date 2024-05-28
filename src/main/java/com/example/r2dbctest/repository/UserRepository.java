package com.example.r2dbctest.repository;

import com.example.r2dbctest.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, Long> {}
