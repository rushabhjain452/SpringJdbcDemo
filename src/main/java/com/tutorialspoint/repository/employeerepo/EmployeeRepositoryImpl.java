package com.tutorialspoint.repository.employeerepo;

import java.util.List;

import com.tutorialspoint.model.EmployeeWithDept;
import com.tutorialspoint.model.mapper.EmployeeWithDeptRowMapper;
import com.tutorialspoint.model.resultsetextractor.EmployeeResultSetExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tutorialspoint.model.Employee;
import com.tutorialspoint.model.mapper.EmployeeRowMapper;

@Repository(value = "employeeRepo")
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeWithDept> findAllEmployees() {
//		String sql = "SELECT * FROM Employee";
		String sql = "SELECT empid, e.name as ename, salary, e.deptid as deptid, d.name as dname FROM employee as e inner join dept as d on e.deptid=d.deptid";
		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new EmployeeWithDeptRowMapper());
		// Using BeanPropertyRowMapper
//		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EmployeeWithDept>(EmployeeWithDept.class));
		// Using ResultSetExtractor
//		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new EmployeeResultSetExtractor());
		return empList;
	}

	@Override
	public List<EmployeeWithDept> findEmployeeById(int empid) {
//		String sql = "SELECT * FROM Employee WHERE empid =" + empid;
		String sql = "SELECT empid, e.name as ename, salary, e.deptid as deptid, d.name as dname " +
				"FROM employee as e inner join dept as d on e.deptid=d.deptid " +
				"WHERE empid = " + empid;
		List<EmployeeWithDept> empList = jdbcTemplate.query(sql, new EmployeeWithDeptRowMapper());
		return empList;
	}
	
	@Override
	public List<Employee> findEmployeeByName(String name) {
		String sql = "SELECT * FROM Employee WHERE Name = '" + name + "'";
		List<Employee> empList = jdbcTemplate.query(sql, new EmployeeResultSetExtractor());
		return empList;
	}

	@Override
	public int addEmployee(Employee e) {
		// Using Statement
//		String sql = "INSERT INTO EMPLOYEE (name, salary) VALUES ('" + e.getName() + "', " + e.getSalary() + ")";
//		int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(e));
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
	public int updateEmployee(int empid, Employee e) {
		e.setEmpid(empid);
		String sql = "UPDATE Employee SET name=:name, salary=:salary, deptid=:deptid WHERE empid=:empid";
		// public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder)
		int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(e));
		return n;
	}

	@Override
	public int deleteEmployee(int empid) {
		Employee e = new Employee();
		e.setEmpid(empid);
		String sql = "DELETE FROM Employee WHERE empid=:empid";
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(e));
	}

}
