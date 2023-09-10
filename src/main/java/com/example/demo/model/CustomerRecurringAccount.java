package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class CustomerRecurringAccount {
	
	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	private String customerId;
	private String fullName;
	private String panNumber;
	private String account_number;
	private String branch;
	private String AccountType;
	private String RD_AccountNo;
	private String nominee;
	private String InvestedAmount;
	private String tenure;
	private String StartDate;
	private String endDate;
	private String finalAmount;
	private String returnAmount;	
	private String presentDate;
	private String paymentStatus;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
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
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getRD_AccountNo() {
		return RD_AccountNo;
	}
	public void setRD_AccountNo(String rD_AccountNo) {
		RD_AccountNo = rD_AccountNo;
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	public String getInvestedAmount() {
		return InvestedAmount;
	}
	public void setInvestedAmount(String investedAmount) {
		InvestedAmount = investedAmount;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}
	public String getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}
	public String getPresentDate() {
		return presentDate;
	}
	public void setPresentDate(String presentDate) {
		this.presentDate = presentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
	
	

}
