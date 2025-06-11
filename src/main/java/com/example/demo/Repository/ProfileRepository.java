package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	
	
}
