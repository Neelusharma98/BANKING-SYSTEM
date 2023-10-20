package com.techlabs.controllers;

import java.math.BigDecimal;
import java.util.Date;

public class NewTransaction {
    private int transactionid;
    private int accountNumber;
    private int receiverAccountNumber;
    private String type;
    private BigDecimal balance;
    private Date transactionDate;

    public NewTransaction() {
        
    }

    public NewTransaction(int transactionid, int accountNumber, int receiverAccountNumber, String type,
            BigDecimal balance, Date transactionDate) {
        this.transactionid = transactionid;
        this.accountNumber = accountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.type = type;
        this.balance = balance;
        this.transactionDate = transactionDate;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}

