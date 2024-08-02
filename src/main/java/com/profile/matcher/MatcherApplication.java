package com.profile.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatcherApplication {

	public static void main(String[] args) {
		Runtime.Version version = Runtime.version();
		System.out.println("Java version: "+version);
//		SpringApplication.run(MatcherApplication.class, args);
	}

}
