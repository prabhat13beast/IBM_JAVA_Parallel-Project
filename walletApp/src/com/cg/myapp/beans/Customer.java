package com.cg.myapp.beans;

public class Customer {
	private int account_no;
	private String customer_name;
	private int balance;
	private String password;

	public Customer(int account_no, String customer_name, int balance, String password) {
		this.account_no = account_no;
		this.customer_name = customer_name;
		this.balance = balance;
		this.password = password;
	}

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
