package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>
{

	public boolean existsByTeacherId(long teacherId);
	
	@Query("SELECT teacher FROM Teacher teacher WHERE teacher.id LIKE %?1%"
			+ " OR teacher.teacherId LIKE %?1%"
			+ " OR teacher.name LIKE %?1%"
		//	+ " OR teacher.gender LIKE %?1%"
		//	+ " OR teacher.dateOfBirth LIKE %?1%"
			+ " OR teacher.mobileNumber LIKE %?1%")
		//	+ " OR teacher.joiningDate LIKE %?1%"
		//	+ " OR teacher.qualification LIKE %?1%"
		//	+ " OR teacher.experience LIKE %?1%"
		//	+ " OR teacher.email LIKE %?1%"
		//	+ " OR teacher.address LIKE %?1%"
		//	+ " OR teacher.city LIKE %?1%"
		//	+ " OR teacher.state LIKE %?1%"
		//	+ " OR teacher.zipCode LIKE %?1%"
		//	+ " OR teacher.country LIKE %?1%"
		//	+ " OR teacher.uploadPic LIKE %?1%")
	List<Teacher> search(String keyword);
	
	public Teacher findByName(String name);

}
