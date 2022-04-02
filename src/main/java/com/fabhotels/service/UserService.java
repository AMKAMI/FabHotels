package com.fabhotels.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabhotels.config.EncryptPassword;
import com.fabhotels.controllers.UserController;
import com.fabhotels.entity.User;
import com.fabhotels.repository.UserRepository;
import com.fabhotels.request.UpdateProfileRequest;
import com.fabhotels.request.UserRegistrationRequest;
import com.fabhotels.response.UserResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	static Logger log = Logger.getLogger(UserController.class.getName());
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public UserResponse getUser(String email) {
		return new UserResponse(userRepository.findByEmail(email));
	}
	
	public Boolean isUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		return (EncryptPassword.encrptPassword(password).equals(user.getPassword()));
	}
	
	public UserResponse registerUser(UserRegistrationRequest userRegistration) {
		
		userRegistration.setPassword(EncryptPassword.encrptPassword(userRegistration.getPassword()));
		log.info("Encrpted Password is "+ userRegistration.getPassword());
		User user=new User(userRegistration);
		return new UserResponse(userRepository.save(user));
	}
	
	public UserResponse updateUserProfile(UpdateProfileRequest updateProfileRequest, String email) {
				User user = userRepository.findByEmail(email);
				
				if(updateProfileRequest.getAddress()!=null) user.setAddress(updateProfileRequest.getAddress());
				if(updateProfileRequest.getAge()!=0) user.setAge(updateProfileRequest.getAge());
				if(updateProfileRequest.getName()!=null) user.setName(updateProfileRequest.getName());
				if(updateProfileRequest.getPassword()!=null) user.setPassword(updateProfileRequest.getPassword());
				
				return new UserResponse(userRepository.save(user));
				
	}
	
	
}
