package ie.gmit.rad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
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
			String co_code = myRs.getString("co_code");
			String co_name = myRs.getString("co_name");
			String co_details = myRs.getString("co_details");

			// create new country object
			Country country = new Country(co_code, co_name, co_details);

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
		myStmt.setString(1, country.getCo_code());
		myStmt.setString(2, country.getCo_name());
		myStmt.setString(3, country.getCo_details());
		myStmt.execute();			
	}
	
	public void updateCountry(String co_code, String co_name, String co_details) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt;
		
		myConn = mysqlDS.getConnection();
		
		String sql = "SELECT from country SET co_name ='" + co_name + "' ,co_details ='" + co_details + "' WHERE co_code = '"+ co_code +"';";
		myStmt = myConn.prepareStatement(sql);
		
		myConn.close();
		myStmt.close();
		
		myStmt.executeUpdate(sql);
		
	}

}

