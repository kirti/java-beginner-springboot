package com.appssmartminds.app.ws.userservice;

import com.appssmartminds.app.ws.ui.model.request.UsersDetailsRequestModel;
import com.appssmartminds.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UsersDetailsRequestModel userDetails);

}
