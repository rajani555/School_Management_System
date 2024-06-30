package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Room;
import com.abhikarma_rajani.repository.RoomRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class RoomService
{
	@Autowired
	private RoomRepository roomRepository;

	// (01)
	public void addRoom(Room room)
	{
		roomRepository.save(room);	
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
	public List<Room> roomListAll(String keyword)
	{
		
		if(keyword != null)
		{
			return roomRepository.search(keyword);
		}
		else
		{
			return(List<Room>) roomRepository.findAll();
		}	
	}

	// (03)
	public void deleteRoomWithId(long id)
	{
		roomRepository.deleteById(id);	
	}

	// (04)
	public Room updateRoomWithId(long id) 
	{
		Optional<Room> temp = roomRepository.findById(id);
		Room room = temp.get();
		return room;
	}

	// (05)
	public void saveAfterUpdateRoom(Room room)
	{
		roomRepository.save(room);
	}
}
