package ie.gmit.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ie.gmit.DAO.*;
import ie.gmit.Model.*;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SessionScoped
@ManagedBean
public class CountryController {

	ArrayList<Country> countries;
	private CountryDAO countryDAO;
	private Country country;

	public CountryController() {
		super();
		countries = new ArrayList<Country>();
		try {
			countryDAO = new CountryDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CountryController(ArrayList<Country> countries) {
		super();
		this.countries = countries;
	}
	
	public ArrayList <Country> getCountries(){
		return countries;
	}
	
	public void setCountries(ArrayList<Country>countries) {
		this.countries = countries;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public void loadCountries() throws Exception {
		countries.clear();
		if (countryDAO != null) {
			try {
				countries = countryDAO.loadCountries();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String addCountry(Country country) {
		if (countryDAO != null) {
			try {
				countryDAO.addCountry(country);
				return "list_countries";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country " + country.getCountryName() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " + country.getCountryName());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String loadCountry(String countryCode) {
		try {
			country = countryDAO.loadCountry(countryCode);
			return "view_country";	
		}catch (Exception e) {
			return null;
		}
	}
	
	public String deleteCountry(String countryCode) {
		try {
			countryDAO.deleteCountry(countryCode);
			return "list_countries";
		} catch (Exception e) {
			return null;
		}
	}
	
	public String updateCountry(String countryCode, String countryName, String countryDetails){
		try {
			countryDAO.updateCountry(countryCode, countryName, countryDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list_countries";
	}

}