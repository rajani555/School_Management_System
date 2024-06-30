package com.abhikarma_rajani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.abhikarma_rajani.entity.Event;
import com.abhikarma_rajani.service.EventService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Events")
public class EventController
{
	@Autowired
	private EventService eventService;
	
	//(01) Load the Event form !
	@GetMapping("/addEvent")
	public String loadEventForm(Model model)
	{
		Event event= new Event();
		model.addAttribute("event", event);
		return "add-events";
	}
		
	//(02) Save Event details !
	@PostMapping("/saveEvent")
	public String saveEvent(@ModelAttribute("event") Event event, HttpSession session)
	{
		eventService.addEvent(event);	
		session.setAttribute("msg", "Event added successfully in records...!!");
		return "redirect:/Events/EventDetails";
	}
	
	//(03) Fetching all the Event details from database with search option!!
	@GetMapping ("/EventDetails")
	public String EventList(Model model, @Param("keyword") String keyword)
	{
		List<Event> eventlist = eventService.eventListAll(keyword);
		model.addAttribute("eventlist", eventlist);
		model.addAttribute("keyword", keyword);
		return "event";
	}
	
	//(04) Delete Event by id !
	@GetMapping("/DeleteEventById/{id}")
	public String deleteEvent(@PathVariable(value = "id") long id, HttpSession session)
	{
		eventService.deletEventWithId(id);
		session.setAttribute("msgd", "Event deleted successfully...!!");
		return "redirect:/Events/EventDetails";
	}
	
	//(04) Update Event by id !
	@GetMapping("/UpdateEventById/{id}")
	public String updateEvent(@PathVariable(value = "id") long id, Model model)
	{
		Event event = eventService.updateEventWithId(id);
		model.addAttribute("event", event);
		return "edit-event";
	}
	
	//(04) Save after update the Event !
	@PostMapping("/UpdateEvent")
	public String saveAfterUpdate(@ModelAttribute("event") Event event, HttpSession session)
	{
		eventService.saveAfterUpdateholiday(event);
		session.setAttribute("msgu", "Event updated successfully...!!");
		return "redirect:/Events/EventDetails";
	}
}
