package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Sport;
import com.abhikarma_rajani.repository.SportRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class SportService
{
	@Autowired
	private SportRepository sportRepository;

	// (01)
	public void addSport(Sport sport)
	{
		sportRepository.save(sport);
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
	public List<Sport> sportListAll(String keyword)
	{
		
		if(keyword != null)
		{
			return sportRepository.search(keyword);
		}
		else
		{
			return(List<Sport>) sportRepository.findAll();
		}	
	}

	// (03)
	public void deleteSportWithId(long id)
	{
		sportRepository.deleteById(id);
	}

	// (04)
	public Sport updateSportWithId(long id)
	{
		Optional<Sport> temp = sportRepository.findById(id);
		Sport sport = temp.get();
		return sport;
	}

	// (05)
	public void saveAfterUpdateSport(Sport sport)
	{
		sportRepository.save(sport);
	}
}
