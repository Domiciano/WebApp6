package edu.co.icesi.introspringboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/myprofile")
    public String myProfile() {
        return "user/profile";
    }
}
