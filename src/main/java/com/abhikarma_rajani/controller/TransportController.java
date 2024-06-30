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
import com.abhikarma_rajani.entity.Transport;
import com.abhikarma_rajani.service.TransportService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Transport")
public class TransportController
{
	@Autowired
	private TransportService transportService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the transport form !
	@GetMapping("/addTransport")
	public String loadTransportForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Transport transport= new Transport();
		model.addAttribute("transport", transport);
		return "add-transport";
	}
	
	//(02) Save transport details !
	@PostMapping("/saveTransport")
	public String saveTransport(@ModelAttribute("transport") Transport transport, HttpSession session)
	{
		boolean checkRouteName = transportService.checkRouteName(transport.getRouteName());
		if(checkRouteName)
		{
			session.setAttribute("msg", "This route name is already in records...!!");
		}
		else
		{
			Transport addTransport = transportService.addTransport(transport);
			if(addTransport != null)
			{
				session.setAttribute("msg", "Route added successfully in records...!!");
			}
			else
			{
				session.setAttribute("msg", "Something worng in server...!!");
			}
		}
		
		return "redirect:/Transport/TransportDetails";
	}
	
	//(03) Fetching all the transport details from database with search option!!
	@GetMapping ("/TransportDetails")
	public String transportList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Transport> transportlist = transportService.transportListAll(keyword);
		model.addAttribute("transportlist", transportlist);
		model.addAttribute("keyword", keyword);
		return "transport";
	}
	
	//(04) Delete Transport by id !
	@GetMapping("/DeleteTransportById/{id}")
	public String deleteTransport(@PathVariable(value = "id") long id, HttpSession session)
	{
		transportService.deletTransportWithId(id);
		session.setAttribute("msgd", "Route deleted successfully...!!");
		return "redirect:/Transport/TransportDetails";
	}
	
	//(04) Update Transport by id !
	@GetMapping("/UpdateTransportById/{id}")
	public String updateTransport(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Transport transport = transportService.updateTransportWithId(id);
		model.addAttribute("transport", transport);
		return "edit-transport";
	}
	
	//(04) Save after update the transport !
	@PostMapping("/UpdateTransport")
	public String saveAfterUpdate(@ModelAttribute("library") Transport Transport, HttpSession session)
	{
		transportService.saveAfterUpdateTransport(Transport);
		session.setAttribute("msgu", "Route updated successfully...!!");
		return "redirect:/Transport/TransportDetails";
	}
}
