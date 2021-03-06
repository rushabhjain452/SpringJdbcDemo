package com.tutorialspoint.controller;

import java.util.List;

import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialspoint.model.Employee;
import com.tutorialspoint.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeWithDept>> getAllEmployees(){
		Result<List<EmployeeWithDept>> empList = employeeService.findAllEmployees();
		return new ResponseEntity<>(empList.getData(), HttpStatus.valueOf(empList.getCode()));
	}
	
	@GetMapping(path="/{empid}")
	public ResponseEntity<EmployeeWithDept> getEmployeeById(@PathVariable int empid) {
		Result<EmployeeWithDept> empResult = employeeService.findEmployeeById(empid);
		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
	}
	
	@GetMapping(path="/name/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name) {
		Result<List<Employee>> empResult = employeeService.findEmployeeByName(name);
		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody(required=true) Employee e) {
		Result<Employee> empResult = employeeService.addEmployee(e);
		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
	}

	@PutMapping("/{empid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int empid, @RequestBody(required=true) Employee e) {
		Result<Employee> empResult = employeeService.updateEmployee(empid, e);
		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
	}

	@DeleteMapping("/{empid}")
	public ResponseEntity<Result<String>> deleteEmployee(@PathVariable int empid) {
		Result<String> empResult = employeeService.deleteEmployee(empid);
		return new ResponseEntity<>(empResult, HttpStatus.valueOf(empResult.getCode()));
	}
}
