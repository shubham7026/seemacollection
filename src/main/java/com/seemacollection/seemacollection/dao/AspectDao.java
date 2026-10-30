package com.seemacollection.seemacollection.dao;


import com.seemacollection.seemacollection.entity.SignUpCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface AspectDao extends JpaRepository<SignUpCountEntity, Date> {
}
