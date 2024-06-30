package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "room")
public class Room 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String block;
	private String roomNo;
	private String roomType;
	private String noOfBeds;
	private String costPerBed;
	private String availability;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getNoOfBeds() {
		return noOfBeds;
	}
	public void setNoOfBeds(String noOfBeds) {
		this.noOfBeds = noOfBeds;
	}
	public String getCostPerBed() {
		return costPerBed;
	}
	public void setCostPerBed(String costPerBed) {
		this.costPerBed = costPerBed;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Room(long id, String block, String roomNo, String roomType, String noOfBeds, String costPerBed,
			String availability) {
		super();
		this.id = id;
		this.block = block;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.noOfBeds = noOfBeds;
		this.costPerBed = costPerBed;
		this.availability = availability;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
