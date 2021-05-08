package com.tutorialspoint.model.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tutorialspoint.model.Employee;

public class EmployeeResultSetExtractor implements ResultSetExtractor<List<Employee>> {

	@Override
	public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Employee> empList = new ArrayList<>();
		while(rs.next()) {
			Employee e = new Employee();
			e.setEmpid(rs.getInt("empid"));
			e.setName(rs.getString("name"));
			e.setSalary(rs.getDouble("salary"));
			e.setDeptid(rs.getInt("deptid"));
			empList.add(e);
		}
		return empList;
	}

}
