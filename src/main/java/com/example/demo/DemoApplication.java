package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Customer;
import com.example.demo.repository.ICustomerRepository;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	@Autowired
	private ICustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
