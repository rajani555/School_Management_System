package com.abhikarma_rajani.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.abhikarma_rajani.dto.AdminDto;
import com.abhikarma_rajani.entity.Admin;
import com.abhikarma_rajani.service.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController
{
	@Autowired
	private AdminService adminService;
	
	public AdminController(AdminService adminService)
	{
		this.adminService = adminService;
	}
	
	@Autowired
	private UserDetailsService userDetailsService;

	// Load the log-in page !!
	@GetMapping("/login")
	public String login(Model model)
	{
		AdminDto adminDto= new AdminDto();
		model.addAttribute("admin", adminDto);
		return "login";
	}
	
	// Load the sign-up page !!
	@GetMapping("/register")
	public String signup(Model model)
	{
		AdminDto adminDto= new AdminDto();
		model.addAttribute("admin", adminDto);
		return "register";
	}
	
	// Save the register user !!
	@PostMapping("/register")
	public String saveAdmin(@ModelAttribute("admin") AdminDto adminDto, HttpSession session)
	{
		boolean checkUsername = adminService.checkUsername(adminDto.getUsername());
		if(checkUsername)
		{
			session.setAttribute("msg", "This username is already exsits...!!");
		}
		else
		{
			Admin saveAdmin = adminService.saveAdmin(adminDto);
			if(saveAdmin != null)
			{
				session.setAttribute("msg", "User registerd successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Something wrong in server...!!");
			}
		}
		
		return "redirect:/register";
	}
}
