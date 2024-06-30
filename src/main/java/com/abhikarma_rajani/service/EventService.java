package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Event;
import com.abhikarma_rajani.repository.EventRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EventService 
{
	@Autowired
	private EventRepository eventRepository;
	
	// (01)
	public void addEvent(Event event)
	{
		eventRepository.save(event);
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
	public List<Event> eventListAll(String keyword)
	{
		
		if(keyword != null)
		{
			return eventRepository.search(keyword);
		}
		else
		{
			return(List<Event>) eventRepository.findAll();
		}	
	}

	// (03)
	public void deletEventWithId(long id)
	{
		eventRepository.deleteById(id);
		
	}

	// (04)
	public Event updateEventWithId(long id)
	{
		Optional<Event> temp = eventRepository.findById(id);
		Event event = temp.get();
		return event;
	}

	// (05)
	public void saveAfterUpdateholiday(Event event)
	{
		eventRepository.save(event);
	}

}
