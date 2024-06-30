package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.entity.Exam;
import com.abhikarma_rajani.repository.ExamRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ExamService
{
	@Autowired
	private ExamRepository examRepository;

	// (02)
	public Exam addExam(Exam exam)
	{
		Exam save = examRepository.save(exam);
		return save;
		
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
	public List<Exam> examListAll(String keyword)
	{
		if(keyword != null)
		{
			return examRepository.search(keyword);
		}
		else
		{
			return(List<Exam>) examRepository.findAll();
		}	
	}

	// (04)
	public void deletExamWithId(long id)
	{
		examRepository.deleteById(id);
		
	}

	// (05)
	public Exam updateExamWithId(long id)
	{
		Optional<Exam> temp = examRepository.findById(id);
		Exam exam = temp.get();
		return exam;
	}

	// (06)
	public void saveAfterUpdateExam(Exam exam)
	{
		examRepository.save(exam);
	}
}
