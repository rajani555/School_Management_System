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
import com.abhikarma_rajani.entity.Departments;
import com.abhikarma_rajani.entity.StarStudent;
import com.abhikarma_rajani.entity.Student;
import com.abhikarma_rajani.service.DepartmentsService;
import com.abhikarma_rajani.service.FeesService;
import com.abhikarma_rajani.service.StarStudentService;
import com.abhikarma_rajani.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController
{
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DepartmentsService departmentsService;
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private StarStudentService starStudentService;

	
	//(01) go to index !!
	@GetMapping("/index")
	public String index(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		// Student Count:
		List<Student> allStudent = studentService.getAllStudent();
		int size = allStudent.size();
		model.addAttribute("size", size);
		
		// Department Count:
		List<Departments> allDepartment = departmentsService.getAllDepartment();
		int size2 = allDepartment.size();
		model.addAttribute("size2", size2);
		
		// Total Fees Count:
		Double totalFeeCount = feesService.totalFeeCount();
		model.addAttribute("totalFeeCount", totalFeeCount);
		
		// Total 2023
		List<Student> allB = studentService.getAll2023();
		int size3 = allB.size();
		model.addAttribute("size3", size3);
		
		// Total 2024
		List<Student> all2024 = studentService.getAll2024();
		int size4 = all2024.size();
		model.addAttribute("size4", size4);
		
		// Total 2025
		List<Student> all2025 = studentService.getAll2025();
		int size5 = all2025.size();
		model.addAttribute("size5", size5);
		
		// Star Student
		List<StarStudent> getAllStar = starStudentService.findAllStar();
		model.addAttribute("getAllStar", getAllStar);
		
		return "index";
	}
	
	//(02) go to teacher dashboard !!
	@GetMapping("/teacher-dashboard")
	public String teacherDashboard()
	{
		return "teacher-dashboard";
	}
	
	//(03) go to student dashboard !!
	@GetMapping("/student-dashboard")
	public String studentDashboard()
	{
		return "student-dashboard";
	}
	
	//(03) go to student view !!
	@GetMapping("/student-details")
	public String studentView()
	{
		return "student-details";
	}
	
	//(05) load the add form with student object !!
	@GetMapping ("/addStudent")
	public String addstudent(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Student student= new Student();
		model.addAttribute("student", student);
		return "add-student";
	}
	
	// #Image Variable uploadDir
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/studentimages";
	
	//(06) Save the student into DB with condition !!
	@PostMapping ("/saveStudent")
	public String saveStudent(@ModelAttribute("student") @RequestBody Student student,
							  @RequestParam("studentImage") MultipartFile file,
							  @RequestParam("imgName") String imgName,
							  HttpSession session) throws IOException
	{
		boolean checkAdmissionId = studentService.checkAdmissionId(student.getAdmissionId());
		if(checkAdmissionId)
		{
			session.setAttribute("msg", "Given AdmissionId already exists...!!");
			//System.out.println("AdmissionId already exist...!!");
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
			student.setImageName(imageUUID);
			
			Student addStudentWithCondition = studentService.addStudent(student);
			if(addStudentWithCondition != null)
			{
				session.setAttribute("msg", "Student Register Successfully...!!");
				//System.out.println("Student Register Successfully...!!");
			}
			else
			{
				session.setAttribute("msg", "Somthing went rong...!!");
				//System.out.println("Something error in server...!!");
			}
		}
		return "redirect:/studentlist";
	}
		
	//(08) fetching all the student from database with search option!!
	@GetMapping ("/studentlist")
	public String studentList(Model model, @Param("keyword") String keyword, @Param("name") String name, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Student> studentlist = studentService.listAll(keyword);
		
		model.addAttribute("studentlist", studentlist);
		model.addAttribute("keyword", keyword);
		return "students";
	}
	
	//(09) Delete the student by id from database!!
	@GetMapping ("/DeleteStudentById/{id}")
	public String deleteStudentById(@PathVariable (value= "id") long id, HttpSession session)
	{
		studentService.deleteStudentWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/studentlist";
	}

	//(10) Update student by id into database!!
	@GetMapping ("/UpdateStudentById/{id}")
	public String updateStudentById(@PathVariable (value= "id") long id, Model model, Principal principal)
	{	
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		model.addAttribute("student", studentService.updateStudentWithId(id));
		return "edit-student";
	}

	//(11) update the teacher into DB !!
	// #Image Variable uploadDir
	// public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/teacherimages";
	@PostMapping ("/updateStudent")
	public String updateAndSaveStudent(@ModelAttribute("student") @RequestBody Student student,
							  @RequestParam("studentImage") MultipartFile file,
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
		student.setImageName(imageUUID);
		
		studentService.editStudent(student);
		session.setAttribute("msgu", "Record Updated Successfully...!!");
		return "redirect:/studentlist";
	}
	
	//(12) view particular student details...!!
	@GetMapping ("/ViewStudentDetailsById/{id}")
	public String viewParticularStudentDetailsById(@PathVariable("id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Student viewParticularStudentDetailsWithId = studentService.viewParticularStudentDetailsWithId(id);
		model.addAttribute("student", viewParticularStudentDetailsWithId);
		return "student-details";
	}
	
	//(13) Print the Excel Report for all!!
	@GetMapping ("/ExcelReport")
	public void generateExcelReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=student.xls";
		
		response.setHeader(headerKey, headerValue);
		
		studentService.generateExcel(response);
	}
	
	//(14) Print the Excel Report for particular!!
	@GetMapping ("/ExcelReportById/{id}")
	public void generateExcelReportById(@PathVariable(value = "id") long id, HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment;filename=student.xls";
		
		response.setHeader(headerKey, headerValue);
		
		studentService.generateExcelById(id, response);
	}
	
}
