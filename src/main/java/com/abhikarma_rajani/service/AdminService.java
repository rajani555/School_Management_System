package com.abhikarma_rajani.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.dto.AdminDto;
import com.abhikarma_rajani.entity.Admin;
import com.abhikarma_rajani.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService 
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Admin findByUsername(String username)
	{
		return adminRepository.findByUsername(username);
	}
	
	// In case of password reset time !
	public Admin save(Admin admin)
	{
		return adminRepository.save(admin);
	}
	
	public Admin saveAdmin(AdminDto adminDto)
	{
		Admin admin= new Admin(adminDto.getUsername(), passwordEncoder.encode(adminDto.getPassword()), adminDto.getFullname());
		return adminRepository.save(admin);
	}
	
	// (*)
	public boolean checkUsername(String username)
	{
		boolean existsByUsername = adminRepository.existsByUsername(username);
		return existsByUsername;
	}
	
	// (*)
	public void removeSessionMessage()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg"); 
	}

}
