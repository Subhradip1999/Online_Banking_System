/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CustomerAccountDetails;

public interface UserRepo extends JpaRepository<CustomerAccountDetails, String>{
		
	@Query("select cad.accNo from CustomerAccountDetails cad where cad.customerId = ?1")
	String findAccNoByCustomerID(String customerID);
}
