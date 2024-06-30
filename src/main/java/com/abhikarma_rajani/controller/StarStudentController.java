package com.abhikarma_rajani.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.abhikarma_rajani.entity.StarStudent;
import com.abhikarma_rajani.service.StarStudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Star")
public class StarStudentController 
{
	@Autowired
	private StarStudentService starStudentService;
	
	// #Image Variable uploadDir
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/starstudentimages";
	@GetMapping("/addStarStudent")
	public String addStarStudent(Model model)
	{
		StarStudent starStudent= new StarStudent();
		model.addAttribute("starStudent", starStudent);
		return "add-star-student";
	}
	
	@PostMapping("/saveStarStudent")
	public String saveStarStudent(@ModelAttribute("starStudent") StarStudent starStudent, @RequestParam("studentImage") MultipartFile file, HttpSession session,
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
		starStudent.setImageName(imageUUID);
		starStudentService.saveStarStudent(starStudent);
		session.setAttribute("msg", "Star student added Successfully...!!");
		return "redirect:/index";
	}
	
	// This api is refer in student controller at index page...please see!!
	@GetMapping("/getAllStar")
	public String getAllStar(Model model)
	{
		List<StarStudent> getAllStar = starStudentService.findAllStar();
		model.addAttribute("getAllStar", getAllStar);
		return "index";
	}
	
	@GetMapping("/deleteStarById/{id}")
	public String deleteStar(@PathVariable (value="id") Long id, HttpSession session)
	{
		starStudentService.deleteStar(id);
		session.setAttribute("msgd", "Star student deleted Successfully...!!");
		return "redirect:/index";
	}
	
}
