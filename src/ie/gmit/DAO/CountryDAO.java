package ie.gmit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ie.gmit.Model.Country;

public class CountryDAO {
	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public CountryDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<Country> loadCountries() throws Exception {
		ArrayList<Country> countries = new ArrayList<Country>();
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from country";

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String countryCode = myRs.getString("co_code");
			String countryName = myRs.getString("co_name");
			String countryDetails = myRs.getString("co_details");

			// create new country object
			Country country = new Country(countryCode, countryName, countryDetails);

			countries.add(country);
		}	
		return countries;
	}
	
	public Country loadCountry(String co_code) throws Exception {
		List<Country> countries = new ArrayList<Country>();
		Country country = new Country();
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from country WHERE co_code = '" + co_code + "';";

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String countryCode = myRs.getString("co_code");
			String countryName = myRs.getString("co_name");
			String countryDetails = myRs.getString("co_details");

			country = new Country(countryCode, countryName, countryDetails);
			countries.add(country);
		}	
		return country;
	}
	
	public void addCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into country values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCountryCode());
		myStmt.setString(2, country.getCountryName());
		myStmt.setString(3, country.getCountryDetails());
		myStmt.execute();			
	}
	
	public void updateCountry(String countryCode, String countryName, String countryDetails) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		
		String sql = "UPDATE country SET co_name = ? , co_details = ? WHERE co_code = ?";
		myStmt = myConn.prepareStatement(sql);

		myStmt.setString(1, countryName);
		myStmt.setString(2, countryDetails);
		myStmt.setString(3, countryCode);
		
		myStmt.execute();
		myConn.close();
		myStmt.close();
	}
	
	public void deleteCountry(String countryCode) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		
		String sql = "DELETE from country WHERE co_code = ?";
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1, countryCode);
		
		myStmt.executeUpdate();
		myStmt.close();
		myConn.close();
		
		
	}

}

