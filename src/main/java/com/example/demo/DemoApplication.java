package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public GreetResponse hello(){
		 GreetResponse response = new GreetResponse("Hello world"
		,List.of("Java", "Golang", "JS"),
				new Person("Alex", 28, 3000));

		 return  response;
	}

	record Person(String name, int age, double savings){
	}

	record GreetResponse(String greet,
						 List<String> favProgLanguages,
						 Person person
						 ){
	}
	
}
