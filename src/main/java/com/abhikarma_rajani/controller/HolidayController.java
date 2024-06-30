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
import com.abhikarma_rajani.entity.Holiday;
import com.abhikarma_rajani.service.HolidayService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Holidays")
public class HolidayController
{
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the Holiday form !
	@GetMapping("/addHoliday")
	public String loadHolidayForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Holiday holiday= new Holiday();
		model.addAttribute("holiday", holiday);
		return "add-holiday";
	}
	
	//(02) Save Holiday details !
	@PostMapping("/saveHoliday")
	public String saveHoliday(@ModelAttribute("holiday") Holiday holiday, HttpSession session)
	{
		holidayService.addHoliday(holiday);	
		session.setAttribute("msg", "Holiday added successfully in records...!!");
		return "redirect:/Holidays/HolidayDetails";
	}
	
	//(03) Fetching all the Holiday details from database with search option!!
	@GetMapping ("/HolidayDetails")
	public String holidaytList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Holiday> holidaytlist = holidayService.holidayListAll(keyword);
		model.addAttribute("holidaylist", holidaytlist);
		model.addAttribute("keyword", keyword);
		return "holiday";
	}
	
	//(04) Delete Transport by id !
	@GetMapping("/DeleteHolidayById/{id}")
	public String deleteHoliday(@PathVariable(value = "id") long id, HttpSession session)
	{
		holidayService.deletHolidayWithId(id);
		session.setAttribute("msgd", "Holiday deleted successfully...!!");
		return "redirect:/Holidays/HolidayDetails";
	}
	
	//(04) Update holiday by id !
	@GetMapping("/UpdateHolidayById/{id}")
	public String updateHoliday(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Holiday holiday = holidayService.updateHolidayWithId(id);
		model.addAttribute("holiday", holiday);
		return "edit-holiday";
	}
	
	//(04) Save after update the holiday !
	@PostMapping("/UpdateHoliday")
	public String saveAfterUpdate(@ModelAttribute("holiday") Holiday holiday, HttpSession session)
	{
		holidayService.saveAfterUpdateholiday(holiday);
		session.setAttribute("msgu", "Holiday updated successfully...!!");
		return "redirect:/Holidays/HolidayDetails";
	}
}
