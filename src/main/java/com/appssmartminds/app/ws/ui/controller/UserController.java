package com.appssmartminds.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.appssmartminds.app.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.appssmartminds.app.ws.ui.model.request.UsersDetailsRequestModel;
import com.appssmartminds.app.ws.ui.model.response.UserRest;
import com.appssmartminds.app.ws.userservice.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping()
	public String getUsers(@RequestParam(value="page",defaultValue="1",required=false) int page,@RequestParam(value="limit",defaultValue="15") int limit) {
		
		return "get user was called with page = "+ page + " limit = " + limit;
	}
	
	// bind to 
	@GetMapping(path="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		
	}

	@PostMapping(
			consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UsersDetailsRequestModel userDetails) {
		
		UserRest returnValue = userService.createUser(userDetails);
	    
		if(users == null) users = new HashMap<>();
		users.put(returnValue.getUserId(), returnValue);
		
		
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}",
			consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetailRequestModel userDetails) {
		
	
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstname(userDetails.getFirstname());
		storedUserDetails.setLastname(userDetails.getLastname());
		users.put(userId, storedUserDetails);
		return storedUserDetails;
	
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}
