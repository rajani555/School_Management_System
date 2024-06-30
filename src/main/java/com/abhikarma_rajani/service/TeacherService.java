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

import com.abhikarma_rajani.entity.Teacher;
import com.abhikarma_rajani.repository.TeacherRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class TeacherService
{
	@Autowired
	private TeacherRepository teacherRepository;
	
	/*
	 // (02)
	public void addTeacher(Teacher teacher)
	{
		teacherRepository.save(teacher);
	}
	*/
	
	// (03)
	public Teacher addTeacherWithCondition(Teacher teacher)
	{
		return teacherRepository.save(teacher);	
	}
	
	// (*)
	public boolean checkTeacherId(long teacherId)
	{
		return teacherRepository.existsByTeacherId(teacherId);
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
	

	// (04)
	public List<Teacher> listAll(String keyword)
	{
		if(keyword != null)
		{
			return teacherRepository.search(keyword);
		}
		else
		{
			return(List<Teacher>) teacherRepository.findAll();
		}	
	}

	// (05)
	public void deleteStudentWithId(long id)
	{
		teacherRepository.deleteById(id);	
	}

	// (06)
	public Teacher updateTeacherWithId(long id)
	{
		Optional<Teacher> temp = teacherRepository.findById(id);
		Teacher teacher = temp.get();
		return teacher;
	}

	// (07)
	public void editTeacher(Teacher teacher)
	{
		teacherRepository.save(teacher);
	}
		
	// (08) Business logic for EXCEL REPORT GENERATION:
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException
	{
		List<Teacher> findAllList = teacherRepository.findAll();
		
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Teacher Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		//row.createCell(0).setCellValue("ID");
		row.createCell(0).setCellValue("TEACHER ID");
		row.createCell(1).setCellValue("TEACHER NAME");
		row.createCell(2).setCellValue("GENDER");
		row.createCell(3).setCellValue("DATE OF BIRTH");
		row.createCell(4).setCellValue("MOBILE NUMBER");
		row.createCell(5).setCellValue("JOINING DATE");
		row.createCell(6).setCellValue("QUALIFICATION");
		row.createCell(7).setCellValue("EXPERIENCE");
		row.createCell(8).setCellValue("EMAIL");
		row.createCell(9).setCellValue("ADDRESS");
		row.createCell(10).setCellValue("CITY");
		row.createCell(11).setCellValue("STATE");
		row.createCell(12).setCellValue("ZIP CODE");
		row.createCell(13).setCellValue("COUNTRY");
		
		int dataRowIndex= 1; 
		
		for(Teacher teacher : findAllList)
		{
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			//dataRow.createCell(0).setCellValue(teacher.getId());
			dataRow.createCell(0).setCellValue(teacher.getTeacherId());
			dataRow.createCell(1).setCellValue(teacher.getName());
			dataRow.createCell(2).setCellValue(teacher.getGender());
			dataRow.createCell(3).setCellValue(teacher.getDateOfBirth());
			dataRow.createCell(4).setCellValue(teacher.getMobileNumber());
			dataRow.createCell(5).setCellValue(teacher.getJoiningDate());
			dataRow.createCell(6).setCellValue(teacher.getQualification());
			dataRow.createCell(7).setCellValue(teacher.getExperience());
			dataRow.createCell(8).setCellValue(teacher.getEmail());
			dataRow.createCell(9).setCellValue(teacher.getAddress());
			dataRow.createCell(10).setCellValue(teacher.getCity());
			dataRow.createCell(11).setCellValue(teacher.getState());
			dataRow.createCell(12).setCellValue(teacher.getZipCode());
			dataRow.createCell(13).setCellValue(teacher.getCountry());
			
			dataRowIndex ++;
		}
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
		
	}
		
}
