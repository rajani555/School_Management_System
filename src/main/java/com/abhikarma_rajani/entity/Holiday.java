package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "holiday")
public class Holiday
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String holidayName;
	private String typeofHoliday;
	private String startDate;
	private String endDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public String getTypeofHoliday() {
		return typeofHoliday;
	}
	public void setTypeofHoliday(String typeofHoliday) {
		this.typeofHoliday = typeofHoliday;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Holiday(long id, String holidayName, String typeofHoliday, String startDate, String endDate) {
		super();
		this.id = id;
		this.holidayName = holidayName;
		this.typeofHoliday = typeofHoliday;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Holiday() {
		super();
		// TODO Auto-generated constructor stub
	}

}
