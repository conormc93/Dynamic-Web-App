package ie.gmit.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class City {
	
	private String cityCode;
	private String countryCode;
	private String regionCode;
	private String cityName;
	private int population;
	private boolean isCoastal;
	private float areaKM;
	
	public City() {
		super();
	}

	public City(String cityCode, String countryCode, String regionCode, String cityName, int population,
			boolean isCoastal, float areaKM) {
		super();
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.regionCode = regionCode;
		this.cityName = cityName;
		this.population = population;
		this.isCoastal = isCoastal;
		this.areaKM = areaKM;
	}

		
	public String getCityCode() {
		return cityCode;
	}

	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	
	public String getCountryCode() {
		return countryCode;
	}

	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	
	public String getRegionCode() {
		return regionCode;
	}

	
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	
	public String getCityName() {
		return cityName;
	}

	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	public int getPopulation() {
		return population;
	}

	
	public void setPopulation(int population) {
		this.population = population;
	}

	
	public boolean getisCoastal() {
		return isCoastal;
	}

	
	public void setCoastal(boolean isCoastal) {
		this.isCoastal = isCoastal;
	}

	
	public float getAreaKM() {
		return areaKM;
	}

	
	public void setAreaKM(float areaKM) {
		this.areaKM = areaKM;
	}
	
}
