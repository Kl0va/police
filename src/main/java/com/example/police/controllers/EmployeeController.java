package com.example.police.controllers;

import com.example.police.models.Position;
import com.example.police.models.Statement;
import com.example.police.models.User;
import com.example.police.repo.StatementRepository;
import com.example.police.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    StatementRepository statementRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/employeeHome")
    public String getHome(){
        return "employeeHome";
    }

    @GetMapping("/considerStatement")
    public String getStatements(Model model){
        Iterable<Statement> statements = statementRepository.findAll();
        model.addAttribute("statement",statements);
        return "considerStatement";
    }

    @PostMapping("/considerStatement")
    public String addStatement(@AuthenticationPrincipal User user,@RequestParam long id,
            Model model){
        Optional<Statement> statement = statementRepository.findById(id);
        user.getStatements().add(statement.get());
        statement.get().getUsers().add(user);
        userRepository.save(user);
        return "redirect:/AcceptedStatement";
    }

    @GetMapping("/AcceptedStatement")
    public String getAccepted(@AuthenticationPrincipal User user,Model model){
        Iterable<Statement> statements = statementRepository.findAll();
        model.addAttribute("statement",statements);
        User users = userRepository.findByUsername(user.getUsername());
        users.getStatements();
        model.addAttribute("users",users);
        return "AcceptedStatement";
    }
}
