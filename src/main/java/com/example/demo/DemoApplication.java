package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.controller.ApplicationFilter;
import com.example.demo.model.Customer;
import com.example.demo.model.Personel;
import com.example.demo.repository.ICustomerRepository;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	@Autowired
	private ICustomerRepository repository;
	
	@Value(value = "env")
	private String env;
	
	@Autowired
	private Personel personel;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public ApplicationFilter applicationFilter() {
		return new ApplicationFilter(); 
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Customer customer1 = new Customer();
		customer1.setName("Ali");
		customer1.setSurname("Kaya");
		customer1.setAge(20);

		Customer customer2 = new Customer();
		customer2.setName("Ayşe");
		customer2.setSurname("Kartal");
		customer2.setAge(20);

		repository.save(customer1);
		repository.save(customer2);
	}
}
