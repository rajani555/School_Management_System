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

import com.abhikarma_rajani.entity.Fees;
import com.abhikarma_rajani.repository.FeesRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class FeesService
{	
	@Autowired
	private FeesRepository feesRepository;
	
	// Total Fees Count:
	public Double totalFeeCount()
	{
		Double sumFees = feesRepository.sumFees();
		return sumFees;
	}

	// (02)
	public Fees addFees(Fees fees)
	{
		return feesRepository.save(fees);
	}

	// (*)
	public boolean checkAdmissionId(long admissionId)
	{
		return feesRepository.existsByAdmissionId(admissionId);
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
	public List<Fees> feesListAll(String keyword)
	{
		if(keyword != null)
		{
			return feesRepository.search(keyword);
		}
		else
		{
			return(List<Fees>) feesRepository.findAll();
		}	
	}

	// (04)
	public void deleteFeesReordWithId(long id)
	{
		feesRepository.deleteById(id);
		
	}

	// (05)
	public Fees updateFeesWithId(long id)
	{
		Optional<Fees> temp = feesRepository.findById(id);
		Fees fees = temp.get();
		return fees;
	}

	// (*)
	public void editAndSave(Fees fees)
	{
		feesRepository.save(fees);
		
	}
	
	// (07) Business logic for EXCEL REPORT GENERATION (FOR ALL):
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException
	{
		List<Fees> findAllList = feesRepository.findAll();
		
		
		/* Apache POI Components: 
		 * WorkBook
		 * Sheet
		 * Row
		 * Cell
	   */
		
		//WorkBook Creation:
		HSSFWorkbook hssfWorkbook= new HSSFWorkbook();
		
		//Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("Fees Report");
		
		//Row Creation:
		// 1. Header Row
		HSSFRow row = sheet.createRow(0);
		
		//Cell Creation:
		// 1. Cell Row
		row.createCell(0).setCellValue("ADMISSION ID");
		row.createCell(1).setCellValue("STUDENT NAME");
		row.createCell(2).setCellValue("CLASS NAME");
		row.createCell(3).setCellValue("GENDER");
		row.createCell(4).setCellValue("FEES TYPE");
		row.createCell(5).setCellValue("FEES AMOUNT");
		row.createCell(6).setCellValue("PAID AMOUNT");
		row.createCell(7).setCellValue("REMAINING AMOUNT");
		row.createCell(8).setCellValue("FEES STATUS");
		row.createCell(9).setCellValue("FEES PAID DATE");
		
		int dataRowIndex= 1; 
		
		for(Fees fees : findAllList)
		{
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(fees.getAdmissionId());
			dataRow.createCell(1).setCellValue(fees.getStudentName());
			dataRow.createCell(2).setCellValue(fees.getClassName());
			dataRow.createCell(3).setCellValue(fees.getGender());
			dataRow.createCell(4).setCellValue(fees.getFeeType());
			dataRow.createCell(5).setCellValue(fees.getFeeAmount());
			dataRow.createCell(6).setCellValue(fees.getPaidAmount());
			dataRow.createCell(7).setCellValue(fees.getRemainingAmount());
			dataRow.createCell(8).setCellValue(fees.getFeesStatus());
			dataRow.createCell(9).setCellValue(fees.getPaidDate());
			
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
		Optional<Fees> findById = feesRepository.findById(id);
		Fees fees = findById.get();
		
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
		row.createCell(0).setCellValue("ADMISSION ID");
		row.createCell(1).setCellValue("STUDENT NAME");
		row.createCell(2).setCellValue("CLASS NAME");
		row.createCell(3).setCellValue("GENDER");
		row.createCell(4).setCellValue("FEES TYPE");
		row.createCell(5).setCellValue("FEEs AMOUNT");
		row.createCell(6).setCellValue("PAID AMOUNT");
		row.createCell(7).setCellValue("REMAINING AMOUNT");
		row.createCell(8).setCellValue("FEES STATUS");
		row.createCell(9).setCellValue("FEES PAID DATE");
	
		
		int dataRowIndex= 1; 
	
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(fees.getAdmissionId());
			dataRow.createCell(1).setCellValue(fees.getStudentName());
			dataRow.createCell(2).setCellValue(fees.getClassName());
			dataRow.createCell(3).setCellValue(fees.getGender());
			dataRow.createCell(4).setCellValue(fees.getFeeType());
			dataRow.createCell(5).setCellValue(fees.getFeeAmount());
			dataRow.createCell(6).setCellValue(fees.getPaidAmount());
			dataRow.createCell(7).setCellValue(fees.getRemainingAmount());
			dataRow.createCell(8).setCellValue(fees.getFeesStatus());
			dataRow.createCell(9).setCellValue(fees.getPaidDate());
			
			dataRowIndex ++;
			
		
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		outputStream.close();
	}
	
	// (08) For all PDF...!
	public List<Fees> listAll()
	{
		List<Fees> findAll = feesRepository.findAll();
		return findAll;
	}
	
	// (09) For particular PDF...!!
	public Fees printPdfWithId(long id)
	{
		Optional<Fees> temp = feesRepository.findById(id);
		Fees particularfees = temp.get();
		return particularfees;	
	}
	
}
