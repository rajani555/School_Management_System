package com.abhikarma_rajani.security_configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.abhikarma_rajani.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/index", "/register", "/login", "/password-request", "/reset-password").permitAll()
		.requestMatchers(SECURED_URLs).authenticated()
		.requestMatchers("/resources/**", "/static/**", "/css/**", "/assets/**", "/js/**", "/img/**", "/icon/**", "/fonts/**", "/plugins/**", "studentimages/**", "/teacherimages/**", "starstudentimages/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/index", true)
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll();
		return http.build();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception
	{
		managerBuilder
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	
	private static final String[] SECURED_URLs = {"/teacher-dashboard", "/student-dashboard", "/student-details", "/addStudent", "/saveStudent", "/studentlist", "/DeleteStudentById/{id}", 
												  "/UpdateStudentById/{id}", "/updateStudent", "/ViewStudentDetailsById/{id}", "/ExcelReport", "/ExcelReportById/{id}",
												  
												  "/add-teacher", "/saveTeacher", "/teacherlist", "/DeleteTeacherById/{id}", "/UpdateTeacherById/{id}", "/updateTeacher", "/TeacherExcelReport", 
												  
												  "/add-departments", "/saveDepartment", "/departmentslist", "/DeleteDepartmentsById/{id}", "/UpdateDepartmentsById/{id}", "/updateDepartment", 
												  "/PrintExcelReport", "/PrintPdfReport", "/PrintPdfReportById/{id}",
												  
												  "/add-subject", "/saveSubject", "/subjectslist", "/DeleteSubjectById/{id}", "/UpdateSubjectById/{id}", "/UpdateSubject", "/SubjectExcelReport", 
												  
												  "/addFees", "/saveFees", "/fees-collections", "/DeleteFeesById/{id}", "/UpdateFeesById/{id}", "/UpdateFees", "/PrintExcelReportFees", "/ExcelReportByIdFees/{id}", 
												  "/PrintPdfReportFees", "/PrintPdfReportByIdFees/{id}",
												  
												  "/addExpenses", "/saveExpenses", "/expenses", "/DeleteExpensesById/{id}", "/UpdateExpensesById/{id}", "/UpdateExpenses", 
												  
												  "/addSalary", "/add-salary", "/salary", "/DeleteSalaryById/{id}", "/UpdateSalaryById/{id}", "/UpdateSalary",
												  
												  "/Exam/**", "/Events/**", "/Books/**", "/Transport/**", "/Holidays/**", "/Times/**", "/Sports/**", "/Hostel/**", "/Star/**", "/update/{id}", "/update"};
	
	private static final String[] UN_SECURED_URLs = {"/index", "/register", "/login", "/password-request", "/reset-password"};
	
}
