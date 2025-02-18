package com.tomgora.gym_app_remaster.controllers;

// internal imports
import com.tomgora.gym_app_remaster.database.DatabaseOperations;
import com.tomgora.gym_app_remaster.Member;

// spring related imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// utils
import java.util.List;

@Controller
public class MemberListController {

    @Autowired
    private DatabaseOperations databaseOperations; //access my database operation methods

    //map to url and pass retrieved List of members as model for thymeleaf to render
    @GetMapping("memberlist")
    public String showMembers(Model model) {
        // retrieve List of members using relevant function
        List<Member> members = databaseOperations.getMemberList();
        System.out.println("Fetched members: " + members); // Debugging line
        // add list to the passed model as attribute named "members"
        model.addAttribute("members", members);
        return "members"; // redirect
    }
}
