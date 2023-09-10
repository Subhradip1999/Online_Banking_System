package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.CustomerRecurringAccount;

public interface CustomerRdDetailsRepo extends JpaRepository<CustomerRecurringAccount, Integer>{

	@Query("from CustomerRecurringAccount ctra where ctra.customerId = ?1")
	List<CustomerRecurringAccount> findAllByCustomerID(String customerId);
	
	@Modifying
	@Query("update CustomerRecurringAccount cusRD set cusRD.finalAmount=?2 , cusRD.returnAmount=?3 , cusRD.paymentStatus=?4 where cusRD.customerId = ?1")
	@Transactional
	void setCustomerRecurringAccountInfoBycustomerId(String customerId,String finalAmount,String returnAmount,String paymentStatus);

	@Query("from CustomerRecurringAccount ctra where ctra.customerId = ?1")
	CustomerRecurringAccount findByCustomerID(String customerId);
	
}
