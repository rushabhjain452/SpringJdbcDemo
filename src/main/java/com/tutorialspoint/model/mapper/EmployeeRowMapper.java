package com.tutorialspoint.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tutorialspoint.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee e = new Employee();
		e.setEmpid(rs.getInt("empid"));
		e.setName(rs.getString("ename"));
		e.setSalary(rs.getDouble("salary"));
		e.setDeptid(rs.getInt("deptid"));
		return e;
	}

}
