package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "exam")
public class Exam
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String examName;
	private String className;
	private String subject;
	private String fees;
	private String startTime;
	private String endTime;
	private String examDate;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String starTime) {
		this.startTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public Exam(long id, String examName, String className, String subject, String fees, String startTime,
			String endTime, String examDate) {
		super();
		this.id = id;
		this.examName = examName;
		this.className = className;
		this.subject = subject;
		this.fees = fees;
		this.startTime = startTime;
		this.endTime = endTime;
		this.examDate = examDate;
	}
	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
