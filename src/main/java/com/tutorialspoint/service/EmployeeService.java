package com.tutorialspoint.service;

import java.util.List;

import com.tutorialspoint.exception.ResultException;
import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tutorialspoint.model.Employee;
import com.tutorialspoint.repository.employeerepo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	@Qualifier("employeeRepo")
	EmployeeRepository employeeRepository;
	
	public Result<List<EmployeeWithDept>> findAllEmployees(){
		List<EmployeeWithDept> empList  = employeeRepository.findAllEmployees();
		return new Result<>(200, empList);
	}
	
	public Result<EmployeeWithDept> findEmployeeById(int empid) {
		EmployeeWithDept emp = employeeRepository.findEmployeeById(empid);
		if(emp != null){
			return new Result<>(200, emp);
		}
		throw new ResultException(new Result<>(404, "Employee with EmployeeId: " + empid + " not found."));
	}
	
	public Result<List<Employee>> findEmployeeByName(String name) {
		List<Employee> empList = employeeRepository.findEmployeeByName(name);
		if(empList.size() > 0){
			return new Result<>(200, empList);
		}
		throw new ResultException(new Result<>(404, "No employee found with name : " + name));
	}

	public Result<Employee> addEmployee(Employee e) {
		int empid = employeeRepository.addEmployee(e);
		if(empid > 0){
			e.setEmpid(empid);
			// Code : 201 for Insert (POST)
			return new Result<>(201, e);
		}
		throw new ResultException(new Result<>(400, "Error in adding employee."));
	}

	public Result<Employee> updateEmployee(int empid, Employee e) {
		boolean result = employeeRepository.updateEmployee(empid, e);
		if(result){
			return new Result<>(200, e);
		}
		throw new ResultException(new Result<>(404, "Employee with EmployeeId: " + empid + " not found."));
	}

	public Result<String> deleteEmployee(int empid) {
		boolean result = employeeRepository.deleteEmployee(empid);
		if(result){
			return new Result<>(200, "Employee with id : " + empid + " deleted successfully.");
		}
		throw new ResultException(new Result<>(404, "Employee with EmployeeId: " + empid + " not found."));
	}
}
