package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.lastAccountNumberCount;

public interface lastUpdateAccNumber extends JpaRepository<lastAccountNumberCount, Integer>{

	@Modifying
	@Query("update lastAccountNumberCount lac set lac.lastUpdatedNumber = ?1 where lac.Id = ?2")
	@Transactional
	void setLastAccNumberById(String lastUpdatedNumber, int Id);
}
