package com.fabhotels.response;

import com.fabhotels.entity.TransactionInfo;

public class PassbookResponse {
	
	private String date;

	private String transaction;
	
	private double debit;
	
	private double credit;
	
	private double balance;

	public PassbookResponse(TransactionInfo allTransactions) {
		this.date=allTransactions.getDate();
		this.transaction=allTransactions.getTransaction();
		this.debit=allTransactions.getDebit();
		this.credit=allTransactions.getCredit();
		this.balance=allTransactions.getBalance();
	}
	
	public PassbookResponse() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
