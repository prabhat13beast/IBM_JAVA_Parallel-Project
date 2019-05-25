package com.cg.myapp.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.cg.myapp.beans.*;
import com.cg.myapp.service.*;

public class MainClass {

	public static void main(String[] args) {
		int exit = 0;
		int choice;
		Customer customer = null;
		Scanner sc = new Scanner(System.in);
		ServiceClass service = new ServiceClass();
		while (exit == 0) {
			System.out.println("Enter choice of operation you want to perform");
			System.out.println("1. Create new Account \n" + "2. View Account balance \n" + "3. Deposit Amount \n"
					+ "4. Withdraw Amount \n" + "5. Transfer Amount \n" + "6. Exit Application");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Welcome!");
					System.out.println("Please enter your name : ");
					String customer_name = sc.nextLine();
					int account_no = service.generateAccountNo();
					EnterPasswordAgain: while (true) {
						System.out.println("Please enter your password : ");
						String password1 = sc.nextLine();
						System.out.println("Please re enter your password : ");
						String password2 = sc.nextLine();

						if (service.validatePassword(password1, password2)) {
							customer = new Customer(account_no, customer_name, 0, password1);
							service.createAccount(customer);
							System.out.println("Account number created for you is: " + account_no);
							break;
						} else {
							continue EnterPasswordAgain;
						}
					}
					break;

				case 2:
					while (true) {
						System.out.println("Please enter your account number : ");
						int accountCheck = sc.nextInt();
						sc.nextLine();
						System.out.println("Please enter your password : ");
						String passwordCheck = sc.nextLine();
						if (service.validateLogin(accountCheck, passwordCheck)) {
							System.out.println("Your Account balance is: " + service.viewAccountBalance(accountCheck));
							break;
						} else
							continue;
					}
					break;
				case 3:
					while (true) {
						System.out.println("Please enter your account number : ");
						int accountCheck = sc.nextInt();
						sc.nextLine();
						System.out.println("Please enter your password : ");
						String passwordCheck = sc.nextLine();
						if (service.validateLogin(accountCheck, passwordCheck)) {
							System.out.println("Please enter amount you want to deposit : ");
							int amount = sc.nextInt();
							sc.nextLine();
							if (service.DepositBalance(accountCheck, amount))
								System.out.println("Amount deposited");
							break;
						} else
							continue;
					}
					break;

				case 4:
					while (true) {
						System.out.println("Please enter your account number : ");
						int accountCheck = sc.nextInt();
						sc.nextLine();
						System.out.println("Please enter your password : ");
						String passwordCheck = sc.nextLine();
						if (service.validateLogin(accountCheck, passwordCheck)) {
							System.out.println("Please enter amount you want to withdraw : ");
							int amount = sc.nextInt();
							sc.nextLine();
							if (service.withdrawBalance(accountCheck, amount))
								System.out.println("Amount withdrawn");
							break;
						} else
							continue;
					}
					break;

				case 5:
					while (true) {
						System.out.println("Please enter your account number : ");
						int accountCheck = sc.nextInt();
						sc.nextLine();
						System.out.println("Please enter your password : ");
						String passwordCheck = sc.nextLine();
						if (service.validateLogin(accountCheck, passwordCheck)) {
							System.out.println("Please enter account number you want to transfer to : ");
							int anotherAccount = sc.nextInt();
							sc.nextLine();
							if (!service.validateAccountNumber(anotherAccount)) {
								System.out.println("Enter amount you want to transfer");
								int amount = sc.nextInt();
								sc.nextLine();
								if (service.TransferAmount(accountCheck, anotherAccount, amount))
									System.out.println("Amount transfered");
								break;
							}
						} else {
							System.out.println("Please check your credentials");
							continue;
						}
					}
					break;

				case 6:
					exit = 1;
					break;

				default:
					System.out.println("Enter appropriate choice");
				}
			} catch (Exception e) {
				System.out.println("Please enter input in appropriate format " + e);
			}

		}

	}

}
