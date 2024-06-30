package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long>
{
	@Query("SELECT spo FROM Sport spo WHERE spo.sportId LIKE %?1%"
			+ " OR spo.sportName LIKE %?1%"
			+ " OR spo.coachName LIKE %?1%"
		//	+ " OR spo.startTime LIKE %?1%"
		//	+ " OR spo.endTime LIKE %?1%"
			+ " OR spo.startedYear LIKE %?1%")
	List<Sport> search(String keyword);

}
