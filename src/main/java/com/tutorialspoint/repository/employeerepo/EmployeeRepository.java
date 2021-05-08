package com.tutorialspoint.repository.employeerepo;

import java.util.List;

import com.tutorialspoint.model.EmployeeWithDept;
import org.springframework.stereotype.Repository;

import com.tutorialspoint.model.Employee;

@Repository
public interface EmployeeRepository {

	List<EmployeeWithDept> findAllEmployees();
	
	List<EmployeeWithDept> findEmployeeById(int empid);
	
	List<Employee> findEmployeeByName(String name);
	
	int addEmployee(Employee e);
	
	int updateEmployee(int empid, Employee e);
	
	int deleteEmployee(int empid);
	
}
