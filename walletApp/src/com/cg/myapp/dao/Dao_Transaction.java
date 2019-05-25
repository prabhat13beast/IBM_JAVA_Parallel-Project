//package com.cg.myapp.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class Dao_Transaction {
//	static Connection dbCon;
//	static PreparedStatement statement;
//
//	public Dao_Transaction() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
//		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println("Issues while connecting to databse : " + e);
//		}
//
//	}
//
////	public void storeMessages(int accountNumber, String message) {
////		java.util.Date date = new java.util.Date();
////
////		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
////		java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
////
////		try {
////			statement = dbCon.prepareStatement("insert into transactioninfo values (?,?,?,?)");
////			statement.setInt(1, accountNumber);
////			statement.setDate(2, sqlDate);
////			statement.setTimestamp(3, sqlTime);
////			statement.setString(4, message);
////
////			if (statement.executeUpdate() > 0) {
////				// System.out.println("Transaction details entered Successfully");
////			}
////
////		} catch (SQLException e) {
////			System.out.println(e);
////		}
////	}
//
//	public ArrayList<String> viewMessages(int accountNumber) {
//		String message = "";
//		ArrayList<String> list = new ArrayList<String>();
//		try {
//			statement = dbCon.prepareStatement("select * from transactioninfo where account_number = ?");
//			statement.setInt(1, accountNumber);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				message = rs.getString("transaction_message") + " on " + rs.getDate("transaction_date") + " at "
//						+ rs.getTimestamp("transaction_time");
//				list.add(message);
//				message = "";
//			}
//		} catch (SQLException e) {
//
//		}
//		return list;
//	}
//
//}
