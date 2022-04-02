package com.fabhotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabhotels.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	User findByEmail(String email); 
}
