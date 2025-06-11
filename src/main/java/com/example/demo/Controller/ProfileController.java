package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Profile;
import com.example.demo.Service.ProfileService;

@Controller
public class ProfileController {

	@Autowired
    private ProfileService profileService;

    @GetMapping("/profileForm")
    public String showForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "profileForm";  // Nombre de la vista Thymeleaf
    }

    @PostMapping("/saveProfile")
    public String saveProfile(Profile profile) {
        profileService.saveProfile(profile);
        return "redirect:/profileForm";
    }
}
