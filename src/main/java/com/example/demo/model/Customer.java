package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Cloneable {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=[").append(this.getId()).append("] ");
		builder.append("name=[").append(this.getName()).append("] ");
		builder.append("surname=[").append(this.getSurname()).append("] ");
		builder.append("age=[").append(this.getAge()).append("] ");
		return builder.toString();
	}
}
