package com.tutorialspoint.repository.departmentrepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tutorialspoint.model.Department;
import com.tutorialspoint.model.DepartmentWithEmployees;

@Repository
public interface DepartmentRepository {
	List<Department> findAllDepartments();
	
	List<Department> findDepartmentById(int deptid);
	
	List<DepartmentWithEmployees> findAllDepartmentsWithEmployees();
	
	List<DepartmentWithEmployees> findDepartmentNyIdWithEmployees(int deptid);
	
	int addDepartment(Department d);
	
	int updateDepartment(int deptid, Department d);
	
	int deleteDepartment(int deptid);
}
