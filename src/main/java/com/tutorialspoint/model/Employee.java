package com.tutorialspoint.model;

public class Employee {
	
	private int empid;
	private String name;
	private double salary;
	private int deptid;
	
	public Employee() {
		super();
	}

	public Employee(int empid, String name, double salary, int deptid) {
		super();
		this.empid = empid;
		this.name = name;
		this.salary = salary;
		this.deptid = deptid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", salary=" + salary + "]";
	}
	
}
