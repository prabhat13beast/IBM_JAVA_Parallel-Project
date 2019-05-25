package com.cg.myapp.service;

import java.sql.Array;
import java.util.ArrayList;

import com.cg.myapp.beans.Customer;
import com.cg.myapp.dao.Dao;
//import com.cg.myapp.dao.Dao_Transaction;

public class ServiceClass {

	Dao dao = new Dao();
	// Dao_Transaction daot = new Dao_Transaction();

	public boolean validatePassword(String password1, String password2) {
		if (password1.equals(password2)) {
			if (password1.length() >= 8)
				return true;
			else {
				System.out.println("Password length must be atleast 8");
				return false;
			}
		} else
			return false;
	}

	public void createAccount(Customer customer) {
		int account_no = customer.getAccount_no();
		String password = customer.getPassword();
		String customer_name = customer.getCustomer_name();
		int balance = customer.getBalance();
		dao.createAccount(account_no, password, customer_name, balance);
	}

	public int generateAccountNo() {
		String num = "";
		int length = 0;
		int store;
		while (length < 8) {
			store = (int) (Math.random() * 9);
			num = num + store;
			length++;
		}

		int account_no = Integer.parseUnsignedInt(num);

		if (dao.validateAccountNumber(account_no))
			return account_no;
		else
			generateAccountNo();

		return 0;
	}

	public boolean validateLogin(int accountCheck, String passwordCheck) {
		return dao.validateLogin(accountCheck, passwordCheck);
	}

	public int viewAccountBalance(int accountCheck) {
		return dao.viewAccountBalance(accountCheck);
	}

	public boolean DepositBalance(int accountCheck, int amount) {
		return dao.DepositBalance(accountCheck, amount);
	}

	public boolean withdrawBalance(int accountCheck, int amount) {
		return dao.withdrawBalance(accountCheck, amount);
	}

	public boolean TransferAmount(int accountCheck, int anotherAccount, int amount) {
		return dao.TransferAmount(accountCheck, anotherAccount, amount);
	}

	public boolean validateAccountNumber(int anotherAccount) {
		return dao.validateAccountNumber(anotherAccount);
	}

//	public ArrayList<String> viewTransactionDetails(int accountCheck)
//	{
//		return daot.viewMessages(accountCheck);
//		
//	}

}
