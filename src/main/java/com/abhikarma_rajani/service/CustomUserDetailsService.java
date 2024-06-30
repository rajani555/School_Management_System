package com.abhikarma_rajani.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhikarma_rajani.entity.Admin;
import com.abhikarma_rajani.repository.AdminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	//Import Repository here !
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		Admin admin= adminRepository.findByUsername(username);
		if(admin==null)
		{
			throw new UsernameNotFoundException("Username or Password not found");
		}
		return new CustomUserDetails(
				admin.getUsername(),
				admin.getPassword(),
				authorities(),
				admin.getFullname());
	}
	
	public Collection<? extends GrantedAuthority> authorities()
	{
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
