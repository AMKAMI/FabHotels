package com.fabhotels.response;

import com.fabhotels.entity.User;

public class UserResponse {

	private String name;
	private String address;
	private String email;
	private int age;
	private String status;
	
	public UserResponse(User user) {
		this.name=user.getName();
		this.address= user.getAddress();
		this.age = user.getAge();
		this.email=user.getEmail();
		this.status="Success";
	}

	public UserResponse() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
