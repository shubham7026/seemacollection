package com.seemacollection.seemacollection.dao;

import com.seemacollection.seemacollection.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<UserEntity, String> {
}
