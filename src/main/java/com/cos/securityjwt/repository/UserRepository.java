package com.cos.securityjwt.repository;

import com.cos.securityjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // db에 접근할 repo

    Boolean existsByUsername(String username);
}
