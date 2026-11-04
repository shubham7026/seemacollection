package com.seemacollection.seemacollection.repository;

import com.seemacollection.seemacollection.entity.UserAuthEntity;
import com.seemacollection.seemacollection.mysqldao.UserAuthEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserAuthRepository implements UserDetailsService {
    @Autowired
    private UserAuthEntityDao userAuthEntityDao;
    public void saveUser (Collection<UserAuthEntity> userAuthEntities){
        userAuthEntities.forEach(
                user -> userAuthEntityDao.save(user)
        );
    }

    public void deleteAll() {
        userAuthEntityDao.deleteAll();
    }

    @Override
    public UserAuthEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthEntityDao.findByUsername(username);
    }
}
