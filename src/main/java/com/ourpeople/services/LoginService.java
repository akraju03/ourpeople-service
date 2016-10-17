package com.ourpeople.services;

import com.google.inject.Inject;
import com.ourpeople.dao.UserDao;
import com.ourpeople.models.UserDetails;

public class LoginService {
	
	//@Inject
	//UserDetails userDetails;
	
	@Inject
	UserDao userDao;
	
	public UserDetails getUserDetails(String username, String password){
		UserDetails userDetails = new UserDetails();
		userDetails =  userDao.getUserDetails(username, password);
		
		if(userDetails.getId() != null){
			userDetails.setStatus("SUCCESS");
			userDetails.setText("User Found");
		}else{
			userDetails.setStatus("FAILURE");
			userDetails.setText("User Not Found");
		}
		
		return userDetails;
	}
	
}
