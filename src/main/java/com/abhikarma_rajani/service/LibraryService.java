package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.entity.Library;
import com.abhikarma_rajani.repository.LibraryRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LibraryService
{
	@Autowired
	private LibraryRepository libraryRepository;
	
	// (01)
	public Library addExam(Library library)
	{
		Library save = libraryRepository.save(library);
		return save;
	}
	
	// (*)
	public boolean checkBook(String bookId)
	{
		boolean existsBybookId = libraryRepository.existsBybookId(bookId);
		return existsBybookId;
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
	public List<Library> libraryListAll(String keyword)
	{
		if(keyword != null)
		{
			return libraryRepository.search(keyword);
		}
		else
		{
			return(List<Library>) libraryRepository.findAll();
		}	
	}

	// (03)
	public void deletLibraryWithId(long id)
	{
		libraryRepository.deleteById(id);
	}

	// (04)
	public Library updateLibraryWithId(long id)
	{
		Optional<Library> temp = libraryRepository.findById(id);
		Library library = temp.get();
		return library;
	}

	// (05)
	public void saveAfterUpdateExam(Library library)
	{
		libraryRepository.save(library);
	}

}
