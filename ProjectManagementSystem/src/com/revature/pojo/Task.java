package com.revature.pojo;

import com.esotericsoftware.kryo.Kryo;

public class Task {

	private static int taskCount = 0;
	private int taskId;
	private String taskName;
	private String description;
	private int employeeId;

	public Task() {
		this(null, null, 0);
	}
	
	public Task(String taskName, String description, int employeeId) {
		super();
		this.taskId = taskCount;
		taskCount++;
		this.taskName = taskName;
		this.description = description;
		this.employeeId = employeeId;
	}

	public int getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Task) {
			Task task = (Task) obj;
			if(this.taskId == task.taskId && this.taskName.equals(task.taskName) && 
					this.description.equals(task.description) && this.employeeId == task.employeeId) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "TaskId: "+ getTaskId() + "\nTaskName: " + getTaskName() + "\nDescription: " + getDescription() + "\nEmployeeId:" + employeeId;
	}
}

