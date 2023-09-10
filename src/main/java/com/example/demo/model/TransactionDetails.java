package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionDetails {
	
	@Id
	@GeneratedValue
	private int id;
	private String senderAccNo;
	private String receiverAccNo;
	private String senderBranchName;
	private String receiverBranchName;
	private String amount;
	private String transactionStatus;
	private String transactionID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(String senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public String getReceiverAccNo() {
		return receiverAccNo;
	}
	public void setReceiverAccNo(String receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	
	public String getSenderBranchName() {
		return senderBranchName;
	}
	public void setSenderBranchName(String senderBranchName) {
		this.senderBranchName = senderBranchName;
	}
	public String getReceiverBranchName() {
		return receiverBranchName;
	}
	public void setReceiverBranchName(String receiverBranchName) {
		this.receiverBranchName = receiverBranchName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	@Override
	public String toString() {
		return "TransactionDetails [id=" + id + ", senderAccNo=" + senderAccNo + ", receiverAccNo=" + receiverAccNo
				+ ", senderBranchName=" + senderBranchName + ", receiverBranchName=" + receiverBranchName + ", amount="
				+ amount + ", transactionStatus=" + transactionStatus + ", transactionID=" + transactionID + "]";
	}
	

}
