package ie.gmit.rad;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SessionScoped
@ManagedBean
public class CountryController {

	ArrayList<Country> countries;
	private DAO dao;
	private Country country;

	public CountryController() {
		super();
		countries = new ArrayList<Country>();
		try {
			dao = new DAO();
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
		if (dao != null) {
			try {
				countries = dao.loadCountries();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String addCountry(Country country) {
		if (dao != null) {
			try {
				dao.addCountry(country);
				return "list_countries";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country " + country.getCo_name() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " + country.getCo_name());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String loadCountry(String co_code) {
		try {
			country = dao.loadCountry(co_code);
			return "view_country";	
		}catch (Exception e) {
			return null;
		}
	}
	
	public String deleteCountry(String co_code) {
		try {
			dao.deleteCountry(co_code);
			return "list_countries";
		} catch (Exception e) {
			return null;
		}
	}
	public String updateCountry(String co_code, String co_name, String co_details){
		try {
			dao.updateCountry(co_code, co_name, co_details);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list_countries";
	}

}