package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>
{

	@Query("SELECT ex FROM Exam ex WHERE ex.id LIKE %?1%"
			+ " OR ex.examName LIKE %?1%"
			+ " OR ex.className LIKE %?1%"
			+ " OR ex.subject LIKE %?1%"
		//	+ " OR ex.fees LIKE %?1%"
		//	+ " OR ex.starTime LIKE %?1%"
		//	+ " OR ex.endTime LIKE %?1%")
			+ " OR ex.examDate LIKE %?1%")
	List<Exam> search(String keyword);
	
}
