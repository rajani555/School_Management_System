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

import com.abhikarma_rajani.entity.Expenses;
import com.abhikarma_rajani.service.ExpensesService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpensesController
{
	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//(01) Load the Expenses Form !!
	@GetMapping("/addExpenses")
	public String loadExpensesForm(Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Expenses expenses= new Expenses();
		model.addAttribute("expenses", expenses);
		return "add-expenses";
	}
	
	//(02) Save Expenses into DB !!
	@PostMapping("/saveExpenses")
	public String saveExpenses(@ModelAttribute("expenses") Expenses expenses, HttpSession session)
	{
		boolean checkExpensesId = expensesService.checkExpensesId(expenses.getExpensesId());
		if(checkExpensesId)
		{
			session.setAttribute("msg", "Given salary ID is already in records...!!");
		}
		else
		{
			Expenses addExpenses = expensesService.addExpenses(expenses);
			if(addExpenses != null)
			{
				session.setAttribute("msg", "Expenses added successfully in records...!!");
			}
			else
			{
				session.setAttribute("msg", "Something worng in server...!!");
			}
		}
		
		return "redirect:/expenses";
	}
	
	//(03) Fetching all the expenses details from database with search option!!
	@GetMapping ("/expenses")
	public String expensesList(Model model, @Param("keyword") String keyword, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		List<Expenses> expenseslist = expensesService.expensesListAll(keyword);
		
		model.addAttribute("expenseslist", expenseslist);
		model.addAttribute("keyword", keyword);
		return "expenses";
	}
	
	//(04) Delete expenses by id !!
	@GetMapping("/DeleteExpensesById/{id}")
	public String deleteExpensesById(@PathVariable(value = "id") long id, HttpSession session)
	{
		expensesService.deleteExpensesWithId(id);
		session.setAttribute("msgd", "Record deleted successfully...!!");
		return "redirect:/expenses";
	}
	
	//(05) Update the expenses by id !!
	@GetMapping("/UpdateExpensesById/{id}")
	public String updateExpensesById(@PathVariable(value = "id") long id, Model model, Principal principal)
	{
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("loadUserByUsername", loadUserByUsername);
		
		Expenses expenses = expensesService.updateExpenseWithId(id);
		model.addAttribute("expenses", expenses);
		return "edit-expenses";
	}
	
	//(06) save after update the expenses !!
	@PostMapping("/UpdateExpenses")
	public String saveAfterUpdate(@ModelAttribute("expenses") Expenses expenses, HttpSession session)
	{
		expensesService.saveAfterUpdate(expenses);
		session.setAttribute("msgu", "Record updated successfully...!!");
		return "redirect:/expenses";
	}
}
