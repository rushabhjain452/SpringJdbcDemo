package com.tutorialspoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tutorialspoint.model.Department;
import com.tutorialspoint.model.DepartmentWithEmployees;
import com.tutorialspoint.repository.departmentrepo.DepartmentRepository;
import com.tutorialspoint.response.Result;

@Service
public class DepartmentService {
	
	@Autowired
	@Qualifier(value = "departmentRepo")
	DepartmentRepository departmentRepository;
	
	public Result<List<Department>> findAllDepartments(){
		List<Department> deptList  = departmentRepository.findAllDepartments();
		if(deptList.size() > 0){
			return new Result<>(200, deptList);
		}
		else{
			return new Result<>(400, "No departments found.");
		}
	}
	
	public Result<Department> findDepartmentById(int deptid) {
		List<Department> deptList = departmentRepository.findDepartmentById(deptid);
//		if(deptList.size() > 0){
		if(deptList != null){
			return new Result<>(200, deptList.get(0));
		}
		else{
			return new Result<>(400, "No employee found with empid : " + deptid);
		}
	}
	
	public Result<List<DepartmentWithEmployees>> findAllDepartmentsWithEmployees(){
		List<DepartmentWithEmployees> deptList  = departmentRepository.findAllDepartmentsWithEmployees();
		if(deptList.size() > 0){
			return new Result<>(200, deptList);
		}
		else{
			return new Result<>(400, "No departments found.");
		}
	}
	
	public Result<Department> addDepartment(Department d) {
		int deptid = departmentRepository.addDepartment(d);
		if(deptid > 0){
			d.setDeptid(deptid);
			// Code : 201 for Insert (POST)
			return new Result<>(201, d);
		}else {
			return new Result<>(400, "Error in adding department.");
		}
	}

}
