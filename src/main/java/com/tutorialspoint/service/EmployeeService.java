package com.tutorialspoint.service;

import java.util.List;

import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tutorialspoint.model.Employee;
import com.tutorialspoint.repository.employeerepo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	@Qualifier("employeeRepo")
	EmployeeRepository employeeRepository;
	
	public Result<List<EmployeeWithDept>> findAllEmployees(){
		List<EmployeeWithDept> empList  = employeeRepository.findAllEmployees();
//		System.out.println("Size : " + empList.size());
		if(empList.size() > 0){
			return new Result<>(200, empList);
		}
		else{
//			return new Result<>(204, "No employees found.");
			return new Result<>(404, "No employees found.");
		}
	}
	
	public Result<EmployeeWithDept> findEmployeeById(int empid) {
		EmployeeWithDept emp = employeeRepository.findEmployeeById(empid);
		if(emp != null){
			return new Result<>(200, emp);
		}
		else{
			return new Result<>(404, "No employee found with empid : " + empid);
		}
	}
	
	public Result<Employee> findEmployeeByName(String name) {
		List<Employee> empList = employeeRepository.findEmployeeByName(name);
		if(empList.size() > 0){
			return new Result<>(200, empList.get(0));
		}
		else{
			return new Result<>(404, "No employee found with name : " + name);
		}
	}

	public Result<Employee> addEmployee(Employee e) {
		int empid = employeeRepository.addEmployee(e);
		if(empid > 0){
			e.setEmpid(empid);
			// Code : 201 for Insert (POST)
			return new Result<>(201, e);
		}else {
			return new Result<>(400, "Error in adding employee.");
		}
	}

	public Result<Employee> updateEmployee(int empid, Employee e) {
		boolean result = employeeRepository.updateEmployee(empid, e);
		if(result){
			return new Result<>(200, e);
		}
		else{
			return new Result<>(404, "Unable to delete. Given employee id : " + empid + " not found.");
		}
	}

	public Result<Employee> deleteEmployee(int empid) {
		boolean result = employeeRepository.deleteEmployee(empid);
		if(result){
			return new Result<>(200, "Employee with id : " + empid + " deleted successfully.");
		}
		else{
			return new Result<>(404, "Unable to delete. Given employee id : " + empid + " not found.");
		}
	}
}
