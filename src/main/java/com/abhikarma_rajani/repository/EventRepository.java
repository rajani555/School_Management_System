package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>
{

	@Query("SELECT eve FROM Event eve WHERE eve.eventId LIKE %?1%"
			+ " OR eve.eventName LIKE %?1%"
			+ " OR eve.eventDate LIKE %?1%")
	List<Event> search(String keyword);

}
