package com.tutorialspoint.model.mapper;

import com.tutorialspoint.model.Employee;
import com.tutorialspoint.model.EmployeeWithDept;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeWithDeptRowMapper implements RowMapper<EmployeeWithDept> {

    @Override
    public EmployeeWithDept mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeWithDept e = new EmployeeWithDept();
        e.setEmpid(rs.getInt("empid"));
        e.setName(rs.getString("ename"));
        e.setSalary(rs.getDouble("salary"));
        e.setDeptid(rs.getInt("deptid"));
        e.setDeptName(rs.getString("dname"));  // 'dname' is the alias in the Select query
        return e;
    }
}
