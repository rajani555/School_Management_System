package com.abhikarma_rajani.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.abhikarma_rajani.entity.Teacher;
import com.abhikarma_rajani.service.TeacherService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class TeacherController 
{
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) load the add form with teacher object !!
	@GetMapping ("/add-teacher")
	public String addTeacher(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Teacher teacher= new Teacher();
		model.addAttribute("teacher", teacher);
		return "add-teacher";
	}
	
	/*
	//(02) Save the teacher into DB WITHOUT any condition!!
	// #Image Variable uploadDir
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/teacherimages";
	 
	@PostMapping ("/saveTeacher")
	public String saveTeacher(@ModelAttribute("teacher") @RequestBody Teacher teacher,
							  @RequestParam("teacherImage") MultipartFile file,
							  @RequestParam("imgName") String imgName) throws IOException
	{
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());	
		}
		else
		{
			imageUUID = imgName;
		}
		teacher.setImageName(imageUUID);
		
		
		teacherService.addTeacher(teacher);
		return "redirect:/teacherlist";
	}
	*/

	//(03) save the teacher into DB WITH condition !!
	// #Image Variable uploadDir
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/teacherimages";
	
	@PostMapping ("/saveTeacher")
	public String saveTeacherWithCondition(@ModelAttribute("teacher") @RequestBody Teacher teacher, 
										   @RequestParam("teacherImage") MultipartFile file,
			                               @RequestParam("imgName") String imgName,
			                               HttpSession session) throws IOException
	{
		boolean checkTeacherId = teacherService.checkTeacherId(teacher.getTeacherId());
		if(checkTeacherId)
		{
			session.setAttribute("msg", "Teacher Id already exists...!!");
			//System.out.println("Teacher Id already exits...!!");
		}
		else
		{
			String imageUUID;
			if(!file.isEmpty())
			{
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
				Files.write(fileNameAndPath, file.getBytes());	
			}
			else
			{
				imageUUID = imgName;
			}
			teacher.setImageName(imageUUID);
			
			Teacher addTeacherWithCondition = teacherService.addTeacherWithCondition(teacher);
			if(addTeacherWithCondition != null)
			{
				session.setAttribute("msg", "Teacher Registered Successfully...!!");
				//System.out.println("Teacher Registered Successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Something error into server...!!");
				//System.out.println("Something error into server...!!");
			}
		}
		return "redirect:/teacherlist";
	}
	
	// (04) Fetching all the teacher from database with search option!!
	@GetMapping ("/teacherlist")
	public String studentList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Teacher> teacherlist = teacherService.listAll(keyword);
		
		model.addAttribute("teacherlist", teacherlist);
		model.addAttribute("keyword", keyword);
		
		return "teachers";
	}
	
	//(05) Delete the Teacher by id from database!!
	@GetMapping ("/DeleteTeacherById/{id}")
	public String deleteTeacherById(@PathVariable (value= "id") long id, HttpSession session)
	{
		teacherService.deleteStudentWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/teacherlist";
	}
	
	//(06) Update Teacher by id into database!!
	@GetMapping ("/UpdateTeacherById/{id}")
	public String updateTeacherById(@PathVariable (value= "id") long id, Model model, Principal principal)
	{	
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		model.addAttribute("teacher", teacherService.updateTeacherWithId(id));
		
		return "edit-teacher";
	}
	
	//(07) update the teacher into DB !!
	// #Image Variable uploadDir
	// public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/teacherimages";
	@PostMapping ("/updateTeacher")
	public String saveTeacher(@ModelAttribute("teacher") @RequestBody Teacher teacher,
							  @RequestParam("teacherImage") MultipartFile file,
							  @RequestParam("imgName") String imgName,
							  HttpSession session) throws IOException
	{
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());	
		}
		else
		{
			imageUUID = imgName;
		}
		teacher.setImageName(imageUUID);
		
		teacherService.editTeacher(teacher);
		session.setAttribute("msgu", "Record Updated Successfully...!!");
		return "redirect:/teacherlist";
	}
		
	// (08) Print the Excel Report!!
	@GetMapping ("/TeacherExcelReport")
	public void generateExcelReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=teacher.xls";
		
		response.setHeader(headerKey, headerValue);
		
		teacherService.generateExcel(response);
	}
	
}
