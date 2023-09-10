package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CustomerAccTypeDetails;

public interface UserAccTypeRepo extends JpaRepository<CustomerAccTypeDetails, Integer>{
	
	@Query("from CustomerAccTypeDetails accInv where accInv.customerId = ?1")
	List<CustomerAccTypeDetails> findAllByCustomerID(String customerId);
	
	
}
