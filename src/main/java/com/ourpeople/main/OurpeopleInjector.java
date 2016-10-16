package com.ourpeople.main;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.inject.AbstractModule;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OurpeopleInjector extends AbstractModule {
	OurPeopleConfiguration peopleConfig;

	@Override
	protected void configure(){
		bind(OurPeopleConfiguration.class).toInstance(peopleConfig);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		bind(ObjectMapper.class).toInstance(objectMapper);
		//bind(BookingService.class).to(BookingServiceImpl.class);
	}

	public OurpeopleInjector(OurPeopleConfiguration peopleConfig) {
		this.peopleConfig = peopleConfig;

	}
}
