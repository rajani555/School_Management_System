package com.abhikarma_rajani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhikarma_rajani.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>
{
	// Custom - User defined method !
	Admin findByUsername(String username);
	
	public boolean existsByUsername(String username);
}
