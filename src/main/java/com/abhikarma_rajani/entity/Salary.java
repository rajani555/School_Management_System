package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name= "salary")
public class Salary
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String staffId;
	private String name;
	private String gender;
	private String joiningDate;
	private double amount;
	private double paidSalaryAmount;
	private double RemainingSalaryAmount;
	private String salaryStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPaidSalaryAmount() {
		return paidSalaryAmount;
	}

	public void setPaidSalaryAmount(double paidSalaryAmount) {
		this.paidSalaryAmount = paidSalaryAmount;
	}

	public double getRemainingSalaryAmount() {
		return RemainingSalaryAmount;
	}

	public void setRemainingSalaryAmount(double remainingSalaryAmount) {
		RemainingSalaryAmount = remainingSalaryAmount;
	}
	public String getSalaryStatus() {
		return salaryStatus;
	}

	public void setSalaryStatus(String salaryStatus) {
		this.salaryStatus = salaryStatus;
	}

	public Salary(long id, String staffId, String name, String gender, String joiningDate, double amount, double paidSalaryAmount, double RemainingSalaryAmount, String salaryStatus) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.name = name;
		this.gender = gender;
		this.joiningDate = joiningDate;
		this.amount = amount;
		this.paidSalaryAmount = paidSalaryAmount;
		this.RemainingSalaryAmount = RemainingSalaryAmount;
		this.salaryStatus = salaryStatus;
	}

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

}
