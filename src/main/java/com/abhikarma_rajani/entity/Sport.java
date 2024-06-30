package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "sport")
public class Sport 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String sportId;
	private String sportName;
	private String coachName;
	private String startTime;
	private String endTime;
	private String startedYear;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSportId() {
		return sportId;
	}
	public void setSportId(String sportId) {
		this.sportId = sportId;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartedYear() {
		return startedYear;
	}
	public void setStartedYear(String startedYear) {
		this.startedYear = startedYear;
	}
	public Sport(long id, String sportId, String sportName, String coachName, String startTime, String endTime,
			String startedYear) {
		super();
		this.id = id;
		this.sportId = sportId;
		this.sportName = sportName;
		this.coachName = coachName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startedYear = startedYear;
	}
	public Sport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
