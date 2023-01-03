package hw1;

import java.util.List;

public interface AppService {
	public boolean add(String id, String firstName, String lastName, String salaryStr, String address);
	public Employee get(String id);
	public int getListTotalPages(int pageSize);
	public List<Employee> getList(int pageSize, int page);
	public boolean update(Employee employee, String newFirstName, String newLastName, String newSalaryStr, String newAddress);
	public boolean delete(String id);
	
	public boolean validateId(String input);
	public boolean validateFirstName(String input);
	public boolean validateLastName(String input);
	public boolean validateSalary(String input);
	public boolean validateAddress(String input);
	
	public boolean idExists(String id); 
}
