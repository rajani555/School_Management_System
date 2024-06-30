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

import com.abhikarma_rajani.entity.Subject;
import com.abhikarma_rajani.repository.SubjectRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class SubjectService
{
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Subject saveSubject(Subject subject)
	{
		 return subjectRepository.save(subject);
	}
	
	// (*)
	public boolean checkSubjectId(String subjectId)
	{
		boolean existsBySubjectId = subjectRepository.existsBySubjectId(subjectId);
		return existsBySubjectId;
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

	public List<Subject> listAll(String keyword)
	{
		if(keyword != null)
		{
			return subjectRepository.search(keyword);
		}
		else
		{
			return(List<Subject>) subjectRepository.findAll();
		}
	}

	public void deleteSubjectWithId(long id)
	{
		subjectRepository.deleteById(id);
		
	}

	public Subject updateSubject(long id)
	{
		Optional<Subject> findById = subjectRepository.findById(id);
		Subject subject = findById.get();
		return subject;
		
	}

	public void updateSubject(Subject subject)
	{
		subjectRepository.save(subject);
		
	}

	// (14) Business logic for EXCEL REPORT GENERATION:
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException
	{
		List<Subject> findAllList = subjectRepository.findAll();
		
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Subject Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		// 1. Cell Row
		//row.createCell(0).setCellValue("ID");
		row.createCell(0).setCellValue("SUBJECT ID");
		row.createCell(1).setCellValue("SUBJECT NAME");
		row.createCell(2).setCellValue("CLASS NAME");
		
		int dataRowIndex= 1; 
		
		for(Subject subject : findAllList)
		{
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			//dataRow.createCell(0).setCellValue(subject.getId());
			dataRow.createCell(0).setCellValue(subject.getSubjectId());
			dataRow.createCell(1).setCellValue(subject.getSubjectName());
			dataRow.createCell(2).setCellValue(subject.getClassName());
			
			dataRowIndex ++;
		}
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
		
	}
	
}

