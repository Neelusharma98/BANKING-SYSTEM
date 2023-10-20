package com.techlabs.controllers;


import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private int transactionId; 
    private String accountNumber; 
    private String receiverAccountNumber; 
    private String type; 
    private BigDecimal amount; 
    private Date date;
    
    
    
    private BigDecimal balance;

    public Transaction(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
	public Transaction(int transactionId, String accountNumber, String receiverAccountNumber, String type,
			BigDecimal amount, Date date) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.type = type;
		this.amount = amount;
		this.date = date;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	} 
	public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    

}
