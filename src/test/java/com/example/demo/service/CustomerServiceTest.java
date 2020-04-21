package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Customer;
import com.example.demo.repository.ICustomerRepository;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerService service;

	@MockBean
	private ICustomerRepository repository;

	@Test
	public void testGetAll() {
		Customer customer1 = getCustomer();
		Customer customer2 = getCustomer();

		List<Customer> list = new ArrayList<>();
		list.add(customer1);
		list.add(customer2);

		Mockito.when(repository.findAll()).thenReturn(list);
		assertThat(service.getAll()).isEqualTo(list);
		assertThat(service.getAll().size()).isEqualTo(2);
	}

	@Test
	public void testGetID() {
		Customer customer = getCustomer();
		Optional<Customer> opt = Optional.of(customer);
		Mockito.when(repository.findById(1L)).thenReturn(opt);
		assertThat(service.getID(1L)).isEqualTo(opt);
	}

	@Test
	public void testSave() {
		Customer customer = getCustomer();
		Mockito.when(repository.save(customer)).thenReturn(customer);
		assertThat(service.save(customer)).isEqualTo(customer);
	}

	@Test
	public void testDelete() {
		Customer customer = getCustomer();
		Optional<Customer> opt = Optional.of(customer);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(opt);
		assertThat(service.delete(Mockito.anyLong())).isEqualTo(customer);
	}
	
	@Test
	public void testUpdate() {
		Customer customer = getCustomer();
		Optional<Customer> opt = Optional.of(customer);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(opt);
		
		customer.setName("Ayşe");
		Mockito.when(repository.save(customer)).thenReturn(customer);
		
		assertThat(service.update(Mockito.anyLong(),customer)).isEqualTo(customer);
	}     

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Ali");
		customer.setSurname("Kaya");
		customer.setAge(20);
		return customer;
	}
}
