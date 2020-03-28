package com.example.demo.model;

public class ApiResponse<T> {

	private boolean status;
	private String message;
	private T oldValue;
	private T newValue;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getOldValue() {
		return oldValue;
	}

	public void setOldValue(T oldValue) {
		this.oldValue = oldValue;
	}

	public T getNewValue() {
		return newValue;
	}

	public void setNewValue(T newValue) {
		this.newValue = newValue;
	}

}
