/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class loginStatus {
	@Id
	private String UserID;
	private String LoginStatus;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getLoginStatus() {
		return LoginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		LoginStatus = loginStatus;
	}
	
	
}
