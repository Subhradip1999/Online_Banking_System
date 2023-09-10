/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */
package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserCredentials;

public interface UserLoginRepo extends JpaRepository<UserCredentials, String>{
	
	@Modifying
	@Query("update UserCredentials uc set uc.Password = ?1, uc.UserType = ?2 ,uc.LoginStatus = ?3 where uc.UserID = ?4")
	@Transactional
	void setUserInfoById(String Password, String UserType, String LoginStatus,String UserID);
}
