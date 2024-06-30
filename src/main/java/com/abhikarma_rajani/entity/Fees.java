package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name= "fees")
public class Fees
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private long admissionId;
	private String studentName;
	private String className;
	private String gender;
	private String feeType;
	private double feeAmount;
	private double paidAmount;
	private double remainingAmount;
	private String feesStatus;
	private String paidDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(long admissionId) {
		this.admissionId = admissionId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public String getFeesStatus() {
		return feesStatus;
	}
	public void setFeesStatus(String feesStatus) {
		this.feesStatus = feesStatus;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public Fees(long id, long admissionId, String studentName, String className, String gender, String feeType,
			double feeAmount, double paidAmount, double remainingAmount, String feesStatus, String paidDate) {
		super();
		this.id = id;
		this.admissionId = admissionId;
		this.studentName = studentName;
		this.className = className;
		this.gender = gender;
		this.feeType = feeType;
		this.feeAmount = feeAmount;
		this.paidAmount = paidAmount;
		this.remainingAmount = remainingAmount;
		this.paidDate = paidDate;
		this.feesStatus = feesStatus;
	}
	public Fees() {
		super();
		// TODO Auto-generated constructor stub
	}

}
