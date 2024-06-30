package com.abhikarma_rajani.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.abhikarma_rajani.entity.Fees;
import com.abhikarma_rajani.pdf.FeesPdfExporterById;
import com.abhikarma_rajani.pdf.FeesPdfExporter;
import com.abhikarma_rajani.service.FeesService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class FeesController
{
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// (01) Load the Fees form!
	@GetMapping("/addFees")
	public String loadFeeForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Fees fees = new Fees();
		model.addAttribute("fees", fees);
		return "add-fees-collection";
	}
	
	// (02) Save the fees amount into DB with condition !!
	@PostMapping("/saveFees")
	public String saveFees(@ModelAttribute("fees") Fees fees, HttpSession session)
	{	
		boolean checkAdmissionId = feesService.checkAdmissionId(fees.getAdmissionId());
		if(checkAdmissionId)
		{
			session.setAttribute("msg", "Given AdmissionId already exists...!!");
			//System.out.println("AdmissionId already exist...!!");
		}
		else
		{
			Fees addFeesWithCondition = feesService.addFees(fees);
			if(addFeesWithCondition != null)
			{
				session.setAttribute("msg", "Fees added Successfully...!!");
				//System.out.println("Student Register Successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Somthing went rong...!!");
				//System.out.println("Something error in server...!!");
			}
		}
		return "redirect:/fees-collections";
	}
	
	//(03) Fetching all the fees details from database with search option!!
	@GetMapping ("/fees-collections")
	public String feesList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Fees> feeslist = feesService.feesListAll(keyword);
		
		model.addAttribute("feeslist", feeslist);
		model.addAttribute("keyword", keyword);
		return "fees-collections";
	}
	
	// (04) Delete particulars fees record from DB...!! 
	@GetMapping ("/DeleteFeesById/{id}")
	public String deleteFeesRecordById(@PathVariable(value = "id") long id, HttpSession session)
	{
		feesService.deleteFeesReordWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/fees-collections";
	}
	
	// (05) Update Fees by id...!!
	@GetMapping ("/UpdateFeesById/{id}")
	public String updateFeesById(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Fees fees = feesService.updateFeesWithId(id);
		model.addAttribute("fees", fees);
		return "edit-fees-collection";
		
	}
	
	// (*) Edit and again Save fees into DB...!!
	@PostMapping ("/UpdateFees")
	public String editAndSaveFees(@ModelAttribute("fees") Fees fees, HttpSession session)
	{
		feesService.editAndSave(fees);
		session.setAttribute("msgu", "Record updated successfully...!!");
		return "redirect:/fees-collections";
		
	}
	
	//(07) Print excel report for all...!!
	@GetMapping ("/PrintExcelReportFees")
	public void generateExcelReportFees(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=fees.xls";
		
		response.setHeader(headerKey, headerValue);
		
		feesService.generateExcel(response);
	}
	
	//(14) Print the Excel Report for particular!!
	@GetMapping ("/ExcelReportByIdFees/{id}")
	public void generateExcelReportById(@PathVariable(value = "id") long id, HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename= fees.xls";
		
		response.setHeader(headerKey, headerValue);
		
		feesService.generateExcelById(id, response);
	}
	
	//(08) Print PDF Report for all...!!
	@GetMapping ("/PrintPdfReportFees")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException
	{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date());
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename= fees_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<Fees> listFees = feesService.listAll();
		
		FeesPdfExporter exporter= new FeesPdfExporter(listFees);
		exporter.export(response);
	}
	

	//(09) Print PDF Report for particular...!!
	@GetMapping ("/PrintPdfReportByIdFees/{id}")
	public void exportToPDFById(@PathVariable(value = "id") long id,  HttpServletResponse response) throws DocumentException, IOException
	{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date());
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename= fees_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		Fees printPdfWithId = feesService.printPdfWithId(id);
		
		FeesPdfExporterById exporter= new FeesPdfExporterById(printPdfWithId);
		exporter.export(response);
	}
	
}
