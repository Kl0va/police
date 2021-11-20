package com.example.police.controllers;

import com.example.police.models.Role;
import com.example.police.models.State;
import com.example.police.models.Statement;
import com.example.police.models.User;
import com.example.police.repo.StateRepository;
import com.example.police.repo.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    StatementRepository statementRepository;
    @Autowired
    StateRepository stateRepository;
    @GetMapping("/")
    public String Home(@AuthenticationPrincipal User user,Model model){
        if(user.getRole().getName().equals("USER")) {
            return "home";
        }
        if(user.getRole().getName().equals("Employee")){
            return "EmployeeHome";
        }
        return "redirect:/login";
    }

    @GetMapping("/statement")
    public String Statement(@AuthenticationPrincipal User user,Model model){
        if(user.getRole().getName().equals("USER")) {
            Iterable<State> states = stateRepository.findAll();
            model.addAttribute("state",states);
            return "statement";
        }
        return "redirect:/home";
    }

    @PostMapping("/statement-add")
    public String addStatement(@AuthenticationPrincipal User user, @RequestParam String text,
            @RequestParam State state, Statement statement, Model model){
        statement.setUser(user);
        statement.setState(state);
        statement.setText(text);
        statementRepository.save(statement);
        return "redirect:/";
    }

    @GetMapping("/AllStatements")
    public String AllStatements(@AuthenticationPrincipal User user,Model model){
        List<Statement> statement = statementRepository.findByUser(user);
        model.addAttribute("statements",statement);
        return "AllStatements";
    }

    @GetMapping("/edit/{id}")
    public String editStatement(@AuthenticationPrincipal User user,@PathVariable(value = "id") long id,Model model){
        if(!statementRepository.existsById(id)){
            return "redirect:/";
        }
            Iterable<State> states = stateRepository.findAll();
            model.addAttribute("state", states);
            Optional<Statement> statement = statementRepository.findById(id);
            ArrayList<Statement> res = new ArrayList<>();
            statement.ifPresent(res::add);
            model.addAttribute("statement", res);
            return "editStatement";
    }

    @PostMapping("/edit/{id}")
    public String updateStatements(@AuthenticationPrincipal User user,@PathVariable(value = "id") long id,@RequestParam String text,
            @RequestParam State state,Model model){
        Statement statement = statementRepository.findById(id).orElseThrow();
            statement.setUser(user);
            statement.setText(text);
            statement.setState(state);
            statementRepository.save(statement);
            return "redirect:/AllStatements";
    }
}
