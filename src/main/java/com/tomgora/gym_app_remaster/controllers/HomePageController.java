package com.tomgora.gym_app_remaster.controllers;

// spring related imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("gym_home") //expose the url for the main page
    public String showMembers() {
        return "home_page"; // redirect
    }
}