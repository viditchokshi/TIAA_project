package com.SpringSignUp.Service;

import com.SpringSignUp.Model.User;

public interface UserService {
	  
	 public User findUserByEmail(String email);
	 
	 public void saveUser(User user);
	}
