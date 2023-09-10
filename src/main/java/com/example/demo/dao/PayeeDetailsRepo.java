package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserPayeeDetails;

public interface PayeeDetailsRepo extends JpaRepository<UserPayeeDetails, Integer>{
	
	@Query("from UserPayeeDetails upd where upd.customerId = ?1")
	List<UserPayeeDetails> findAllByCustomerID(String customerID);
	
	@Modifying
	@Query(nativeQuery = true, value="delete from USER_PAYEE_DETAILS up where up.account_number = ?1")
	@Transactional
	void deletePayeeByAccount_number(String account_number);
	
	@Query("from UserPayeeDetails upd where upd.status = ?1")
	List<UserPayeeDetails> findAllByStatus(String status);
	
	@Modifying
	@Query("update UserPayeeDetails pd set pd.status = ?1 where pd.status = ?2")
	@Transactional
	void setPayeeInfoByStatus(String updateStat, String oldState);
	
//	@Modifying
//	@Query("update UserPayeeDetails pd set ")
//	@Transactional
//	void setPayeeInfoByStatus();

}
