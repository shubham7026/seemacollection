package com.seemacollection.seemacollection.mysqldao;

import com.seemacollection.seemacollection.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthEntityDao extends JpaRepository<UserAuthEntity , Long> {
     UserAuthEntity findByUsername(String username);
}
