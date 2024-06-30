package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Fees;

public interface FeesRepository extends JpaRepository<Fees, Long>
{

	public boolean existsByAdmissionId(Long admissionId);
	
	@Query("SELECT fe FROM Fees fe WHERE fe.id LIKE %?1%"
			+ " OR fe.admissionId LIKE %?1%"
			+ " OR fe.studentName LIKE %?1%"
		//	+ " OR fe.className LIKE %?1%")
		//	+ " OR fe.gender LIKE %?1%")
		//	+ " OR fe.feeType LIKE %?1%"
		//	+ " OR fe.feeAmount LIKE %?1%")
		//	+ " OR fe.paidAmount LIKE %?1%")
		//	+ " OR fe.remainingAmount LIKE %?1%"
		//	+ " OR fe.feesStatus LIKE %?1%"
			+ " OR fe.paidDate LIKE %?1%")
	List<Fees> search(String keyword);
	
	@Query(value = "SELECT sum(paidAmount) FROM Fees")
   	public Double sumFees();

}
