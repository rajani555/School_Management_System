package com.abhikarma_rajani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhikarma_rajani.entity.ForgotPasswordToken;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, Long>
{
	ForgotPasswordToken findByToken(String token);   	//Step-1
}