package com.tutorialspoint.repository.employeerepo;

import java.util.List;

import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.model.mapper.EmployeeWithDeptRowMapper;
import com.tutorialspoint.model.resultsetextractor.EmployeeResultSetExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tutorialspoint.model.Employee;

@Repository(value = "employeeRepo")
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeWithDept> findAllEmployees() {
		String sql = "SELECT empid, e.name as ename, salary, e.deptid as deptid, d.name as dname FROM employee as e inner join dept as d on e.deptid=d.deptid";
		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new EmployeeWithDeptRowMapper());
		// Using BeanPropertyRowMapper
//		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EmployeeWithDept>(EmployeeWithDept.class));
		// Using ResultSetExtractor
//		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new EmployeeResultSetExtractor());
		return empList;
	}

	@Override
	public EmployeeWithDept findEmployeeById(int empid) {
//		String sql = "SELECT * FROM Employee WHERE empid =" + empid;
		String sql = "SELECT empid, e.name as ename, salary, e.deptid as deptid, d.name as dname " +
				"FROM employee as e inner join dept as d on e.deptid=d.deptid " +
				"WHERE empid = :empid";
		MapSqlParameterSource params = new MapSqlParameterSource("empid", empid);
		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, params, new EmployeeWithDeptRowMapper());
		if(empList.size() > 0){
			return empList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<Employee> findEmployeeByName(String name) {
		String sql = "SELECT * FROM Employee WHERE Name = :name";
		MapSqlParameterSource params = new MapSqlParameterSource("name", name);
		List<Employee> empList = jdbcTemplate.query(sql, params, new EmployeeResultSetExtractor());
		return empList;
	}

	@Override
	public int addEmployee(Employee e) {
		KeyHolder holder = new GeneratedKeyHolder();
		String sql = "INSERT INTO EMPLOYEE (name, salary, deptid) VALUES (:name, :salary, :deptid)";
		int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(e), holder);
		if(n > 0){
			return holder.getKey().intValue();
		}
		else{
			return 0;
		}
	}

	@Override
	public boolean updateEmployee(int empid, Employee e) {
		e.setEmpid(empid);
		String sql = "UPDATE Employee SET name=:name, salary=:salary, deptid=:deptid WHERE empid=:empid";
		// public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder)
		int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(e));
		return n > 0;
	}

	@Override
	public boolean deleteEmployee(int empid) {
		String sql = "DELETE FROM Employee WHERE empid = :empid";
		MapSqlParameterSource params = new MapSqlParameterSource("empid", empid);
		return jdbcTemplate.update(sql, params) > 0;
	}

}
