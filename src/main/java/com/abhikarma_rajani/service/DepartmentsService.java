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

import com.abhikarma_rajani.entity.Departments;
import com.abhikarma_rajani.repository.DepartmentsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class DepartmentsService 
{
	
	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	// Department Count:
	public List<Departments> getAllDepartment()
	{
		List<Departments> department = departmentsRepository.findAll();
		return department;
	}
	
	// (02)
	public Departments addDepartmentsWithCondition(Departments departments)
	{
		return departmentsRepository.save(departments);
	}
	
	// (*)
	public 	boolean checkDepartmentsId(String departmentId)
	{
		return departmentsRepository.existsByDepartmentId(departmentId);
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

	// (03)
	public List<Departments> listAll(String keyword)
	{
		if(keyword != null)
		{
			return departmentsRepository.search(keyword);
		}
		else
		{
			return(List<Departments>) departmentsRepository.findAll();
		}
	}

	// (04)
	public void deleteDepartments(Long id)
	{
		departmentsRepository.deleteById(id);
		
	}

	// (05)
	public Departments updateDepartmentsWithId(long id)
	{
		Optional<Departments> findById = departmentsRepository.findById(id);
		Departments departments = findById.get();
		return departments;
		
	}

	// (06)
	public void saveAfterUpdate(Departments departments)
	{
		departmentsRepository.save(departments);
		
	}
	
	// (07) Business logic for EXCEL REPORT GENERATION:
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException
	{
		List<Departments> findAllList = departmentsRepository.findAll();
		
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Departments Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		// 1. Cell Row
		//row.createCell(0).setCellValue("ID");
		row.createCell(0).setCellValue("DEPARTMENT ID");
		row.createCell(1).setCellValue("DEPARTMENT NAME");
		row.createCell(2).setCellValue("HEAD OF DEPARTMENT");
		row.createCell(3).setCellValue("DEPARTMENT START DATE");
		row.createCell(4).setCellValue("NUMBERS OF STUDENT");
		
		int dataRowIndex= 1; 
		
		for(Departments departments : findAllList)
		{
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			//dataRow.createCell(0).setCellValue(departments.getId());
			dataRow.createCell(0).setCellValue(departments.getDepartmentId());
			dataRow.createCell(1).setCellValue(departments.getDepartmentName());
			dataRow.createCell(2).setCellValue(departments.getHeadOfDepartment());
			dataRow.createCell(3).setCellValue(departments.getDepartmentStartDate());
			dataRow.createCell(4).setCellValue(departments.getNumbersOfStudent());
			
			dataRowIndex ++;
		}
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
		
	}
	
	// (08) For all PDF...!!
	public List<Departments> listAll()
	{
		List<Departments> findAll = departmentsRepository.findAll();
		return findAll;
	}

	// (09) For particular PDF...!!
	public Departments printPdfWithId(long id)
	{
		Optional<Departments> temp = departmentsRepository.findById(id);
		Departments particulardepartments = temp.get();
		return particulardepartments;	
	}

}
