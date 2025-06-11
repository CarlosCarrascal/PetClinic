package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Profile;

import com.example.demo.Repository.ProfileRepository;

@Service
public class ProfileService {

	 @Autowired
	 private ProfileRepository profileRepository;
	 public void saveProfile(Profile profile) {
		 profileRepository.save(profile);
	 }
}
