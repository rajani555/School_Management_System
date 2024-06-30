package com.abhikarma_rajani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abhikarma_rajani.entity.Expenses;
import com.abhikarma_rajani.repository.ExpensesRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ExpensesService 
{
	@Autowired
	private ExpensesRepository expensesRepository;
	
	// (01)
	public Expenses addExpenses(Expenses expenses)
	{
		return expensesRepository.save(expenses);
		
	}
	
	// (*)
	public boolean checkExpensesId(String expensesID)
	{
		return expensesRepository.existsByExpensesId(expensesID);
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
	public List<Expenses> expensesListAll(String keyword)
	{
		if(keyword != null)
		{
			return expensesRepository.search(keyword);
		}
		else
		{
			return(List<Expenses>) expensesRepository.findAll();
		}	
	}

	// (03)
	public void deleteExpensesWithId(long id)
	{
		expensesRepository.deleteById(id);
	}

	// (04)
	public Expenses updateExpenseWithId(long id)
	{
		Optional<Expenses> temp = expensesRepository.findById(id);
		Expenses expenses = temp.get();
		return expenses;
		
	}

	// (05)
	public void saveAfterUpdate(Expenses expenses)
	{
		expensesRepository.save(expenses);
		
	}
}
