package com.example.police.controllers;

import com.example.police.models.Position;
import com.example.police.models.Role;
import com.example.police.models.State;
import com.example.police.models.User;
import com.example.police.repo.PositionRepository;
import com.example.police.repo.StateRepository;
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

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    StatementRepository statementRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/adminHome")
    public String getHome(){
        return "adminHome";
    }

    @GetMapping("/AddPosition")
    public String getAddPosition(Model model){
        return "AddPosition";
    }

    @PostMapping("/position-add")
    public String getAdminHome(@AuthenticationPrincipal User user,
                               @RequestParam double salary,
                               @RequestParam String name, Position position, Model model){
        position.setName(name);
        position.setSalary(salary);
        positionRepository.save(position);
        return "adminHome";
    }


    @GetMapping("/editPosition")
    public String getPositions(Model model){
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("position",positions);
        return "editPosition";
    }

    @PostMapping("/editPosition")
    public String editPosition(@RequestParam String name, @RequestParam double salary, Model model){
        Position position = positionRepository.findByName(name);
        position.setName(name);
        position.setSalary(salary);
        positionRepository.save(position);

        return "redirect:/editPosition";
    }

    @GetMapping("/AddEmployee")
    public String addEmployee(Model model){
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("position",positions);
        return "AddEmployee";
    }

    @GetMapping("/editEmployee")
    public String editEmployee(Model model){
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("position",positions);
        Role role = new Role("EMPLOYEE");
        List<User> users = userRepository.findByRole(role);
        model.addAttribute("usersDB",users);
        return "editEmployee";
    }

    @PostMapping("/editEmployee")
    public String editEmployeeFinal(@RequestParam String username,@RequestParam String first,@RequestParam String second,
            @RequestParam String middle,@RequestParam String ser,
            @RequestParam String num,@RequestParam Position position,Model model){
        User user = userRepository.findByUsername(username);
        user.setFirst_name(first);
        user.setSecond_name(second);
        user.setMiddle_name(middle);
        user.setSer_pass(ser);
        user.setNum_pass(num);
        user.setPosition(position);
        userRepository.save(user);
        return "redirect:/editEmployee";
    }
}
