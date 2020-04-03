package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);

	Customer findByNameAndSurname(String name, String surname);

	@Transactional
	@Query(value = "select * from dual where name= :val", nativeQuery = true)
	void get(@Param("val") String val);
}
