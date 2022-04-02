package com.fabhotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabhotels.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer>{

	Wallet findByEmail(String email);
	
}
