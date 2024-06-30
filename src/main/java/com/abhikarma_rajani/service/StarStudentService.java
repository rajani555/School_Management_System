package com.abhikarma_rajani.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.entity.StarStudent;
import com.abhikarma_rajani.repository.StarStudentRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class StarStudentService 
{
	@Autowired
	private StarStudentRepository starStudentRepository;

	public void saveStarStudent(StarStudent starStudent)
	{
		starStudentRepository.save(starStudent);	
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

	public List<StarStudent> findAllStar() 
	{
		List<StarStudent> findAll = starStudentRepository.findAll();
		return findAll;
	}

	public void deleteStar(Long id)
	{
		starStudentRepository.deleteById(id);
	}
}
