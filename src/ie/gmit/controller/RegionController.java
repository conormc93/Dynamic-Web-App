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
public class RegionController {
	ArrayList<Region> regions;
	private RegionDAO regionDAO;
	private Region region;

	public RegionController() {
		super();
		regions = new ArrayList<Region>();
		try {
			regionDAO = new RegionDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RegionController(ArrayList<Region> regions) {
		super();
		this.regions = regions;
	}
	
	public ArrayList <Region> getRegions(){
		return regions;
	}
	
	public void setRegions(ArrayList<Region>regions) {
		this.regions = regions;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public void loadRegions() throws Exception {
		regions.clear();
		if (regionDAO != null) {
			try {
				regions = regionDAO.loadRegions();
				System.out.println("IN LOAD REGIONS");
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Could not load Regions from the DAO. ");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public String addRegion(Region region) {
		if (regionDAO != null) {
			try {
				regionDAO.addRegion(region);
				return "list_regions";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Region " + region.getRegionName() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Region " + region.getRegionName());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
		return null;
	}

}
