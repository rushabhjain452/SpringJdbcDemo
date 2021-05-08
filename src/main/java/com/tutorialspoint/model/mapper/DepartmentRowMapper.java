package com.tutorialspoint.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tutorialspoint.model.Department;

public class DepartmentRowMapper implements RowMapper<Department> {

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
//		System.out.println("rowNum : " + rowNum);
//		if(rowNum == 0) {
////			rs.absolute(rowNum+1);
////			rs.relative(1);
//			System.out.println(rs.next());
//			System.out.println(rs.getString("name"));
//		}
		Department department = new Department();
		department.setDeptid(rs.getInt("deptid"));
		department.setName(rs.getString("name"));
		return department;
	}
	
}
