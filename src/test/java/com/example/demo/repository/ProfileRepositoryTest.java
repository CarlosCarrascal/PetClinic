package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.Model.Profile;
import com.example.demo.Repository.ProfileRepository;

@DataJpaTest
public class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testSaveProfile() {
        Profile profile = new Profile();
        profile.setName("Test Profile");
        profile.setDescription("Test Description");

        Profile savedProfile = entityManager.persistAndFlush(profile);
        assertNotNull(savedProfile.getId());

        Profile foundProfile = profileRepository.findById(savedProfile.getId()).orElse(null);
        assertNotNull(foundProfile);
        assertEquals("Test Profile", foundProfile.getName());
        assertEquals("Test Description", foundProfile.getDescription());
    }
} 