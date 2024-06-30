package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long>
{
	@Query("SELECT holi FROM Holiday holi WHERE holi.holidayName LIKE %?1%"
			+ " OR holi.typeofHoliday LIKE %?1%"
		//	+ " OR holi.startDate LIKE %?1%"
			+ " OR holi.endDate LIKE %?1%")
	List<Holiday> search(String keyword);

}
