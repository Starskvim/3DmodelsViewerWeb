package com.example.modelsviewerweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/models";
    }

    @GetMapping("/admin")
    public String toAdmin() {
        return "admin";
    }
}