package hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component; 

@Component
public class LaunchApp { 
	
	@Autowired private AppService appService;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void run() throws IOException {    
		System.out.println("Welcome!"); 
		System.out.println(); 
		boolean continueRun = true;
		
		while(continueRun) {
			System.out.println("1 -> Add employee record");
			System.out.println("2 -> Search employee record");
			System.out.println("3 -> List all employee records");
			System.out.println("4 -> Update employee record");
			System.out.println("5 -> Delete employee record");
			System.out.println("6 -> Exit app");
			System.out.print("Enter choice (1-6): "); 
			
			String option = br.readLine();
			String id, firstName, lastName, salaryStr, address;
			boolean sts;
			
			switch(option) {
				case "1":
					System.out.println();
					
					System.out.println("Adding a new employee record");
					id = inputId(true);
					firstName = inputFirstName(null);
					lastName = inputLastName(null);
					salaryStr = inputSalary(null);
					address = inputAddress(null);
					
					sts = this.appService.add(id, firstName, lastName, salaryStr, address);
					
					if(sts) {
						System.out.println("Successfully added new employee record!");
					}
					else {
						System.out.println("Failed to add new employee record, please try again");
					}
					
					System.out.println(); 
					break;
				case "2":
					System.out.println();
					
					System.out.println("Searching employee record");
					id = inputId(false); 
					
					if(this.appService.idExists(id)) {
						Employee emp = this.appService.get(id);
						System.out.println("ID: " + emp.getId());
						System.out.println("First name: " + emp.getFirstName());
						System.out.println("Last name: " + emp.getLastName());
						System.out.println("Salary: " + emp.getSalary());
						System.out.println("Address: " + emp.getAddress());
					}
					else {
						System.out.println("Cannot find employee record of ID '" + id + "'");
					}
					
					System.out.println(); 
					break; 
				case "3": 
					int pageSize = 5;
					int totalPages = this.appService.getListTotalPages(pageSize);
					int currentPage = 1;
					
					if(totalPages == 0) {
						System.out.println("No records found");
						break;
					}
					
					while(true) {
						System.out.println();
						List<Employee> list = this.appService.getList(pageSize, currentPage);
						System.out.println(generateListOutput(list, currentPage, totalPages));
						
						System.out.print("Enter page (or 'q' to go back): "); 
						int selectedPage;
						
						try {
							String input = br.readLine();
							
							if(input.equals("q")) {
								break;
							}
							
							selectedPage = Integer.parseInt(input);
							if(selectedPage < 1 || selectedPage > totalPages) {
								System.out.println("Please enter a valid input");
								continue;
							}
						}
						catch(Exception e) {
							System.out.println("Please enter a valid input");
							continue;
						}
						
						currentPage = selectedPage;
					}
					
					System.out.println(); 
					break;
				case "4":
					System.out.println();
					
					System.out.println("Updating employee record");
					id = inputId(false); 
					if(this.appService.idExists(id)) {
						Employee emp = this.appService.get(id);
						System.out.println("ID: " + emp.getId());
						System.out.println("First name: " + emp.getFirstName());
						System.out.println("Last name: " + emp.getLastName());
						System.out.println("Salary: " + emp.getSalary());
						System.out.println("Address: " + emp.getAddress());
						
						firstName = inputFirstName(emp.getFirstName());
						lastName = inputLastName(emp.getLastName());
						salaryStr = inputSalary(String.valueOf(emp.getSalary()));
						address = inputAddress(emp.getAddress());
						
						sts = this.appService.update(emp, firstName, lastName, salaryStr, address);
						
						if(sts) {
							System.out.println("Successfully updated employee record!");
						}
						else {
							System.out.println("Failed to update employee record, please try again");
						}
					}
					else {
						System.out.println("Cannot find employee record of ID '" + id + "'");
					}
					
					System.out.println(); 
					break;
					
				case "5":	
					System.out.println();
					
					System.out.println("Deleting employee record");
					id = inputId(false); 
					
					if(this.appService.idExists(id)) {
						sts = this.appService.delete(id);
						
						if(sts) {
							System.out.println("Successfully deleted employee record");
						}
						else {
							System.out.println("Failed to delete employee record, please try again");
						}
					}
					else {
						System.out.println("Cannot find employee record of ID '" + id + "'");
					} 
					
					System.out.println();
					break;
				case "6":
					System.out.println();
					System.out.println("Bye bye!");
					continueRun = false;
					System.out.println();
					break;
				default:
					System.out.println();
					System.out.println("Please select a valid choice"); 
					System.out.println();
			}  
		}
	}
	
	public String generateListOutput(List<Employee> list, int page, int totalPages) {
		StringBuilder ret = new StringBuilder();
		
		String seperatorStr = String.format("%100s", "");
		seperatorStr = seperatorStr.replace(' ', '-');
		ret.append(seperatorStr);
		
		ret.append("\n");
		
		String tmpStr = String.format("%-20s", "Username");
		ret.append(tmpStr);
		tmpStr = String.format("%-20s", "First Name");
		ret.append(tmpStr);
		tmpStr = String.format("%-20s", "Last Name");
		ret.append(tmpStr);
		tmpStr = String.format("%-20s", "Salary");
		ret.append(tmpStr);
		tmpStr = String.format("%-20s", "Address");
		ret.append(tmpStr);
		
		ret.append("\n");
		
		ret.append(seperatorStr);
		
		ret.append("\n");
		
		for(Employee emp:list) {
			tmpStr = String.format("%-20s", emp.getId());
			ret.append(tmpStr);
			tmpStr = String.format("%-20s", emp.getFirstName());
			ret.append(tmpStr);
			tmpStr = String.format("%-20s", emp.getLastName());
			ret.append(tmpStr);
			tmpStr = String.format("%-20s", emp.getSalary());
			ret.append(tmpStr);
			tmpStr = String.format("%-20s", emp.getAddress());
			ret.append(tmpStr);
			
			ret.append("\n");
		}
		
		ret.append(seperatorStr);
		ret.append("\n");
		ret.append("Page " + page + " of " + totalPages);
		ret.append("\n");
		ret.append(seperatorStr); 
		ret.append("\n");
		
		return ret.toString();
	}
	
	public String inputSalary(String currentValue) throws IOException {
		while(true) {
			System.out.print(currentValue == null ? "Enter salary: " : "Enter salary (current is '" + currentValue + "', enter to skip): ");
			String input = this.br.readLine();
			
			if(currentValue != null && input.equals("")) {
				return null;
			}
			
			if(!this.appService.validateSalary(input)) {
				System.out.println("Salary must be a positive number");
				continue;
			}
			
			return input.trim();
		}
	}
	
	public String inputAddress(String currentValue) throws IOException {
		while(true) {
			System.out.print(currentValue == null ? "Enter address: " : "Enter address (current is '" + currentValue + "', enter to skip): ");
			String input = this.br.readLine();
			
			if(currentValue != null && input.equals("")) {
				return null;
			}
			
			if(!this.appService.validateAddress(input)) {
				System.out.println("Address must be between 1 to 50 chars");
				continue;
			}
			
			return input.trim();
		}
	}
	
	public String inputLastName(String currentValue) throws IOException {
		while(true) {
			System.out.print(currentValue == null ? "Enter last name: " : "Enter last name (current is '" + currentValue + "', enter to skip): ");
			String input = this.br.readLine();
			
			if(currentValue != null && input.equals("")) {
				return null;
			}
			
			if(!this.appService.validateLastName(input)) {
				System.out.println("Last name must be between 1 to 20 chars");
				continue;
			}
			
			return input.trim();
		}
	}
	
	public String inputFirstName(String currentValue) throws IOException {
		while(true) {
			System.out.print(currentValue == null ? "Enter first name: " : "Enter first name (current is '" + currentValue + "', enter to skip): ");
			String input = this.br.readLine();
			
			if(currentValue != null && input.equals("")) {
				return null;
			}
			
			if(!this.appService.validateFirstName(input)) {
				System.out.println("First name must be between 1 to 20 chars");
				continue;
			}
			
			return input.trim();
		}
	}
	
	public String inputId(boolean addOperation) throws IOException {
		while(true) {
			System.out.print("Enter employee ID: ");
			String input = this.br.readLine();
			
			if(!this.appService.validateId(input)) {
				System.out.println("ID must be between 8 - 20 chars");
				continue;
			}
			else if(addOperation && this.appService.idExists(input)) {
				System.out.println("ID already exists, please choose another");
				continue;
			}
			
			return input.trim().toLowerCase();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);
		LaunchApp launchApp = ac.getBean(LaunchApp.class);
		launchApp.run();
	}
}
