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
public class CityController {
	
	ArrayList<City> cities;
	private CityDAO cityDAO;
	private City city;

	public CityController() {
		super();
		cities = new ArrayList<City>();
		try {
			cityDAO = new CityDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CityController(ArrayList<City> cities) {
		super();
		this.cities = cities;
	}
	
	public ArrayList <City> getCities(){
		return cities;
	}
	
	public void setCities(ArrayList<City>cities) {
		this.cities = cities;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public void loadCities() throws Exception {
		cities.clear();
		if (cityDAO != null) {
			try {
				cities = cityDAO.loadCities();
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Could not load Cities from the DAO");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public String addCity(City city) {
		if (cityDAO != null) {
			try {
				cityDAO.addCity(city);
				return "list_cities";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Attempting to add Country: " + city.getCountryCode() + ", Region: " + city.getRegionCode() + " and City: " + city.getCityCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert City " + city.getCityName());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
		return null;
	}
}
