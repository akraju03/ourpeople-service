package com.ourpeople.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ourpeople.models.UserDetails;
import com.ourpeople.services.LoginService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

	@Inject
	LoginService loginService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Sku by ID", notes = "Gets Sku for the given ID.", response = UserDetails.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of Sku", response = UserDetails.class),
			@ApiResponse(code = 404, message = "Sku doesn't exist"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public UserDetails sayHello(@QueryParam("email") String email, @QueryParam("password") String password) {
		return loginService.getUserDetails(email, password);
	}
}
