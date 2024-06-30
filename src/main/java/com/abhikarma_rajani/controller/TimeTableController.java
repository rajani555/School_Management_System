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
import com.abhikarma_rajani.entity.TimeTable;
import com.abhikarma_rajani.service.TimeTableService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Times")
public class TimeTableController
{
	@Autowired
	private TimeTableService timeTableService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the TimeTable form !
	@GetMapping("/addTimeTable")
	public String loadTimeTableForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		TimeTable timeTable= new TimeTable();
		model.addAttribute("timeTable", timeTable);
		return "add-time-table";
	}
		
	//(02) Save TimeTable details !
	@PostMapping("/saveTimeTable")
	public String saveTimeTable(@ModelAttribute("timeTable") TimeTable timeTable, HttpSession session)
	{
		timeTableService.addTimeTable(timeTable);	
		session.setAttribute("msg", "TimeTable added successfully in records...!!");
		return "redirect:/Times/TimeTableDetails";
	}
	
	//(03) Fetching all the TimeTable details from database with search option!!
	@GetMapping ("/TimeTableDetails")
	public String TimeTableList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<TimeTable> timeTableList = timeTableService.timeTableListAll(keyword);
		model.addAttribute("timeTableList", timeTableList);
		model.addAttribute("keyword", keyword);
		return "time-table";
	}
	
	//(04) Delete TimeTable by id !
	@GetMapping("/DeleteTimeTableById/{id}")
	public String deleteTimeTable(@PathVariable(value = "id") long id, HttpSession session)
	{
		timeTableService.deletTimeTableWithId(id);
		session.setAttribute("msgd", "TimeTable deleted successfully...!!");
		return "redirect:/Times/TimeTableDetails";
	}
	
	//(04) Update TimeTable by id !
	@GetMapping("/UpdateTimeTableById/{id}")
	public String updateTimeTable(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		TimeTable timeTable = timeTableService.updateTimeTableWithId(id);
		model.addAttribute("timeTable", timeTable);
		return "edit-time-table";
	}
	
	//(04) Save after update the TimeTable !
	@PostMapping("/UpdateTimeTable")
	public String saveAfterUpdate(@ModelAttribute("timeTable") TimeTable timeTable, HttpSession session)
	{
		timeTableService.saveAfterUpdateTimeTable(timeTable);
		session.setAttribute("msgu", "TimeTable updated successfully...!!");
		return "redirect:/Times/TimeTableDetails";
	}
}
