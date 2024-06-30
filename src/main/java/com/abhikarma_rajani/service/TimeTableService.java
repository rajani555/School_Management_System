package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.TimeTable;
import com.abhikarma_rajani.repository.TimeTableRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class TimeTableService
{
	@Autowired
	private TimeTableRepository timeTableRepository;

	// (01)
	public void addTimeTable(TimeTable timeTable)
	{
		timeTableRepository.save(timeTable);
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
	
	// (02)
	public List<TimeTable> timeTableListAll(String keyword)
	{
		
		if(keyword != null)
		{
			return timeTableRepository.search(keyword);
		}
		else
		{
			return(List<TimeTable>) timeTableRepository.findAll();
		}	
	}

	// (03)
	public void deletTimeTableWithId(long id) 
	{
		timeTableRepository.deleteById(id);
	}

	// (04)
	public TimeTable updateTimeTableWithId(long id)
	{
		Optional<TimeTable> temp = timeTableRepository.findById(id);
		TimeTable timeTable = temp.get();
		return timeTable;
	}

	// (05)
	public void saveAfterUpdateTimeTable(TimeTable timeTable)
	{
		timeTableRepository.save(timeTable);
	}

}
