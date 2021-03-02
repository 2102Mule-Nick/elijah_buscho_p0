package com.revature.pojo;

import java.util.ArrayList;

public class Employee {

	public static int employeeCount = 0;

	public enum Role {
		EMPLOYEE, MANAGER, ADMIN
	}

	private Role role;
	private ArrayList<Integer> projects;
	private String username;
	private String password;
	private int employeeId;

	public Employee() {
		this(Role.EMPLOYEE, null, null);
	}
	
	public Employee(Role role, String username, String password) {
		this(role, new ArrayList<Integer>(), username, password);
	}

	public Employee(Role role, ArrayList<Integer> projects, String username, String password) {
		super();
		this.role = role;
		this.projects = projects;
		this.username = username;
		this.password = password;
		this.employeeId = employeeCount;
		employeeCount++;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ArrayList<Integer> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Integer> projects) {
		this.projects = projects;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Employee) {
			Employee employee = (Employee) obj;
			if(this.employeeId == employee.employeeId && this.username.equals(employee.username) && this.password.equals(employee.password) && this.role.equals(employee.role)) {
				return true;
			}
		}
		return false;
	}

}

