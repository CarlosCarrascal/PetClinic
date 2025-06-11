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
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.Controller.UserController;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        
        // Configurar ViewResolver para las pruebas
        ViewResolver viewResolver = new InternalResourceViewResolver();
        ((InternalResourceViewResolver) viewResolver).setPrefix("/WEB-INF/views/");
        ((InternalResourceViewResolver) viewResolver).setSuffix(".html");
        
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(get("/userForm"))
               .andExpect(status().isOk())
               .andExpect(view().name("userForm"))
               .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testSaveUser() throws Exception {
        doNothing().when(userService).saveUser(any(User.class));

        mockMvc.perform(post("/saveUser")
                .param("name", "Test User")
                .param("email", "test@example.com"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/userForm"));

        verify(userService, times(1)).saveUser(any(User.class));
    }
} 