package com.example.springboot.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.data.impl.MyCountryDAO;
import com.example.springboot.model.Country;
import com.example.springboot.service.impl.MYCountryService;

@RestController
@RequestMapping("/sayHi")
public class RestEx {

	@Autowired
	MYCountryService countryService;
	@Autowired
	MyCountryDAO myCountryDAO;

	@GetMapping(produces="application/json")
	public List getCountries() {
		System.out.println("$$$$$$$$$$$$default countries.....");
		List listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}

	@GetMapping(value="/getCountry/{id}", produces= {"application/json"})
	public Country getCountryById(@PathVariable("id") Integer id) {
		return countryService.getCountry(id);
	}

	@PostMapping( produces= {"application/json"})
	public Country addCountry(Country country) {
		return countryService.addCountry(country);
	}

	@PutMapping( produces= {"application/json"})
	public Country updateCountry(Country country) {
		return countryService.updateCountry(country);

	}

	@DeleteMapping(value="/{id}", produces= {"application/json"})
	public void deleteCountry(@PathParam("id") int id) {
		countryService.deleteCountry(id);

	}
}
