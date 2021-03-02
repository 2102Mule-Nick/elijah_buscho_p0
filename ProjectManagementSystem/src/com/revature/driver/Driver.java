package com.revature.driver;

import java.util.Scanner;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOKryo;
import com.revature.dao.ProjectDAO;
import com.revature.dao.ProjectDAOKryo;
import com.revature.dao.TaskDAO;
import com.revature.dao.TaskDAOKryo;

public class Driver {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
//set up DAOs
		EmployeeDAO employeeDao = new EmployeeDAOKryo();
		ProjectDAO projectDao = new ProjectDAOKryo();
		TaskDAO taskDao = new TaskDAOKryo();
		
//set up services
//		AuthService authService = new AuthServiceImpl(userDao);
		
//	setup menus
//		Menu register = new RegistractionMenu();
//		Menu login = new LoginMenu(authService);
//		Menu welcomeMenu = new WelcomeMenu(login, register);
//		((RegistractionMenu)register).setWelcomeMenu(welcomeMenu);
//		((RegistractionMenu)register).setAuthService(authService);
//		login.setScanner(scan);
//		register.setScanner(scan);
//		welcomeMenu.setScanner(scan);
//		Menu nextMenu = welcomeMenu;
		
//	ui loop
//		while(nextMenu != null) {
//			nextMenu.displayOptions();
//			
//			nextMenu = nextMenu.advance();
//			
//		}
		

		
	}

}
