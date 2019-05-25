package com.cg.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	Connection dbCon;
	PreparedStatement statement;

	// Dao_Transaction daot;
	public Dao() {
		// daot = new Dao_Transaction();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues while connecting to databse : " + e);
		}

	}

	public boolean validateAccountNumber(int account_no) {
		try {
			statement = dbCon.prepareStatement("select account_no from accountinfo");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				if (rs.getInt("account_no") == account_no)
					return false;

			}

		} catch (SQLException e) {
			System.out.println("Issues while retriving account numbers " + e);
		}

		return true;
	}

	public void createAccount(int account_no, String password, String customer_name, int balance) {
		try {
			statement = dbCon.prepareStatement("insert into accountinfo values (?,?,?,?)");
			statement.setInt(1, account_no);
			statement.setString(2, password);
			statement.setString(3, customer_name);
			statement.setInt(4, balance);
			if (statement.executeUpdate() > 0) {
				System.out.println("Account created Successfully");
				// daot.storeMessages(account_no, "Account created Successfully");
			}
		} catch (SQLException e) {
			System.out.println("Some issues while inserting data into database " + e);
		}

	}

	public boolean validateLogin(int accountCheck, String passwordCheck) {
		String password = "";
		if (!validateAccountNumber(accountCheck)) {
			try {
				statement = dbCon.prepareStatement("select password from accountinfo where account_no = ?");
				statement.setInt(1, accountCheck);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					password = rs.getString("password");
				}

				if (passwordCheck.equals(password))
					return true;
				else {
					System.out.println("Wrong password. Try entering correct password");
					return false;
				}
			} catch (SQLException e) {

				System.out.println("Issues while retriving password " + e);
			}
		} else {
			System.out.println("Sorry! No such account number exists.");
			return false;
		}
		return false;
	}

	public int viewAccountBalance(int accountCheck) {
		int balance = 0;
		try {
			statement = dbCon.prepareStatement("select balance from accountinfo where account_no = ?");
			statement.setInt(1, accountCheck);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				balance = rs.getInt("balance");
			}

		} catch (SQLException e) {

			System.out.println("Issues while retriving password " + e);
		}
		return balance;
	}

	public int getBalance(int accountCheck) {
		int balance = 0;
		String getBalance = "select * from accountinfo where account_no = ?";
		try {
			statement = dbCon.prepareStatement(getBalance);
			statement.setInt(1, accountCheck);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				balance = rs.getInt("balance");

		} catch (Exception e) {
			System.out.println("some issues" + e);
		}
		return balance;
	}

	public boolean DepositBalance(int accountCheck, int amount) {
		int balance = getBalance(accountCheck) + amount;
		try {
			statement = dbCon.prepareStatement("update accountinfo set balance = ? where account_no = ?");
			statement.setInt(1, balance);
			statement.setInt(2, accountCheck);
			if (statement.executeUpdate() > 0) {
				System.out.println("Amount of Rs " + amount + " deposited into account");
				return true;
			} else
				return false;

		} catch (SQLException e) {
			System.out.println("Some issue while depositing account " + e);
		}
		return false;
	}

	public boolean withdrawBalance(int accountCheck, int amount) {
		int balance = getBalance(accountCheck);
		if (balance - amount < 0) {
			System.out.println("The amount you are trying to withdraw cannot be withdrawn");
			return false;
		} else {
			balance = balance - amount;
			try {
				statement = dbCon.prepareStatement("update accountinfo set balance = ? where account_no = ?");
				statement.setInt(1, balance);
				statement.setInt(2, accountCheck);
				if (statement.executeUpdate() > 0) {
					System.out.println("Amount of Rs " + amount + " withdrawn from account");
					return true;
				} else
					return false;

			} catch (SQLException e) {
				System.out.println("Some issue while withdrawing amount " + e);
			}
		}
		return false;
	}

	public boolean TransferAmount(int accountCheck, int anotherAccount, int amount) {
		if (withdrawBalance(accountCheck, amount)) {
			if (DepositBalance(anotherAccount, amount)) {
				System.out.println("Amount of Rs " + amount + " transfered to account" + anotherAccount);
				return true;
			} else
				return false;
		} else
			return false;
	}

}
