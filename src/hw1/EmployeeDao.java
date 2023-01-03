package hw1;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class EmployeeDao {
	@Autowired private JdbcTemplate jdbcTemplate;
	
	public boolean add(Employee employee) {
		int row = this.jdbcTemplate.update("INSERT INTO employee VALUES(?, ?, ?, ?, ?)", 
				new Object[] {employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getAddress()});
		return row==1;
	}
	
	public boolean update(Employee employee) {
		int row = this.jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, salary=?, address=? WHERE id=?",
				new Object[] {employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getAddress(), employee.getId()});
		return row==1; 
	}
	 
	public Employee get(String id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?", 
												new EmployeeMapper(),
												id); 
	}
	
	public List<Employee> getList(int pageSize, int page) {
		int skip = (page-1)*pageSize;
		return this.jdbcTemplate.query("SELECT * FROM employee LIMIT ?,?", new EmployeeMapper(), skip, pageSize);
	}
	
	public boolean delete(String id) {
		int row = this.jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
		return row==1;
	} 
	
	public boolean idExists(String id) {
		int count = this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee WHERE id=?", Integer.class, id);
		return count==1;
	} 
	
	public int getListSize() {
		return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employee", Integer.class);
	}
}
