package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class lastAccountNumberCount {

	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	private String lastUpdatedNumber;

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLastUpdatedNumber() {
		return lastUpdatedNumber;
	}

	public void setLastUpdatedNumber(String lastUpdatedNumber) {
		this.lastUpdatedNumber = lastUpdatedNumber;
	}

	
}
