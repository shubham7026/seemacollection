package com.seemacollection.seemacollection.mysqldao;

import com.seemacollection.seemacollection.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface UserDAO extends JpaRepository<UserEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.login = true WHERE u.id = :id")
    void updateLoginById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.points = :points WHERE u.id = :id")
    void updatePointsById(@Param("id") String id, @Param("points") Double points);
}
