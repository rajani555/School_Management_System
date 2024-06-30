package com.abhikarma_rajani.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.abhikarma_rajani.entity.Room;
import com.abhikarma_rajani.service.RoomService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Hostel")
public class RoomController
{
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the Room form !
	@GetMapping("/addRoom")
	public String loadRoomForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Room room= new Room();
		model.addAttribute("room", room);
		return "add-room";
	}
		
	//(02) Save Room details !
	@PostMapping("/saveRoom")
	public String saveRoom(@ModelAttribute("room") Room room, HttpSession session)
	{
		roomService.addRoom(room);	
		session.setAttribute("msg", "Room added successfully in records...!!");
		return "redirect:/Hostel/RoomDetails";
	}
	
	//(03) Fetching all the Room details from database with search option!!
	@GetMapping ("/RoomDetails")
	public String RoomList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Room> roomList = roomService.roomListAll(keyword);
		model.addAttribute("roomList", roomList);
		model.addAttribute("keyword", keyword);
		return "hostel";
	}
	
	//(04) Delete Room by id !
	@GetMapping("/DeleteRoomById/{id}")
	public String deleteRoom(@PathVariable(value = "id") long id, HttpSession session)
	{
		roomService.deleteRoomWithId(id);
		session.setAttribute("msgd", "Room deleted successfully...!!");
		return "redirect:/Hostel/RoomDetails";
	}
	
	//(04) Update Room by id !
	@GetMapping("/UpdateRoomById/{id}")
	public String updateRoom(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Room room = roomService.updateRoomWithId(id);
		model.addAttribute("room", room);
		return "edit-room";
	}
	
	//(04) Save after update the Room !
	@PostMapping("/UpdateRoom")
	public String saveAfterUpdate(@ModelAttribute("room") Room room, HttpSession session)
	{
		roomService.saveAfterUpdateRoom(room);
		session.setAttribute("msgu", "Room updated successfully...!!");
		return "redirect:/Hostel/RoomDetails";
	}
}
