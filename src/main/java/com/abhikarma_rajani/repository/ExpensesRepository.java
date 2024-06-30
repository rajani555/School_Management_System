package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>
{
	public boolean existsByExpensesId(String expensesID);

	@Query("SELECT ppp FROM Expenses ppp WHERE ppp.id LIKE %?1%"
			+ " OR ppp.expensesId LIKE %?1%"
			+ " OR ppp.itemName LIKE %?1%"
		//	+ " OR ppp.itemQuantity LIKE %?1%")
		//	+ " OR ppp.expenseAmount LIKE %?1%"
			+ " OR ppp.sourceOfPurches LIKE %?1%")
		//	+ " OR ppp.dateOfPurches LIKE %?1%")
	public List<Expenses> search(String keyword);
}
