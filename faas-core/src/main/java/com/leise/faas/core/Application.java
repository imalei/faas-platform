package com.leise.faas.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leise.faas.core.context.Context;
import com.leise.faas.core.engine.flow.FlowEngine;

@SpringBootApplication
public class Application {

	@Autowired
	FlowEngine flowEngine;

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	// CommandLineRunner sampleCommandLineRunner() {
	// singleQueryFunc.setStatement("CityMapper.findByState");
	// Context context = new Context();
	// context.getInParams().put("state", "CA");
	// return args -> System.out.println(singleQueryFunc.execute(context));
	// }

	@Bean
	CommandLineRunner sampleCommandLineRunner() {
		Context context = new Context();
		context.getInParams().put("state", "CA");
		flowEngine.executeFlow("test", context);
		return args -> System.out.println(flowEngine);
	}

}
