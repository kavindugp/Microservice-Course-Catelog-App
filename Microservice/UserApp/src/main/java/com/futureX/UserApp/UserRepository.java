package com.futureX.UserApp;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, BigInteger>{


    List<User> findByuserid(BigInteger id);
    List<User> findBycourseid(BigInteger id);
    
}
