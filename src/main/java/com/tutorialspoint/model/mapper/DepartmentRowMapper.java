package com.tutorialspoint.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tutorialspoint.model.Department;

public class DepartmentRowMapper implements RowMapper<Department> {

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department department = new Department();
		department.setDeptid(rs.getInt("deptid"));
		department.setName(rs.getString("name"));
		return department;
	}
	
}
