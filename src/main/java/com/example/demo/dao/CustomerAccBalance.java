package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.AccountBalance;
import com.example.demo.model.UserPayeeDetails;

public interface CustomerAccBalance extends JpaRepository<AccountBalance, String>{

	
	@Query("from AccountBalance accBal where accBal.customerID = ?1")
	List<AccountBalance> findAllByCustomerID(String customerID);
	
//	@Query("from AccountBalance acBl where acBl.customerID = ?1")
//	List<AccountBalance> findAllByCustomerID(String customerID);
	
	@Modifying
	@Query("update AccountBalance ab set ab.AccountBalance = ?1 where ab.AccountNumber = ?2")
	@Transactional
	void setAccountBalanceInfoByAccountBalance(String updateBal, String accNo);
	
	@Query("select accBl.AccountNumber from AccountBalance accBl where accBl.AccountNumber = ?1")
	String findAccountNumberByAccountNumber(String accBal);
	
	@Query("select accBl.AccountBalance from AccountBalance accBl where accBl.AccountNumber = ?1")
	String findAccountBalanceByAccountNumber(String accBal);
	
	@Query("from AccountBalance accBal where accBal.AccountNumber = ?1")
	AccountBalance findByAccNo(String AccountNumber);
	
}
