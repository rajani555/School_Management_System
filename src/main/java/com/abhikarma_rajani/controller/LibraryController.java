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
import com.abhikarma_rajani.entity.Library;
import com.abhikarma_rajani.service.LibraryService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Books")
public class LibraryController
{
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the books form !
	@GetMapping("/addLibrary")
	public String loadLibraryForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Library library= new Library();
		model.addAttribute("library", library);
		return "add-books";
	}
	
	//(02) Save books details !
	@PostMapping("/saveLibrary")
	public String saveLibrary(@ModelAttribute("library") Library library, HttpSession session)
	{
		boolean checkBook = libraryService.checkBook(library.getBookId());
		if(checkBook)
		{
			session.setAttribute("msg", "This book id is already in records...!!");
		}
		else
		{
			Library addlibrary = libraryService.addExam(library);
			if(addlibrary != null)
			{
				session.setAttribute("msg", "Books added successfully in records...!!");
			}
			else
			{
				session.setAttribute("msg", "Something worng in server...!!");
			}
		}
		
		return "redirect:/Books/libraryDetails";
	}
	
	//(03) Fetching all the books details from database with search option!!
	@GetMapping ("/libraryDetails")
	public String libraryList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Library> librarylist = libraryService.libraryListAll(keyword);
		model.addAttribute("librarylist", librarylist);
		model.addAttribute("keyword", keyword);
		return "library";
	}
	
	//(04) Delete Library by id !
	@GetMapping("/DeleteLibraryById/{id}")
	public String deleteLibrary(@PathVariable(value = "id") long id, HttpSession session)
	{
		libraryService.deletLibraryWithId(id);
		session.setAttribute("msgd", "Book deleted successfully...!!");
		return "redirect:/Books/libraryDetails";
	}
	
	//(04) Update Library by id !
	@GetMapping("/UpdateLibraryById/{id}")
	public String updateLibrary(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Library library = libraryService.updateLibraryWithId(id);
		model.addAttribute("library", library);
		return "edit-library";
	}
	
	//(04) Save after update the library !
	@PostMapping("/UpdateLibrary")
	public String saveAfterUpdate(@ModelAttribute("library") Library library, HttpSession session)
	{
		libraryService.saveAfterUpdateExam(library);
		session.setAttribute("msgu", "Book updated successfully...!!");
		return "redirect:/Books/libraryDetails";
	}
}
