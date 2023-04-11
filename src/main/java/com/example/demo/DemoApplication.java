package com.example.demo;

import database.DbConfig;
import database.Farmer;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		Farmer farmer2 = new Farmer(1, "name", "sadda", 22f);

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public GreetResponse hello() {
		GreetResponse response = new GreetResponse("Hello world", List.of("Java", "Golang", "JS"),
				new Person("Alex", 28, 3000));

		return response;
	}

	@GetMapping("/farmers")
	public ResponseEntity<Object> getFarmers() {
		List<Farmer> farmers = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();

		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OtkupKorisnik");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String name = rs.getString("Ime");
				String address = rs.getString("Adresa");
				Float debt = 22f;// rs.getDouble("Dug");
				Farmer farmer = new Farmer(id, name, address, debt);
				farmers.add(farmer);

				map.put("Id", id.toString());
				map.put("Ime", name);
				map.put("Adresa", address);
				map.put("debt", debt.toString());

			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		Farmers farm = new Farmers(map);
		return new ResponseEntity<Object>(farm, HttpStatus.OK);
	}

	record Person(String name, int age, double savings) {
	}

	record GreetResponse(String greet,
			List<String> favProgLanguages,
			Person person) {
	}

	record Farmers(HashMap<String, String> farm) {
	}

}
