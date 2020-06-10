package com.example.springboot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.data.impl.MyCountryDAO;
import com.example.springboot.model.Country;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
@Component
public class MYCountryService {

	static HashMap<Integer, Country> countryIdMap = getCountryIdMap();
	
	@Autowired
	MyCountryDAO countryDAO;

	public MYCountryService() {
		super();

		if (countryIdMap == null) {
			countryIdMap = new HashMap<Integer, Country>();
			// Creating some object of countries while initializing
			Country indiaCountry = new Country(1, "India", 10000);
			Country chinaCountry = new Country(4, "China", 20000);
			Country nepalCountry = new Country(3, "Nepal", 8000);
			Country bhutanCountry = new Country(2, "Bhutan", 7000);

			countryIdMap.put(1, indiaCountry);
			countryIdMap.put(4, chinaCountry);
			countryIdMap.put(3, nepalCountry);
			countryIdMap.put(2, bhutanCountry);
		}
	}

	public List getAllCountries() {
		List countries = new ArrayList(countryIdMap.values());
		return countries;
	}

	public Country getCountry(int id) {
		Country country = countryIdMap.get(id);

		if (country == null) {
			System.out.println("Country with id " + id + " not found");
		}
		return country;
	}

	public Country addCountry(Country country) {
		System.out.println("Before add  = id" + country.getId());
		country.setId(getMaxId() + 1);
		System.out.println("Before add country = id" + country.getId());
		System.out.println("Before add country = size" + countryIdMap.size());
		countryIdMap.put(country.getId(), country);
		System.out.println("after add country = size" + countryIdMap.size());
		countryDAO.addCountry(country);
		
		return country;
	}

	public Country updateCountry(Country country) {
		System.out.println("update country");
		if (country.getId() <= 0)
			return null;
		countryIdMap.put(country.getId(), country);
		System.out.println("update country");
		return country;

	}

	public void deleteCountry(int id) {
		System.out.println("deleteCountry country");
		countryIdMap.remove(id);
		System.out.println("deleteCountry country");
	}

	public static HashMap<Integer, Country> getCountryIdMap() {
		System.out.println("Get country map$$$$$$$$$$$$$$$$$$");
		return countryIdMap;
	}

	public static int getMaxId() {

		return countryIdMap.size() + 1;
	}
}
