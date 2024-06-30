package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Holiday;
import com.abhikarma_rajani.repository.HolidayRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class HolidayService
{
	@Autowired
	private HolidayRepository holidayRepository;

	// (01)
	public void addHoliday(Holiday holiday)
	{
		holidayRepository.save(holiday);
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
	public List<Holiday> holidayListAll(String keyword)
	{
		
		if(keyword != null)
		{
			return holidayRepository.search(keyword);
		}
		else
		{
			return(List<Holiday>) holidayRepository.findAll();
		}	
	}

	// (03)
	public void deletHolidayWithId(long id)
	{
		holidayRepository.deleteById(id);
	}

	// (04)
	public Holiday updateHolidayWithId(long id)
	{
		Optional<Holiday> temp = holidayRepository.findById(id);
		Holiday holiday = temp.get();
		return holiday;
	}

	// (05)
	public void saveAfterUpdateholiday(Holiday holiday)
	{
		holidayRepository.save(holiday);
	}
	
}
