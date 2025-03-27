package tiendaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(InventarioApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tienda");
		dataSource.SetUsername("giovanny");
		dataSource.SetPassword("tapiero");
		return dataSource;
	}
}
