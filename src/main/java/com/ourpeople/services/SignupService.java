package com.ourpeople.services;

import com.ourpeople.dao.SignupDao;
import com.ourpeople.dao.UserDao;
import com.ourpeople.models.SignupDetails;
import com.ourpeople.models.UserDetails;

public class SignupService {

	@SuppressWarnings("null")
	public UserDetails postCreateUser(SignupDetails signupDetail){
		
		UserDetails userDetails =  new SignupDao().createuser(signupDetail);
		
		if(userDetails.getId() != null){
			userDetails.setStatus("SUCCESS");
			userDetails.setText("Sign Up Succesfull");
		}else{
			userDetails.setStatus("FAILURE");
			userDetails.setText("Something went wrong");
		}
		
		//userDetails.setCommunity_name("Sri Somavamsha Arya Kshatriya");
		
		return userDetails;
	}
}
