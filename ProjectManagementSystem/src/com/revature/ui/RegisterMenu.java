package com.revature.ui;

import java.util.Scanner;

public class RegisterMenu implements Menu{

private Scanner scan;
	
	private Menu loginMenu;
	private Menu registerMenu;
	private Menu nextMenu;
	private Menu previousMenu;
	
	
	public RegisterMenu() {
		this(null);
	}
	
	public RegisterMenu(Menu previousMenu) {
		super();
		this.loginMenu = loginMenu;
		this.registerMenu = registerMenu;
		this.previousMenu = previousMenu;
	}
	
	@Override
	public Menu advance() {
		if(this != nextMenu)
			nextMenu.setPreviousMenu(this);
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		if(scantemp.hasNextInt()) {
			int input = scantemp.nextInt();
			switch(input) {
				case 1:
					nextMenu = loginMenu;
					return;
				case 2:
					nextMenu = registerMenu;
					return;
			}
		}
		
		System.out.println("invalid input. Please enter a number");
		nextMenu = this;
		
	}

	@Override
	public Menu getPreviousMenu() {
		return previousMenu;
	}

	@Override
	public Scanner getScanner() {
		return scan;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scan = scan;
	}

	@Override
	public void setPreviousMenu(Menu previousMenu) {
		this.previousMenu = previousMenu;
		
	}

}
