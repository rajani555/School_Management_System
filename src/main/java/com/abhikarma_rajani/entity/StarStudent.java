package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "satr_student")
public class StarStudent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String rollNumber;
	private String totalMarks;
	private String percentage;
	private String year;
	private String imageName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public StarStudent(long id, String name, String rollNumber, String totalMarks, String percentage, String year, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.rollNumber = rollNumber;
		this.totalMarks = totalMarks;
		this.percentage = percentage;
		this.year = year;
		this.imageName = imageName;
	}
	public StarStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
}
