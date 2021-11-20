package com.example.police.controllers;

import com.example.police.models.Position;
import com.example.police.models.Role;
import com.example.police.models.User;
import com.example.police.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String getReg(Model model){

        return "registration";
    }

    @PostMapping("/registration")
    public String addNew(@RequestParam String second, @RequestParam String first,
                         @RequestParam String ser, @RequestParam String num,
                         @RequestParam String middle,User user, Model model){
        user.setFirst_name(first);
        user.setMiddle_name(middle);
        user.setNum_pass(num);
        user.setSecond_name(second);
        user.setSer_pass(ser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role("USER");
        Position position = new Position("USER",0);
        user.setRole(role);
        user.setPosition(position);
        userRepository.save(user);
        return "redirect:/login";
    }
}
