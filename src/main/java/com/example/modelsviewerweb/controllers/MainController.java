package com.example.modelsviewerweb.controllers;

import com.example.modelsviewerweb.services.SerializeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SerializeService serializeService;

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

    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        serializeService.handleFileUploadService(file);
        return "redirect:/admin";
    }
}
