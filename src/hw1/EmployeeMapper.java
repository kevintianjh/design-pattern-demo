package hw1;

import java.sql.ResultSet;
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getString("id"));
		emp.setFirstName(rs.getString("first_name"));
		emp.setLastName(rs.getString("last_name"));
		emp.setSalary(rs.getDouble("salary"));
		emp.setAddress(rs.getString("address"));
		return emp;
	} 
}
