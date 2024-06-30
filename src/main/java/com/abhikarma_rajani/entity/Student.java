package com.abhikarma_rajani.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name= "student")
public class Student
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String admissionSession;
	private long admissionId;
	private String firstName;
	private String lastName;
	private String gender;
	private String dateOfBirth;
	private String rollNumber;
	private String bloodGroup;
	private String religion;
	private String email;
	private String className;
	private String section;
	private long studentMobileNumber;
	private long parentMobileNumber;
	private String fatherName;
	private String motherName;
	private String address;
	
	private String imageName;
	

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getAdmissionSession() {
		return admissionSession;
	}

	public void setAdmissionSession(String admissionSession) {
		this.admissionSession = admissionSession;
	}
	
	public long getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(long admissionId) {
		this.admissionId = admissionId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public long getStudentMobileNumber() {
		return studentMobileNumber;
	}

	public void setStudentMobileNumber(long studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}

	public long getParentMobileNumber() {
		return parentMobileNumber;
	}

	public void setParentMobileNumber(long parentMobileNumber) {
		this.parentMobileNumber = parentMobileNumber;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Student(String admissionSession, long admissionId, String firstName, String lastName, String gender, String dateOfBirth,
			String rollNumber, String bloodGroup, String religion, String email, String className, String section,
			long studentMobileNumber, long parentMobileNumber, String fatherName, String motherName, String address,
			String imageName) {
		super();
		this.admissionSession = admissionSession;
		this.admissionId = admissionId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.rollNumber = rollNumber;
		this.bloodGroup = bloodGroup;
		this.religion = religion;
		this.email = email;
		this.className = className;
		this.section = section;
		this.studentMobileNumber = studentMobileNumber;
		this.parentMobileNumber = parentMobileNumber;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.imageName = imageName;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

}
