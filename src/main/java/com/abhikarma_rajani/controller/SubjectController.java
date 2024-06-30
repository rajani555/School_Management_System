package com.abhikarma_rajani.controller;

import java.io.IOException;
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

import com.abhikarma_rajani.entity.Subject;
import com.abhikarma_rajani.service.SubjectService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class SubjectController
{
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(07) load the add form with subject object !!
	@GetMapping ("/add-subject")
	public String subjectForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Subject subject= new Subject();
		
		model.addAttribute("subject", subject);
		return "add-subject";
	}
	
	@PostMapping ("/saveSubject")
	public String addSubject(@ModelAttribute ("subject") Subject subject, HttpSession session)
	{
		boolean checkSubjectId = subjectService.checkSubjectId(subject.getSubjectId());
		if(checkSubjectId)
		{
			session.setAttribute("msg", "Subject Id already exsits...!!");
			//System.out.println("Subject Id is already exists...!!");
		}
		else
		{
			Subject saveSubject = subjectService.saveSubject(subject);
			if(saveSubject !=null)
			{
				session.setAttribute("msg", "Subject Registered successfully...!!");
				//System.out.println("Subject Registered successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Something wrong in server...!!");
				//System.out.println("Something wrong in server...!!");
			}
		}
		return "redirect:/add-subject";
	}
	
	
	// (10) fetching all the subject from database with search option!!
	@GetMapping ("/subjectslist")
	public String subjectList(Model model, @Param("keyword") String keyword, @Param("name") String name, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Subject> subjectlist = subjectService.listAll(keyword);
		
		model.addAttribute("subjectlist", subjectlist);
		model.addAttribute("keyword", keyword);
		
		return "subjects";
	}
	
	@GetMapping ("/DeleteSubjectById/{id}")
	public String deleteSubjectById(@PathVariable (value="id") long id, HttpSession session)
	{
		subjectService.deleteSubjectWithId(id);
		session.setAttribute("msgd", "Subject deleted successfully...!!");
		return "redirect:/subjectslist";
	}
	
	@GetMapping ("/UpdateSubjectById/{id}")
	public String updateSubjectById(@PathVariable (value="id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Subject subject = subjectService.updateSubject(id);
		model.addAttribute("subject", subject);
		return "edit-subject";
	}
	
	@PostMapping ("/UpdateSubject")
	public String saveSubject(@ModelAttribute ("subject") Subject subject, HttpSession session)
	{
		subjectService.updateSubject(subject);
		session.setAttribute("msgu", "Subject updated successfully...!!");
		return "redirect:/subjectslist";
	}
	
	// (14) Print the Excel Report!!
	@GetMapping ("/SubjectExcelReport")
	public void generateExcelReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=subject.xls";
		
		response.setHeader(headerKey, headerValue);
		
		subjectService.generateExcel(response);
		
	}
}
