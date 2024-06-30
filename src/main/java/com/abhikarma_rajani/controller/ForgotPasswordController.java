package com.abhikarma_rajani.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.abhikarma_rajani.entity.Admin;
import com.abhikarma_rajani.entity.ForgotPasswordToken;
import com.abhikarma_rajani.repository.ForgotPasswordRepository;
import com.abhikarma_rajani.service.AdminService;
import com.abhikarma_rajani.service.ForgotTokenService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController 
{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ForgotTokenService forgotTokenService;
	
	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/password-request")
	public String passwordRequest()
	{
		return "password-request";
	}
	
	@PostMapping("/password-request")
	public String savePasswordRequest(@RequestParam("username") String username, Model model)
	{
		Admin admin = adminService.findByUsername(username);
		if(admin==null) 
		{
			model.addAttribute("error", "This Email is not registerd");
			return "password-request";
		}
		ForgotPasswordToken forgotPasswordToken= new ForgotPasswordToken();
		forgotPasswordToken.setExpireTime(forgotTokenService.expireTimeRange());
		forgotPasswordToken.setToken(forgotTokenService.generateToken());
		forgotPasswordToken.setAdmin(admin);
		forgotPasswordToken.setUsed(false);
		
		forgotPasswordRepository.save(forgotPasswordToken);
		
		String emailLink= "http://localhost:8081/reset-password?token="+forgotPasswordToken.getToken();
		
		try
		{
			forgotTokenService.sendEmail(admin.getUsername(), "Password Reset Link", emailLink);
		} 
		catch (UnsupportedEncodingException | MessagingException e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error while sending email...");
			return "password-request";
		}
		return "redirect:/password-request?success";
	}
	
	@GetMapping("/reset-password")
	public String resetPassword(@Param(value="token") String token, Model model, HttpSession session)
	{
		session.setAttribute("token", token);
		ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
		return forgotTokenService.checkValidity(forgotPasswordToken, model);
	}
	
	@PostMapping("/reset-password")
	public String saveResetPassword(HttpServletRequest httpServletRequest, HttpSession session, Model model)
	{
		 String password= httpServletRequest.getParameter("password");
		 String token= (String) session.getAttribute("token");
		 
		 ForgotPasswordToken forgotPasswordToken= forgotPasswordRepository.findByToken(token);
		 Admin admin= forgotPasswordToken.getAdmin();
		 admin.setPassword(passwordEncoder.encode(password));
		 forgotPasswordToken.setUsed(true);
		 adminService.save(admin);
		 forgotPasswordRepository.save(forgotPasswordToken);
		 model.addAttribute("message", "You have successfully reset your password!");
		 return "reset-password";
	}
}
