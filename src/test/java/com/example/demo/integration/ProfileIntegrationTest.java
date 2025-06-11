package com.example.demo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.Profile;
import com.example.demo.Repository.ProfileRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class ProfileIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testCreateProfile() throws Exception {
        mockMvc.perform(post("/saveProfile")
                .param("name", "Integration Test Profile")
                .param("description", "Integration Test Description"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/profileForm"));

        Profile savedProfile = profileRepository.findAll().stream()
                .filter(p -> "Integration Test Profile".equals(p.getName()))
                .findFirst()
                .orElse(null);

        assertNotNull(savedProfile);
        assertEquals("Integration Test Description", savedProfile.getDescription());
    }
} 