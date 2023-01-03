package hw1;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppServiceImpl implements AppService { 
	@Autowired private EmployeeDao employeeDao; 

	@Override
	public boolean add(String id, String firstName, String lastName, String salaryStr, String address) { 
		double salary = Double.parseDouble(salaryStr);
		 
		Employee emp = new Employee();
		emp.setId(id);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setSalary(salary);
		emp.setAddress(address);
		
		return this.employeeDao.add(emp);
	}

	@Override
	public Employee get(String id) { 
		return this.employeeDao.get(id);
	}

	@Override
	public boolean update(Employee employee, String newFirstName, String newLastName, String newSalaryStr, String newAddress) {
		
		if(newFirstName != null) { 
			employee.setFirstName(newFirstName);
		}
		
		if(newLastName != null) { 
			employee.setLastName(newLastName);
		}
		
		if(newSalaryStr != null) { 
			employee.setSalary(Double.parseDouble(newSalaryStr));
		}
		
		if(newAddress != null) { 
			employee.setAddress(newAddress);
		}
		
		return this.employeeDao.update(employee);
	}

	@Override
	public boolean delete(String id) {   
		return this.employeeDao.delete(id);
	}
	
	@Override
	public boolean idExists(String id) {
		return this.employeeDao.idExists(id);
	}
	 
	@Override
	public int getListTotalPages(int pageSize) {
		double totalSize = this.employeeDao.getListSize();
		int totalPages = (int)Math.ceil(totalSize/pageSize);
		return totalPages;
	}

	@Override
	public List<Employee> getList(int pageSize, int page) {
		return this.employeeDao.getList(pageSize, page);
	}
	
	@Override
	public boolean validateId(String id) {
		if(id == null || (id=id.trim()).length() < 8 || id.length() > 20) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validateFirstName(String firstName) {
		if(firstName == null || (firstName=firstName.trim()).length() == 0 || firstName.length() > 20) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validateLastName(String lastName) {
		if(lastName == null || (lastName=lastName.trim()).length() == 0 || lastName.length() > 20) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validateSalary(String salaryStr) {
		try {
			double salary = Double.parseDouble(salaryStr);
			if(salary <= 0) {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validateAddress(String address) {
		if(address == null || (address=address.trim()).length() == 0 || address.length() > 50) {
			return false;
		}
		
		return true;
	} 
}
