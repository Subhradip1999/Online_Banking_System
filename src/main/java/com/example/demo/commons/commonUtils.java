package com.example.demo.commons;

import com.example.demo.model.UserCredentials;

public class commonUtils {
	int num = 1001;
	public void updateLoginStatus(UserCredentials uc) {
		
		
	}
	
	public String generateCustomerID() {
		String customerId = "CU";
		num = num+1;
		customerId = customerId+""+num;
		
		return customerId;
	}
}
