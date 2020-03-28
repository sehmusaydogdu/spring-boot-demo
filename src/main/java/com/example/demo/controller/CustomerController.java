package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping("/getAll")
	public ApiResponse<List<Customer>> getAll() {
		ApiResponse<List<Customer>> response = new ApiResponse<List<Customer>>();
		try {
			response.setNewValue(service.getAll());
			response.setStatus(true);
			response.setMessage("OK");
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@GetMapping("/get/{id}")
	public Customer get(@PathVariable("id") Long id) {
		try {
			return service.getID(id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping("/getName/{name}")
	public List<Customer> get(@PathVariable("name") String name) {
		try {
			return service.getName(name);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PostMapping("/post")
	public Customer save(@RequestBody Customer customer) {
		try {
			return service.save(customer);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@DeleteMapping("/delete/{id}")
	public Customer delete(@PathVariable("id") Long id) {
		try {
			return service.delete(id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PutMapping("/update/{id}")
	public ApiResponse<Customer> update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		ApiResponse<Customer> response = new ApiResponse<>();
		try {
			response.setOldValue((Customer) service.getID(id).clone());
			response.setNewValue(service.update(id, customer));
			response.setStatus(true);
			response.setMessage("OK");
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return response;
		}
	}
}
