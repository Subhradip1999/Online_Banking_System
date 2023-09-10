/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.loginStatus;

public interface UserLoginUpdateRepo extends JpaRepository<loginStatus, String>{

}
