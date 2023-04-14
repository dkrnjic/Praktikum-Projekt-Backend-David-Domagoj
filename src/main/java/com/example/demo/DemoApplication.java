package com.example.demo;

import database.DbConfig;
import database.Farmer;
import database.Klijent;
import database.KlijentDTO;
import database.Otkup;
import database.OtkupDTO;
import database.PoljoprivrednikDTO;
import database.Prodaja;
import database.ProdajaDTO;
import database.Zitarica;
import database.ZitaricaDTO;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoApplication {
	int i = 0;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/farmers")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> getFarmers() {
		List<Farmer> farmers = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OtkupKorisnik");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String ime = rs.getString("Ime");
				String prezime = rs.getString("Prezime");
				String address = rs.getString("Adresa");
				Float debt = 22f;// rs.getDouble("Dug");
				Farmer farmer = new Farmer(id, ime, prezime, address, debt);
				farmers.add(farmer);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(farmers);
		System.out.println("GET farmer:  " + i++);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@GetMapping("/klijenti")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> getKljient() {
		List<Klijent> klijenti = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Klijenti");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String ime = rs.getString("Ime");
				String prezime = rs.getString("Prezime");
				String address = rs.getString("Adresa");
				Float bought = 22f;// rs.getDouble("Dug");
				Klijent klijent = new Klijent(id, ime, prezime, address, bought);
				klijenti.add(klijent);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(klijenti);
		System.out.println("GET farmer:  " + i++);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@GetMapping("/otkup")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> getOtkup() {
		List<Otkup> otkupi = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("EXEC GetOtkupTable");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String ime = rs.getString("OtkupKorisnikIme");
				ime += rs.getString("OtkupKorisnikPrezime");
				String tip = rs.getString("ZitariceIme");
				int kolicina = rs.getInt("Kolicina");
				String vrijeme = rs.getString("Time");
				float primjesa = rs.getFloat("Primjesa");
				float vlaga = rs.getFloat("Vlaga");
				float cijena = rs.getFloat("Cijena");
				Otkup otkup = new Otkup(id, ime, tip, kolicina, vrijeme, primjesa, vlaga, cijena);
				otkupi.add(otkup);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(otkupi);
		System.out.println("GET farmer:  " + i++);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@GetMapping("/zitarice")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> getZitarice() {
		List<Zitarica> zitarice = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Zitarice");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String ime = rs.getString("Ime");
				String tip = rs.getString("Type");
				float kupovna = rs.getFloat("KupovnaCijena");
				float prodajna = rs.getFloat("ProdajnaCijena");
				Zitarica zitarica = new Zitarica(id, ime, tip, kupovna, prodajna);
				zitarice.add(zitarica);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(zitarice);
		System.out.println("GET farmer:  " + i++);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@GetMapping("/prodaja")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> getProdaja() {
		List<Prodaja> prodaje = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				PreparedStatement stmt = conn.prepareStatement("EXEC ShowProdaje");
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Integer id = rs.getInt("Id");
				String ime = rs.getString("KlijentIme");
				ime += rs.getString("KlijentPrezime");
				String tip = rs.getString("ZitaricaIme");
				int kolicina = rs.getInt("Kolicina");
				String vrijeme = rs.getString("Time");
				float cijena = rs.getFloat("Cijena");
				Prodaja prodaja = new Prodaja(id, ime, tip, kolicina, vrijeme, cijena);
				prodaje.add(prodaja);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(prodaje);
		System.out.println("GET farmer:  " + i++);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@PostMapping("/dodajPoljoprivrednika")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> dodajPoljoprivrednika(@RequestBody PoljoprivrednikDTO poljoprivrednik) {
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				CallableStatement stmt = conn.prepareCall("{call DodajOtkupKorisnika(?, ?, ?)}")) {

			// ResultSet rs = stmt.executeQuery())
			// Set the parameters of the SQL statement to the received data

			stmt.setString(1, poljoprivrednik.getIme());
			stmt.setString(2, poljoprivrednik.getPrezime());
			stmt.setString(3, poljoprivrednik.getAdresa());

			stmt.executeUpdate();

			// Return a success response
			return new ResponseEntity<>("Poljoprivrednik successfully added to database", HttpStatus.OK);

		} catch (SQLException e) {

			// Return an error response if an exception occurs
			return new ResponseEntity<>("Error adding poljoprivrednik to database: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/dodajKlijenta")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> dodajKlijenta(@RequestBody KlijentDTO klijent) {
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				CallableStatement stmt = conn.prepareCall("{call DodajKlijenta(?, ?, ?)}")) {

			stmt.setString(1, klijent.getIme());
			stmt.setString(2, klijent.getPrezime());
			stmt.setString(3, klijent.getAdresa());

			stmt.executeUpdate();
			return new ResponseEntity<>("Klijent successfully added to database", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("Error adding Klijent to database: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/dodajZitaricu")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> dodajZitaricu(@RequestBody ZitaricaDTO zitarica) {
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				CallableStatement stmt = conn.prepareCall("{call DodajZitaricu(?, ?, ?, ?)}")) {

			stmt.setString(1, zitarica.getIme());
			stmt.setString(2, zitarica.getTip());
			stmt.setFloat(3, zitarica.getKupovna());
			stmt.setFloat(4, zitarica.getProdajna());

			stmt.executeUpdate();
			return new ResponseEntity<>("Zitarica uspjesno dodana u database", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("Error dodavanja Zitarice u database: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/dodajProdaju")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> dodajProdaju(@RequestBody ProdajaDTO prodaja) {
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				CallableStatement stmt = conn.prepareCall("{call DodajProdaju(?, ?, ?, ?, ?)}")) {

			Timestamp time = Timestamp.from(Instant.now());
			stmt.setInt(1, prodaja.getKlijentId());
			stmt.setInt(2, prodaja.getZitaricaId());
			stmt.setInt(3, prodaja.getKolicina());
			stmt.setTimestamp(4, new Timestamp(time.getTime()));
			stmt.setFloat(5, prodaja.getCijena());

			stmt.executeUpdate();
			return new ResponseEntity<>("Prodaja uspjesno dodana u database", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("Error dodavanja Prodaje u database: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/dodajOtkup")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<String> dodajOtkup(@RequestBody OtkupDTO otkup) {
		try (Connection conn = DriverManager.getConnection(DbConfig.url);
				CallableStatement stmt = conn.prepareCall("{call DodajOtkup(?, ?, ?, ?, ?,?,?)}")) {

			Timestamp time = Timestamp.from(Instant.now());
			stmt.setInt(1, otkup.getKlijentId());
			stmt.setInt(2, otkup.getZitaricaId());
			stmt.setInt(3, otkup.getKolicina());
			stmt.setTimestamp(4, new Timestamp(time.getTime()));
			stmt.setFloat(5, otkup.getPrimjesa());
			stmt.setFloat(6, otkup.getVlaga());
			stmt.setFloat(7, otkup.getCijena());

			stmt.executeUpdate();
			return new ResponseEntity<>("Otkup uspjesno dodan u database", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("Error dodavanja Otkupa u database: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
