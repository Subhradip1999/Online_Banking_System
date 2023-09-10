package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.TransactionDetails;
import com.example.demo.model.UserPayeeDetails;

public interface TransactionRepo extends JpaRepository<TransactionDetails, Integer>{
	
	@Modifying
	@Query("update TransactionDetails td set td.transactionStatus = ?1 where td.transactionStatus = ?2")
	@Transactional
	void setTransactionDetailsInfoBytransactionStatus(String updateStat, String oldState);
	
	
	@Query("from TransactionDetails td where td.transactionID = ?1")
	List<TransactionDetails> findAllByTransactionID(String transactionID);
	
	List<TransactionDetails> findTopByOrderByIdDesc();
}
