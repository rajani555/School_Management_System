package com.abhikarma_rajani.controller;

import java.security.Principal;
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

import com.abhikarma_rajani.entity.Salary;
import com.abhikarma_rajani.service.SalaryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SalaryController
{
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the Add Salary form!!
	@GetMapping("/addSalary")
	public String loadAddSalary(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Salary salary= new Salary();
		model.addAttribute("salary", salary);
		return "add-salary";
	}
	
	//(02) Save salary into database!!
	@PostMapping("/add-salary")
	public String saveSalary(@ModelAttribute("salary") Salary salary, HttpSession session)
	{
		boolean checkStaffId = salaryService.checkStaffId(salary.getStaffId());
		if(checkStaffId)
		{
			session.setAttribute("msg", "Given staff ID is already in records...!!");
		}
		else
		{
			Salary addSalary = salaryService.addSalary(salary);
			if(addSalary != null)
			{
				session.setAttribute("msg", "Salary added successfully in records...!!");
			}
			else
			{
				session.setAttribute("msg", "Something worng in server...!!");
			}
		}
		return "redirect:/salary";
	}
	
	//(03) Fetching all the salary details from database with search option!!
	@GetMapping ("/salary")
	public String salaryList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Salary> salarylist = salaryService.salaryListAll(keyword);
		
		model.addAttribute("salarylist", salarylist);
		model.addAttribute("keyword", keyword);
		return "salary";
	}
	
	// (04) Delete salary by id !!
	@GetMapping("/DeleteSalaryById/{id}")
	public String deleteSalaryById(@PathVariable(value = "id") long id, HttpSession session)
	{
		salaryService.deleteSalryWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/salary";
	}
	
	//(05) Update salary by id !!
	@GetMapping("/UpdateSalaryById/{id}")
	public String updateSalaryById(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Salary salary = salaryService.updateSalaryWithId(id);
		model.addAttribute("salary", salary);
		return "edit-salary";
	}
	
	//(06) Save after update salary !!
	@PostMapping("/UpdateSalary")
	public String saveAfterUpdate(@ModelAttribute("salary") Salary salary, HttpSession session)
	{
		salaryService.addSalaryAfterUpdate(salary);
		session.setAttribute("msgu", "Record updated successfully...!!");
		return "redirect:/salary";
	}
}
