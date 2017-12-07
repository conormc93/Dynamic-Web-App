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

import ie.gmit.Model.Region;

public class RegionDAO {
	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public RegionDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<Region> loadRegions() throws Exception {
		ArrayList<Region> regions = new ArrayList<Region>();
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from region";

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String countryCode = myRs.getString("co_code");
			String regionCode = myRs.getString("reg_code");
			String regionName = myRs.getString("reg_name");
			String regionDetails = myRs.getString("reg_desc");

			// create new region object
			Region region = new Region(countryCode, regionCode, regionName, regionDetails);

			regions.add(region);
		}	
		return regions;
	}
	
	public Region loadRegion(String reg_code) throws Exception {
		List<Region> regions = new ArrayList<Region>();
		Region region = new Region();
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from region WHERE reg_code = '" + reg_code + "';";

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String countryCode = myRs.getString("co_code");
			String regionCode = myRs.getString("reg_code");
			String regionName = myRs.getString("reg_name");
			String regionDetails = myRs.getString("reg_details");

			region = new Region(countryCode, regionCode, regionName, regionDetails);
			regions.add(region);
		}	
		return region;
	}
	
	public void addRegion(Region region) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into region values (?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, region.getCountryCode());
		myStmt.setString(2, region.getRegionCode());
		myStmt.setString(3, region.getRegionName());
		myStmt.setString(4, region.getRegionDesc());
		myStmt.execute();			
	}

}


