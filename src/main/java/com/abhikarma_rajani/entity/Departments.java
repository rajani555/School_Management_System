package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name= "departments")
public class Departments 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String departmentId;
	private String departmentName;
	private String headOfDepartment;
	private String departmentStartDate;
	private long numbersOfStudent;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getHeadOfDepartment() {
		return headOfDepartment;
	}
	public void setHeadOfDepartment(String headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}
	public String getDepartmentStartDate() {
		return departmentStartDate;
	}
	public void setDepartmentStartDate(String departmentStartDate) {
		this.departmentStartDate = departmentStartDate;
	}
	public long getNumbersOfStudent() {
		return numbersOfStudent;
	}
	public void setNumbersOfStudent(long numbersOfStudent) {
		this.numbersOfStudent = numbersOfStudent;
	}
	public Departments(long id, String departmentId, String departmentName, String headOfDepartment,
			String departmentStartDate, long numbersOfStudent) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.headOfDepartment = headOfDepartment;
		this.departmentStartDate = departmentStartDate;
		this.numbersOfStudent = numbersOfStudent;
	}
	public Departments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
