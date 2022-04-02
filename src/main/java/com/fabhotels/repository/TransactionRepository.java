package com.fabhotels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabhotels.entity.TransactionInfo;

public interface TransactionRepository extends JpaRepository<TransactionInfo,Integer>{

	List<TransactionInfo> findByEmail(String email); 
	
}
