package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long>
{
	public boolean existsByStaffId(String staffId);

	@Query("SELECT sa FROM Salary sa WHERE sa.id LIKE %?1%"
			+ " OR sa.staffId LIKE %?1%"
			+ " OR sa.name LIKE %?1%"
		//	+ " OR sa.gender LIKE %?1%")
		//	+ " OR sa.joiningDate LIKE %?1%"
			+ " OR sa.amount LIKE %?1%")
	public List<Salary> search(String keyword); 
}
