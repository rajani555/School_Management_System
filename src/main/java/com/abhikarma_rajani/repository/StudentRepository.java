package com.abhikarma_rajani.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.abhikarma_rajani.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>
{
	//User (Own) defined method for JpaRepository!!
	
		Optional<Student> findByAdmissionId(long admissionId);
		
		List<Student> findByFirstName(String firstName);
		
		List<Student> findByStudentMobileNumber(long studentMobileNumber);
		
		public void deleteByAdmissionId(long admissionId);
		
		public boolean existsByAdmissionId(long admissionId);

		@Query("SELECT student FROM Student student WHERE student.id LIKE %?1%"
				+ " OR student.admissionId LIKE %?1%"
				+ " OR student.firstName LIKE %?1%"
				+ " OR student.lastName LIKE %?1%"
			//	+ " OR student.gender LIKE %?1%"
			//	+ " OR student.dateOfBirth LIKE %?1%"
			//	+ " OR student.rollNumber LIKE %?1%"
			//	+ " OR student.bloodGroup LIKE %?1%"
			//	+ " OR student.religion LIKE %?1%"
			//	+ " OR student.email LIKE %?1%"
			//	+ " OR student.className LIKE %?1%"
			//	+ " OR student.section LIKE %?1%"
				+ " OR student.studentMobileNumber LIKE %?1%")
			//	+ " OR student.parentMobileNumber LIKE %?1%"
			//	+ " OR student.fatherName LIKE %?1%"
			//	+ " OR student.motherName LIKE %?1%"
			//	+ " OR student.address LIKE %?1%"
			//	+ " OR student.uploadPic LIKE %?1%")
		List<Student> search(String keyword);
		
		public Student findByfirstName(String firstName);
		
		// @Query(value = "SELECT u FROM Student u WHERE u.bloodGroup LIKE 'B+%'")
		@Query(value = "SELECT u FROM Student u WHERE u.admissionSession LIKE '%23'")
		List<Student> findAllsess23();
		
		// @Query(value = "SELECT u FROM Student u WHERE u.bloodGroup LIKE 'B+%'")
		@Query(value = "SELECT u FROM Student u WHERE u.admissionSession LIKE '%24'")
		List<Student> findAllsess24();
		
		// @Query(value = "SELECT u FROM Student u WHERE u.bloodGroup LIKE 'B+%'")
		@Query(value = "SELECT u FROM Student u WHERE u.admissionSession LIKE '%25'")
		List<Student> findAllsess25();
		
}
