package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "transport")
public class Transport 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String routeName;
	private String vehicleNumber;
	private String nosOfVehicle;
	private String driverName;
	private String contactNumber;
	private String driverAddress;
	private String licenseNumber;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getNosOfVehicle() {
		return nosOfVehicle;
	}
	public void setNosOfVehicle(String nosOfVehicle) {
		this.nosOfVehicle = nosOfVehicle;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getDriverAddress() {
		return driverAddress;
	}
	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public Transport(long id, String routeName, String vehicleNumber, String nosOfVehicle, String driverName,
			String contactNumber, String driverAddress, String licenseNumber) {
		super();
		this.id = id;
		this.routeName = routeName;
		this.vehicleNumber = vehicleNumber;
		this.nosOfVehicle = nosOfVehicle;
		this.driverName = driverName;
		this.contactNumber = contactNumber;
		this.driverAddress = driverAddress;
		this.licenseNumber = licenseNumber;
	}
	public Transport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
