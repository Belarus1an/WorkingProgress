package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.UserRepo;
import com.samson.workingProgress.models.Role;
import com.samson.workingProgress.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String showRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, ModelMap modelMap){

        User newUser = userRepo.findByUsername(user.getUsername());
        if (newUser != null){
            modelMap.put("infoNegative", "Ten użytkownik już istnieje!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
