package com.appssmartminds.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.appssmartminds.app.ws.shared.Utils;
import com.appssmartminds.app.ws.ui.model.request.UsersDetailsRequestModel;
import com.appssmartminds.app.ws.ui.model.response.UserRest;
import com.appssmartminds.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	Map<String, UserRest> users;
	
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UsersDetailsRequestModel userDetails) {
		// TODO Auto-generated method stub
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstname(userDetails.getFirstname());
		returnValue.setLastname(userDetails.getLastname());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
	}

}
