package com.abhikarma_rajani.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Student;
import com.abhikarma_rajani.repository.StudentRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	// Student Count:
	public List<Student> getAllStudent()
	{
		List<Student> allStudent = studentRepository.findAll();
		return allStudent;
	}
	
	// T-2023
	public List<Student> getAll2023()
	{
		List<Student> allsess = studentRepository.findAllsess23();
		return allsess;
	}

	// T-2024
	public List<Student> getAll2024()
	{
		List<Student> allsess24 = studentRepository.findAllsess24();
		return allsess24;
	}
	
	// T-2024
	public List<Student> getAll2025()
	{
		List<Student> allsess25 = studentRepository.findAllsess25();
		return allsess25;
	}

	
	// (06)
	public Student addStudent(Student student)
	{
		return studentRepository.save(student);
	}
	
	// (*)
	public boolean checkAdmissionId(long admissionId)
	{
		return studentRepository.existsByAdmissionId(admissionId);
	}
	
	// (*)
	public void removeSessionMessage()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg"); 
	}
	
	// (*)
	public void removeSessionMessage2()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msgd"); 
	}
	
	// (*)
	public void removeSessionMessage3()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msgu"); 
	}
	
	// (08) Code given below use for search and list !!
	public List<Student> listAll(String keyword)
	{
		if(keyword != null)
		{
			return studentRepository.search(keyword);
		}
		else
		{
			return(List<Student>) studentRepository.findAll();
		}	
	}
	
	// (09)
	public void deleteStudentWithId(long id)
	{
		studentRepository.deleteById(id);
		
	}

	// (10)
	public Student updateStudentWithId(long id)
	{
		Optional<Student> temp = studentRepository.findById(id);
		Student student = temp.get();
		return student;
	}
	
	// (11)
	public void editStudent(Student student)
	{
		studentRepository.save(student);
	}
	
	// (12)
	public Student viewParticularStudentDetailsWithId(long id)
	{
		Optional<Student> findById = studentRepository.findById(id);
		Student student = findById.get();
		return student;
	}
	
	// (13) Business logic for EXCEL REPORT GENERATION (FOR ALL):
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException
	{
		List<Student> findAllList = studentRepository.findAll();
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Student Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("ADMISSION ID");
		row.createCell(2).setCellValue("FIRST NAME");
		row.createCell(3).setCellValue("LAST NAME");
		row.createCell(4).setCellValue("GENDER");
		row.createCell(5).setCellValue("DATE OF BIRTH");
		row.createCell(6).setCellValue("ROLL NUMBER");
		row.createCell(7).setCellValue("BLOOD GROUP");
		row.createCell(8).setCellValue("RELIGION");
		row.createCell(9).setCellValue("EMAIL");
		row.createCell(10).setCellValue("CLASS NAME");
		row.createCell(11).setCellValue("SECTION");
		row.createCell(12).setCellValue("STUDENT MOBILE NUMBER");
		row.createCell(13).setCellValue("PARENT MOBILE NUMBER");
		row.createCell(14).setCellValue("FATHER NAME");
		row.createCell(15).setCellValue("MOTHER NAME");
		row.createCell(16).setCellValue("ADDRESS");
		
		int dataRowIndex= 1; 
	
		for(Student student : findAllList)
		{
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(student.getId());
			dataRow.createCell(1).setCellValue(student.getAdmissionId());
			dataRow.createCell(2).setCellValue(student.getFirstName());
			dataRow.createCell(3).setCellValue(student.getLastName());
			dataRow.createCell(4).setCellValue(student.getGender());
			dataRow.createCell(5).setCellValue(student.getDateOfBirth());
			dataRow.createCell(6).setCellValue(student.getRollNumber());
			dataRow.createCell(7).setCellValue(student.getBloodGroup());
			dataRow.createCell(8).setCellValue(student.getReligion());
			dataRow.createCell(9).setCellValue(student.getEmail());
			dataRow.createCell(10).setCellValue(student.getClassName());
			dataRow.createCell(11).setCellValue(student.getSection());
			dataRow.createCell(12).setCellValue(student.getStudentMobileNumber());
			dataRow.createCell(13).setCellValue(student.getParentMobileNumber());
			dataRow.createCell(14).setCellValue(student.getFatherName());
			dataRow.createCell(15).setCellValue(student.getMotherName());
			dataRow.createCell(16).setCellValue(student.getAddress());
			
			dataRowIndex ++;
		}
			
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
	}

	// (14) Business logic for EXCEL REPORT GENERATION (BY ID):
	public void generateExcelById(long id, HttpServletResponse httpServletResponse) throws IOException
	{
		Optional<Student> findById = studentRepository.findById(id);
		Student student = findById.get();
		
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Student Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("ADMISSION ID");
		row.createCell(2).setCellValue("FIRST NAME");
		row.createCell(3).setCellValue("LAST NAME");
		row.createCell(4).setCellValue("GENDER");
		row.createCell(5).setCellValue("DATE OF BIRTH");
		row.createCell(6).setCellValue("ROLL NUMBER");
		row.createCell(7).setCellValue("BLOOD GROUP");
		row.createCell(8).setCellValue("RELIGION");
		row.createCell(9).setCellValue("EMAIL");
		row.createCell(10).setCellValue("CLASS NAME");
		row.createCell(11).setCellValue("SECTION");
		row.createCell(12).setCellValue("STUDENT MOBILE NUMBER");
		row.createCell(13).setCellValue("PARENT MOBILE NUMBER");
		row.createCell(14).setCellValue("FATHER NAME");
		row.createCell(15).setCellValue("MOTHER NAME");
		row.createCell(16).setCellValue("ADDRESS");
		
		int dataRowIndex= 1; 
	
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(student.getId());
			dataRow.createCell(1).setCellValue(student.getAdmissionId());
			dataRow.createCell(2).setCellValue(student.getFirstName());
			dataRow.createCell(3).setCellValue(student.getLastName());
			dataRow.createCell(4).setCellValue(student.getGender());
			dataRow.createCell(5).setCellValue(student.getDateOfBirth());
			dataRow.createCell(6).setCellValue(student.getRollNumber());
			dataRow.createCell(7).setCellValue(student.getBloodGroup());
			dataRow.createCell(8).setCellValue(student.getReligion());
			dataRow.createCell(9).setCellValue(student.getEmail());
			dataRow.createCell(10).setCellValue(student.getClassName());
			dataRow.createCell(11).setCellValue(student.getSection());
			dataRow.createCell(12).setCellValue(student.getStudentMobileNumber());
			dataRow.createCell(13).setCellValue(student.getParentMobileNumber());
			dataRow.createCell(14).setCellValue(student.getFatherName());
			dataRow.createCell(15).setCellValue(student.getMotherName());
			dataRow.createCell(16).setCellValue(student.getAddress());
			
			dataRowIndex ++;
			
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
	}

}
