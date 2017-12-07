package ie.gmit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ie.gmit.Model.City;

public class CityDAO {
private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public CityDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<City> loadCities() throws Exception {
		ArrayList<City> cities = new ArrayList<City>();
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from city";

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String cityCode = myRs.getString("cty_code");
			String countryCode = myRs.getString("co_code");
			String regionCode = myRs.getString("reg_code");
			String cityName = myRs.getString("cty_name");
			int population = myRs.getInt("population");
			boolean isCoastal = myRs.getBoolean("isCoastal");
			float areaKM = myRs.getFloat("areaKM");

			// create new region object
			City city = new City(cityCode, countryCode, regionCode, cityName, population, isCoastal, areaKM);

			cities.add(city);
		}	
		return cities;
	}
	
	public void addCity(City city) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into city values (?, ?, ?, ?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1, city.getCityCode());
		myStmt.setString(2, city.getCountryCode());
		myStmt.setString(3, city.getRegionCode());
		myStmt.setString(4, city.getCityName());
		myStmt.setInt(5, city.getPopulation());
		myStmt.setBoolean(6, city.isCoastal());
		myStmt.setFloat(7, city.getAreaKM());
		
		myStmt.execute();			
	}

}
