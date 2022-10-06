package com.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
* com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.parser.Model.Xml` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
 at [Source: (org.apache.http.nio.entity.ContentInputStream); line: 1, column: 287]*/
@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
		System.out.println("application started");
	}

}
