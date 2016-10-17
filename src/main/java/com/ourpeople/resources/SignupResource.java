package com.ourpeople.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ourpeople.models.SignupDetails;
import com.ourpeople.models.UserDetails;
import com.ourpeople.services.SignupService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/signup")
@Produces(MediaType.APPLICATION_JSON)
public class SignupResource {
	@Inject
	SignupService signupService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "SignUp Data", notes = "Get details about signup data", response = UserDetails.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Signup sucessfull", response = UserDetails.class),
			@ApiResponse(code = 404, message = "Signup failure"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public UserDetails sayHello(SignupDetails userDao) {
		return signupService.postCreateUser(userDao);
	}
}
