package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Controller.ProfileController;
import com.example.demo.Model.Profile;
import com.example.demo.Service.ProfileService;

public class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/profileForm"))
               .andExpect(status().isOk())
               .andExpect(view().name("profileForm"))
               .andExpect(model().attributeExists("profile"));
    }

    @Test
    public void testSaveProfile() throws Exception {
        doNothing().when(profileService).saveProfile(any(Profile.class));

        mockMvc.perform(post("/saveProfile")
                .param("name", "Test Profile")
                .param("description", "Test Description"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/profileForm"));

        verify(profileService, times(1)).saveProfile(any(Profile.class));
    }
} 