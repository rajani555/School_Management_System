package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long>
{
	public boolean existsByrouteName(String routeName);

	@Query("SELECT trans FROM Transport trans WHERE trans.routeName LIKE %?1%"
			+ " OR trans.vehicleNumber LIKE %?1%"
		//	+ " OR trans.nosOfVehicle LIKE %?1%"
			+ " OR trans.driverName LIKE %?1%")
		//	+ " OR trans.contactNumber LIKE %?1%"
		//	+ " OR trans.driverAddress LIKE %?1%"
		//	+ " OR trans.licenseNumber LIKE %?1%")
	public List<Transport> search(String keyword);
}
