package com.example.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Model.Profile;
import com.example.demo.Repository.ProfileRepository;
import com.example.demo.Service.ProfileService;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProfile() {
        Profile profile = new Profile();
        profile.setName("Test Profile");
        profile.setDescription("Test Description");

        profileService.saveProfile(profile);

        verify(profileRepository, times(1)).save(profile);
    }
} 