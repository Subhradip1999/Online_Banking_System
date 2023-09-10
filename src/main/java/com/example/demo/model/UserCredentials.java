/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCredentials {
	
	@Id
	private String UserID;
	private String Password;
	private String UserType;
	private String LoginStatus;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		this.UserID = userID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		this.UserType = userType;
	}
	public String getLoginStatus() {
		return LoginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.LoginStatus = loginStatus;
	}
	@Override
	public String toString() {
		return "UserCredentials [UserID=" + UserID + ", Password=" + Password + ", UserType=" + UserType
				+ ", LoginStatus=" + LoginStatus + "]";
	}
	
	

}
