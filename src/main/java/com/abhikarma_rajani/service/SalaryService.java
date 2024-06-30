package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.entity.Salary;
import com.abhikarma_rajani.repository.SalaryRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class SalaryService
{
	@Autowired
	private SalaryRepository salaryRepository;
	
	// (02)
	public Salary addSalary(Salary salary)
	{
		return salaryRepository.save(salary);
	}
	
	// (*)
	public boolean checkStaffId(String staffId)
	{
		return salaryRepository.existsByStaffId(staffId);
	}
	
	// (*)
	public void removeSessionMessage()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg"); 
	}
	
	// (*)
	public void removeSessionMessage2()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msgd"); 
	}
	
	// (*)
	public void removeSessionMessage3()
	{
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msgu"); 
	}

	// (03)
	public List<Salary> salaryListAll(String keyword)
	{
		if(keyword != null)
		{
			return salaryRepository.search(keyword);
		}
		else
		{
			return(List<Salary>) salaryRepository.findAll();
		}	
	}

	// (04)
	public void deleteSalryWithId(long id)
	{
		salaryRepository.deleteById(id);
		
	}

	// (05)
	public Salary updateSalaryWithId(long id)
	{
		Optional<Salary> temp = salaryRepository.findById(id);
		Salary salary = temp.get();
		return salary;
		
	}

	// (06)
	public void addSalaryAfterUpdate(Salary salary)
	{
		salaryRepository.save(salary);
		
	}
	
}
