package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.ICustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private ICustomerRepository repository;

	public List<Customer> getAll() {
		return repository.findAll();
	}

	public Customer getID(Long id) {
		return repository.findById(id).get();
	}

	public List<Customer> getName(String name) {
		return repository.findByName(name);
	}

	public Customer save(Customer customer) {
		return repository.save(customer);
	}

	public Customer delete(Long id) {
		Customer customer = repository.findById(id).get();
		repository.deleteById(id);
		return customer;
	}

	public Customer update(Long id, Customer customer) throws CloneNotSupportedException {
		Customer db = repository.findById(id).get();
		db.setName(customer.getName());
		db.setSurname(customer.getSurname());
		db.setAge(customer.getAge());
		return repository.save(db);
	}
}
