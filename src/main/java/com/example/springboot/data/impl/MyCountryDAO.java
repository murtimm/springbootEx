package com.example.springboot.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Country;

@Repository
@Component
public class MyCountryDAO extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public List<Country> getCountries() {
		System.out.println("DAO    ******$$$$$$$$$$$$default countries.....");
		// List listOfCountries = countryService.getAllCountries();
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Country> result = new ArrayList<Country>();
		for (Map<String, Object> row : rows) {
			Country emp = new Country();
			emp.setId((int) row.get("id"));
			emp.setCountryName((String) row.get("countryName"));
			emp.setPopulation((long) row.get("population"));
			result.add(emp);
		}

		return result;
	}

	public Country addCountry(Country country) {
		System.out.println("Add country dao");
		String sql = "INSERT INTO COUNTRY " + "(id, countryName,population) VALUES (?, ?,?)";
		getJdbcTemplate().update(sql,
				new Object[] { country.getId(), country.getCountryName(), country.getPopulation() });
		return getCountries().get(0);
	}

	public void updateCountry(Country country) {
		 getJdbcTemplate().update("");

	}

	public void deleteCountry(@PathParam("id") int id) {
		 getJdbcTemplate().execute("");

	}

}
