/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.CustomerAccountDetails;

public interface UserRepo extends JpaRepository<CustomerAccountDetails, String>{

}
