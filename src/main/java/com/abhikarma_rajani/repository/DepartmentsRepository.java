package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Long>
{

	public boolean existsByDepartmentId(String departmentId);
	
	@Query("SELECT departments FROM Departments departments WHERE departments.id LIKE %?1%"
			+ " OR departments.departmentId LIKE %?1%"
			+ " OR departments.departmentName LIKE %?1%"
			+ " OR departments.headOfDepartment LIKE %?1%")
		//	+ " OR departments.departmentStartDate LIKE %?1%"
		//	+ " OR departments.numbersOfStudent LIKE %?1%")
	List<Departments> search(String keyword);
	
	public Departments findBydepartmentName(String departmentName);

}
