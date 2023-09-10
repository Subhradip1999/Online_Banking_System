/**
 * created by: Subhradip Biswas (ADID : SU40109923)
 */

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerAccountDetails {
	
	@Id
	private String customerId;
	private String fullName;
	private String fatherName;
	private String dob;
	private String gender;
	private String permanentAdd;
	private String presentAdd;
	private String panNumber;
	private int mobileNo;
	private int landPh;
	private String accountType;
	private int openingBalance;
	private String accNo;
	private int cardNo;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPermanentAdd() {
		return permanentAdd;
	}
	public void setPermanentAdd(String permanentAdd) {
		this.permanentAdd = permanentAdd;
	}
	public String getPresentAdd() {
		return presentAdd;
	}
	public void setPresentAdd(String presentAdd) {
		this.presentAdd = presentAdd;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getLandPh() {
		return landPh;
	}
	public void setLandPh(int landPh) {
		this.landPh = landPh;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		return "CustomerAccountDetails [customerId=" + customerId + ", fullName=" + fullName + ", fatherName="
				+ fatherName + ", dob=" + dob + ", gender=" + gender + ", permanentAdd=" + permanentAdd
				+ ", presentAdd=" + presentAdd + ", panNumber=" + panNumber + ", mobileNo=" + mobileNo + ", landPh="
				+ landPh + ", accountType=" + accountType + ", openingBalance=" + openingBalance + ", accNo=" + accNo
				+ ", cardNo=" + cardNo + "]";
	}
	
	
	
	
	

}
