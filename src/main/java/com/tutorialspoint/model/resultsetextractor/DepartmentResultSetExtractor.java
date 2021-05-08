package com.tutorialspoint.model.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tutorialspoint.model.Department;
import com.tutorialspoint.model.DepartmentWithEmployees;
import com.tutorialspoint.model.Employee;

public class DepartmentResultSetExtractor implements ResultSetExtractor<List<DepartmentWithEmployees>> {

	@Override
	public List<DepartmentWithEmployees> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<DepartmentWithEmployees> deptList = new ArrayList<DepartmentWithEmployees>();
		Map<Integer, DepartmentWithEmployees> deptMap = new HashMap<Integer, DepartmentWithEmployees>();
		while(rs.next()) {
			Integer deptid = rs.getInt("deptid");
			DepartmentWithEmployees department = deptMap.get(deptid);
			if(department == null) {
				// Create object
				department = new DepartmentWithEmployees();
				// Set object properties
				department.setDeptid(rs.getInt("deptid"));
				department.setName(rs.getString("dname"));
				department.setEmployeeList(new ArrayList<Employee>());
				// Add object to Map and List
				deptMap.put(deptid, department);
				deptList.add(department);
			}
			
			Employee employee = new Employee();
			employee.setEmpid(rs.getInt("empid"));
			employee.setName(rs.getString("ename"));
			employee.setSalary(rs.getDouble("salary"));
			department.getEmployeeList().add(employee);
		}
		return deptList;
	}
	
//	@Override
//	public List<DepartmentWithEmployees> extractData(ResultSet rs) throws SQLException, DataAccessException {
//		List<DepartmentWithEmployees> deptList = new ArrayList<DepartmentWithEmployees>();
//		Map<Integer, DepartmentWithEmployees> deptMap = new HashMap<Integer, DepartmentWithEmployees>();
////		Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();
//		while(rs.next()) {
//			Integer deptid = rs.getInt("deptid");
//			DepartmentWithEmployees department = deptMap.get(deptid);
//			if(department == null) {
//				// Create object
//				department = new DepartmentWithEmployees();
//				// Set object properties
//				department.setDeptid(rs.getInt("deptid"));
//				department.setName(rs.getString("dname"));
//				department.setEmployeeList(new ArrayList<Employee>());
//				// Add object to Map and List
//				deptMap.put(deptid, department);
//				deptList.add(department);
//			}
////			Integer empid = rs.getInt("empid");
////			Employee employee = empMap.get(empid);
////			if(employee == null) {
////				// Create Object
////				employee = new Employee();
////				// Set object properties
////				employee.setEmpid(rs.getInt("empid"));
////				employee.setName(rs.getString("ename"));
////				employee.setSalary(rs.getDouble("salary"));
////				// Add to list
////				if(department.getEmployeeList() == null) {
////					department.setEmployeeList(new ArrayList<Employee>());
////				}
////				department.getEmployeeList().add(employee);
////			}
//			
//			Employee employee = new Employee();
//			employee.setEmpid(rs.getInt("empid"));
//			employee.setName(rs.getString("ename"));
//			employee.setSalary(rs.getDouble("salary"));
//			department.getEmployeeList().add(employee);
//			
////			department.setEmployeeList(emp);
//		}
//		return deptList;
//	}

}
