package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.abhikarma_rajani.entity.Transport;
import com.abhikarma_rajani.repository.TransportRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class TransportService
{
	@Autowired
	private TransportRepository transportRepository;
	
	// (01)
	public Transport addTransport(Transport transport)
	{
		Transport saveTransport = transportRepository.save(transport);
		return saveTransport;
	}
	
	// (*)
	public boolean checkRouteName(String routeName)
	{
		boolean existsByrouteName = transportRepository.existsByrouteName(routeName);
		return existsByrouteName;
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
	public List<Transport> transportListAll(String keyword)
	{
		if(keyword != null)
		{
			return transportRepository.search(keyword);
		}
		else
		{
			return(List<Transport>) transportRepository.findAll();
		}	
	}

	// (03)
	public void deletTransportWithId(long id)
	{
		transportRepository.deleteById(id);
	}

	// (04)
	public Transport updateTransportWithId(long id)
	{
		Optional<Transport> temp = transportRepository.findById(id);
		Transport transport = temp.get();
		return transport;
	}

	// (05)
	public void saveAfterUpdateTransport(Transport transport)
	{
		transportRepository.save(transport);
	}
}
