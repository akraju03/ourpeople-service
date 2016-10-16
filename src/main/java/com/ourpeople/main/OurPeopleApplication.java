package com.ourpeople.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ourpeople.main.OurPeopleConfiguration;
import com.ourpeople.resources.LoginResource;
import com.ourpeople.resources.SignupResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OurPeopleApplication  extends Application<OurPeopleConfiguration> {
	public static void main(String[] args) throws Exception {
		new OurPeopleApplication().run(args);
	}


	@Override
	public void initialize(Bootstrap<OurPeopleConfiguration> bootstrap) {
		// TODO Auto-generated method stub
		super.initialize(bootstrap);
		
	}

	@Override
	public void run(OurPeopleConfiguration configuration, Environment e) throws Exception {

		Injector injector = Guice.createInjector(new OurpeopleInjector(configuration));
		LoginResource loginResource = injector.getInstance(LoginResource.class);
		e.jersey().register(loginResource);
		SignupResource signupResource = injector.getInstance(SignupResource.class);
		e.jersey().register(signupResource);
		
	}
}
