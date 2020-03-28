package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findByName(String name);
}
