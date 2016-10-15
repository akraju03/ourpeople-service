package com.ourpeople.main;

import com.yammer.dropwizard.Service;


import com.yammer.dropwizard.config.Environment;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ourpeople.health.TemplateHealthCheck;
import com.ourpeople.main.OurPeopleConfiguration;
import com.ourpeople.resources.LoginResource;
import com.ourpeople.resources.SignupResource;

public class OurPeopleService extends Service<OurPeopleConfiguration> {
	public static void main(String[] args) throws Exception {
		new OurPeopleService().run(args);
	}

	private OurPeopleService() {
		super("hello-world");
	}

	@Override
	protected void initialize(OurPeopleConfiguration configuration,
				  Environment environment) {
		final String template = configuration.getTemplate();
		final String defaultName = configuration.getDefaultName();
		environment.addResource(new LoginResource());
		environment.addResource(new SignupResource());
		environment.addHealthCheck(new TemplateHealthCheck(template));
	}
}
