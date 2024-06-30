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
import com.abhikarma_rajani.entity.Sport;
import com.abhikarma_rajani.service.SportService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Sports")
public class SportController
{
	@Autowired
	private SportService sportService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the Sport form !
	@GetMapping("/addSport")
	public String loadSportForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Sport sport= new Sport();
		model.addAttribute("sport", sport);
		return "add-sports";
	}
		
	//(02) Save Sport details !
	@PostMapping("/saveSport")
	public String saveSport(@ModelAttribute("sport") Sport sport, HttpSession session)
	{
		sportService.addSport(sport);	
		session.setAttribute("msg", "Sport added successfully in records...!!");
		return "redirect:/Sports/SportDetails";
	}
	
	//(03) Fetching all the Sport details from database with search option!!
	@GetMapping ("/SportDetails")
	public String SportList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Sport> sportList = sportService.sportListAll(keyword);
		model.addAttribute("sportList", sportList);
		model.addAttribute("keyword", keyword);
		return "sports";
	}
	
	//(04) Delete Sport by id !
	@GetMapping("/DeleteSportById/{id}")
	public String deleteSport(@PathVariable(value = "id") long id, HttpSession session)
	{
		sportService.deleteSportWithId(id);
		session.setAttribute("msgd", "Sport deleted successfully...!!");
		return "redirect:/Sports/SportDetails";
	}
	
	//(04) Update Sport by id !
	@GetMapping("/UpdateSportById/{id}")
	public String updateSport(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Sport sport = sportService.updateSportWithId(id);
		model.addAttribute("sport", sport);
		return "edit-sports";
	}
	
	//(04) Save after update the Sport !
	@PostMapping("/UpdateSport")
	public String saveAfterUpdate(@ModelAttribute("sport") Sport sport, HttpSession session)
	{
		sportService.saveAfterUpdateSport(sport);
		session.setAttribute("msgu", "Sport updated successfully...!!");
		return "redirect:/Sports/SportDetails";
	}
}
