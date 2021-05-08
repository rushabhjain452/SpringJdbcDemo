package com.tutorialspoint.controller;

import java.util.List;

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

import com.tutorialspoint.model.Department;
import com.tutorialspoint.model.DepartmentWithEmployees;
import com.tutorialspoint.model.Employee;
import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.response.Result;
import com.tutorialspoint.service.DepartmentService;

@RestController
@RequestMapping(path = "/dept")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments(){
		Result<List<Department>> deptList = departmentService.findAllDepartments();
		return new ResponseEntity<>(deptList.getData(), HttpStatus.valueOf(deptList.getCode()));
	}
	
	@GetMapping(path="/{deptid}")
	public ResponseEntity<Department> getEmployeeById(@PathVariable int deptid) {
		Result<Department> deptResult = departmentService.findDepartmentById(deptid);
		return new ResponseEntity<>(deptResult.getData(), HttpStatus.valueOf(deptResult.getCode()));
	}
	
	@GetMapping(path="/dept-with-employees")
	public ResponseEntity<List<DepartmentWithEmployees>> getAllDepartmentsWithEmployees(){
		Result<List<DepartmentWithEmployees>> deptList = departmentService.findAllDepartmentsWithEmployees();
		return new ResponseEntity<>(deptList.getData(), HttpStatus.valueOf(deptList.getCode()));
	}

	@PostMapping
	public ResponseEntity<Department> addDepartment(@RequestBody(required=true) Department d) {
		Result<Department> deptResult = departmentService.addDepartment(d);
		return new ResponseEntity<>(deptResult.getData(), HttpStatus.valueOf(deptResult.getCode()));
	}
	
	@PostMapping(path="/dept-with-employees")
	public ResponseEntity<DepartmentWithEmployees> addDepartmentWithEmployees(@RequestBody(required=true) DepartmentWithEmployees d) {
//		Result<Department> deptResult = departmentService.addDepartment(d);
//		return new ResponseEntity<>(deptResult.getData(), HttpStatus.valueOf(deptResult.getCode()));
		System.out.println("Dept Name : " + d.getName());
		System.out.println("No of employees : " + d.getEmployeeList().size());
		for(Employee e : d.getEmployeeList()) {
			System.out.println("Employee details : ");
			System.out.println("Name : " + e.getName());
			System.out.println("Salary : " + e.getSalary());
		}
		return new ResponseEntity<>(d, HttpStatus.OK);
	}

	
//	@PutMapping("/{empid}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable int empid, @RequestBody(required=true) Employee e) {
//		Result<Employee> empResult = employeeService.updateEmployee(empid, e);
//		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
//	}
//
//	@DeleteMapping("/{empid}")
//	public ResponseEntity<Employee> deleteEmployee(@PathVariable int empid) {
//		Result<Employee> empResult = employeeService.deleteEmployee(empid);
//		return new ResponseEntity<>(empResult.getData(), HttpStatus.valueOf(empResult.getCode()));
//	}

}
