package hw1;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("hw1")
@PropertySource("classpath:app.properties")
public class BeanConfig {
	
	@Autowired
	Environment environment;
	
	@Bean
	public JdbcTemplate jdbcTemplateBean() { 
		String url = this.environment.getProperty("datasource.url");
		String user = this.environment.getProperty("datasource.user");
		String password = this.environment.getProperty("datasource.password");
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(user);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		
		return new JdbcTemplate(driverManagerDataSource);
	} 
}
