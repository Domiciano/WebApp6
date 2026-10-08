package edu.co.icesi.introspringboot.controller;

import edu.co.icesi.introspringboot.entity.User;
import edu.co.icesi.introspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    @GetMapping("/new")
    public String newUser(
            Model model,
            @RequestParam(required = false) String status
    ) {
        if ("success".equals(status)) {
            model.addAttribute("message", "User successfully saved");
        }
        model.addAttribute("user", new User());
        return "user/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/new?status=success";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }


}
