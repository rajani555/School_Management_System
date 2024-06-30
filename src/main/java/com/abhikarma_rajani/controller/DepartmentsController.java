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
import org.springframework.web.bind.annotation.RequestBody;

import com.abhikarma_rajani.entity.Departments;
import com.abhikarma_rajani.pdf.DepartmentPdfExporterById;
import com.abhikarma_rajani.pdf.DepartmentsPdfExporter;
import com.abhikarma_rajani.service.DepartmentsService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class DepartmentsController
{
	@Autowired
	private DepartmentsService departmentsService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	// (01) load the add form with departments object !!
	@GetMapping("/add-departments")
	public String adddepartments(Model model, Principal principal) 
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Departments departments = new Departments();

		model.addAttribute("departments", departments);
		
		return "add-department";
	}

	// (02) save the departments with the condition into DB !!
	@PostMapping("/saveDepartment")
	public String saveDepartmentsWithCondition(@ModelAttribute("departments") @RequestBody Departments departments, HttpSession session)
	{
		boolean checkDepartmentsId = departmentsService.checkDepartmentsId(departments.getDepartmentId());
		if (checkDepartmentsId)
		{
			session.setAttribute("msg", "Departments Id alreaddy exists...!!");
			// System.out.println("Departments Id already exists...!!");
		} 
		else
		{
			Departments addDepartmentsWithCondition = departmentsService.addDepartmentsWithCondition(departments);
			if (addDepartmentsWithCondition != null) {
				session.setAttribute("msg", "Departments Registerd successfully...!!");
				// System.out.println("Departments Registered successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Something error in server...!!");
				// System.out.println("Something error in server...!!");
			}
		}
		return "redirect:/departmentslist";
	}

	// (03) fetching all the departments from database with search option!!
	@GetMapping("/departmentslist")
	public String departmentstList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Departments> departmentslist = departmentsService.listAll(keyword);

		model.addAttribute("departmentslist", departmentslist);
		model.addAttribute("keyword", keyword);

		return "departments";
	}

	//(04) Delete Departments by id into database!!
	@GetMapping("/DeleteDepartmentsById/{id}")
	public String deleteDepartmentsById(@PathVariable(value = "id") Long id, HttpSession session)
	{
		departmentsService.deleteDepartments(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/departmentslist";
	}
	
	//(05) Update Departments by id into database!!
	@GetMapping ("/UpdateDepartmentsById/{id}")
	public String updateDepartments(@PathVariable (value= "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Departments departments = departmentsService.updateDepartmentsWithId(id);
		model.addAttribute("departments", departments);
		return "edit-department";
	}
	
	//(06) Save Department after update...!!
	@PostMapping ("/updateDepartment")
	public String saveDepartmentsAfterUpdate(@ModelAttribute("departments") Departments departments, HttpSession session)
	{
		departmentsService.saveAfterUpdate(departments);
		session.setAttribute("msgu", "Record updated successfully...!!");
		return "redirect:/departmentslist";
	}
	
	//(07) Print excel report for all...!!
	@GetMapping ("/PrintExcelReport")
	public void generateExcelReportDepartments(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=departments.xls";
		
		response.setHeader(headerKey, headerValue);
		
		departmentsService.generateExcel(response);
	}
	 
	//(08) Print PDF Report for all...!!
	@GetMapping ("/PrintPdfReport")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException
	{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date());
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename= departments_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<Departments> listDepartments = departmentsService.listAll();
		
		DepartmentsPdfExporter exporter= new DepartmentsPdfExporter(listDepartments);
		exporter.export(response);
	}
	
	//(09) Print PDF Report for particular...!!
	@GetMapping ("/PrintPdfReportById/{id}")
	public void exportToPDFById(@PathVariable(value = "id") long id,  HttpServletResponse response) throws DocumentException, IOException
	{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date());
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename= departments_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		Departments printPdfWithId = departmentsService.printPdfWithId(id);
		
		DepartmentPdfExporterById exporter= new DepartmentPdfExporterById(printPdfWithId);
		exporter.export(response);
	}
	
}
