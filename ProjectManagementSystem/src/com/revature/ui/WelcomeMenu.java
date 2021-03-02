package com.revature.ui;

import java.util.Scanner;

public class WelcomeMenu implements Menu{

	private Scanner scan;
	
	private Menu loginMenu;
	private Menu registerMenu;
	private Menu nextMenu;
	private Menu previousMenu;
	
	private static final String[] options = {
			"Welcome!",
			"Please select an option: ",
			"1 - Login",
			"2 - Register"
	};
	
	public WelcomeMenu(Menu loginMenu, Menu registerMenu) {
		this(loginMenu, registerMenu, null);
	}
	
	public WelcomeMenu(Menu loginMenu, Menu registerMenu, Menu previousMenu) {
		super();
		this.loginMenu = loginMenu;
		this.registerMenu = registerMenu;
		this.previousMenu = previousMenu;
	}
	
	@Override
	public Menu advance() {
		if(nextMenu != this)
			nextMenu.setPreviousMenu(this);
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		for(String option : options) {
			System.out.println(option);
		}
		String answer = scan.nextLine();
		Scanner scantemp = new Scanner(answer);
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
	public Scanner getScanner() {
		return scan;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scan = scan;
	}

	@Override
	public Menu getPreviousMenu() {
		return previousMenu;
	}

	@Override
	public void setPreviousMenu(Menu previousMenu) {
		this.previousMenu = previousMenu;
	}

}
