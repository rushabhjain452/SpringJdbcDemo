package com.tutorialspoint.repository.departmentrepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tutorialspoint.model.Department;
import com.tutorialspoint.model.DepartmentWithEmployees;
import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.model.mapper.DepartmentRowMapper;
import com.tutorialspoint.model.mapper.EmployeeWithDeptRowMapper;
import com.tutorialspoint.model.resultsetextractor.DepartmentResultSetExtractor;

@Repository(value = "departmentRepo")
public class DepartmentRepositoryImpl implements DepartmentRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Department> findAllDepartments() {
		String sql = "SELECT * FROM dept";
		return jdbcTemplate.query(sql, new DepartmentRowMapper());
	}

	@Override
	public List<Department> findDepartmentById(int deptid) {
		String sql = "SELECT * FROM dept WHERE deptid=:deptid";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("deptid", deptid);
		return jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Department>(Department.class));
	}

	@Override
	public List<DepartmentWithEmployees> findAllDepartmentsWithEmployees() {
		String sql = "SELECT e.deptid as deptid, d.name as dname, empid, e.name as ename, salary "
				+ "FROM employee as e inner join dept as d on e.deptid=d.deptid";
		return jdbcTemplate.query(sql, new DepartmentResultSetExtractor());
	}

	@Override
	public List<DepartmentWithEmployees> findDepartmentNyIdWithEmployees(int deptid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addDepartment(Department d) {
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "INSERT INTO DEPT (name) VALUES (:name)";
		int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(d), holder);
		if(n > 0){
			return holder.getKey().intValue();
		}
		else{
			return 0;
		}
	}

	@Override
	public int updateDepartment(int deptid, Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartment(int deptid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
