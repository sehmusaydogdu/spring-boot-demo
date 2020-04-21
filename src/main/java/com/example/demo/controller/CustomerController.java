package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private Logger logger = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service;

	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAll() {
		try {
			List<Customer> list = service.getAll();
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			logger.error("GetAll metodunda hata oluştu");
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Customer>> get(@PathVariable("id") Long id) {
		try {
			Optional<Customer> customer = service.getID(id);
			if (customer.isPresent()==false) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping("/getName/{name}")
	public ResponseEntity<List<Customer>> get(@PathVariable("name") String name) {
		try {
			return ResponseEntity.ok().body(service.getName(name));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PostMapping("/post")
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		try {
			Customer data = service.save(customer);
			if (data == null) {
				logger.error("Kayıt Eklerken hata oluştu.");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kayıt Eklerken hata oluştu.");
			}
			return ResponseEntity.ok().body(customer);
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aynı kayıdı tekrardan ekleyemezsin");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
		try {
			Customer customer = service.delete(id);
			if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PutMapping("/update/{id}")
	public ApiResponse<Customer> update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		ApiResponse<Customer> response = new ApiResponse<>();
		try {
			response.setOldValue((Customer) service.getID(id).get().clone());
			response.setNewValue(service.update(id, customer));
			response.setStatus(true);
			response.setMessage("OK");
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}
