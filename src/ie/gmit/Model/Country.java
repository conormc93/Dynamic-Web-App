package ie.gmit.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Country {
	
	private String countryCode;
	private String countryName;
	private String countryDetails;
	
	public Country() {
		super();
	}

	public Country(String countryCode, String countryName, String countryDetails) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.countryDetails = countryDetails;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryDetails() {
		return countryDetails;
	}

	public void setCountryDetails(String countryDetails) {
		this.countryDetails = countryDetails;
	}
	
}
