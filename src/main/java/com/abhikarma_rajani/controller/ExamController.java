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
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhikarma_rajani.entity.Exam;
import com.abhikarma_rajani.service.ExamService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Exam")
public class ExamController 
{
	@Autowired
	private ExamService examService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the exam form !
	@GetMapping("/addExam")
	public String loadExamForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Exam exam= new Exam();
		model.addAttribute("exam", exam);
		return "add-exam";
	}
	
	//(02) Save exam details !
	@PostMapping("/saveExam")
	public String saveExam(@ModelAttribute("exam") Exam exam, HttpSession session)
	{
		Exam addExam = examService.addExam(exam);
		if(addExam != null)
		{
			session.setAttribute("msg", "Record added successfully...!!");
		}
		else
		{
			session.setAttribute("msg", "Something worng in server...!!");
		}
		
		return "redirect:/Exam/examDetails";
	}
	
	//(03) Fetching all the exams details from database with search option!!
	@GetMapping ("/examDetails")
	public String examList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Exam> examlist = examService.examListAll(keyword);
		model.addAttribute("examlist", examlist);
		model.addAttribute("keyword", keyword);
		return "exam";
	}
	
	//(04) Delete Exam by id !
	@GetMapping("/DeleteExamById/{id}")
	public String deleteExam(@PathVariable(value = "id") long id, HttpSession session)
	{
		examService.deletExamWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/Exam/examDetails";
	}
	
	//(04) Update Exam by id !
	@GetMapping("/UpdateExamById/{id}")
	public String updateExam(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Exam exam = examService.updateExamWithId(id);
		model.addAttribute("exam", exam);
		return "edit-exam";
	}
	
	//(04) Save after update the exam !
	@PostMapping("/UpdateExam")
	public String saveAfterUpdate(@ModelAttribute("exam") Exam exam, HttpSession session)
	{
		examService.saveAfterUpdateExam(exam);
		session.setAttribute("msgu", "Record updated successfully...!!");
		return "redirect:/Exam/examDetails";
	}
}
