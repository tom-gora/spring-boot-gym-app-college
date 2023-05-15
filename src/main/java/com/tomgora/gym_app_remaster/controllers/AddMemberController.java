package com.tomgora.gym_app_remaster.controllers;

// internal imports
import com.tomgora.gym_app_remaster.Member;
import com.tomgora.gym_app_remaster.database.DatabaseOperations;

// spring related imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddMemberController {
    @Autowired
    private DatabaseOperations databaseOperations; //access my database operation methods

    @GetMapping("add_member_form")
    // expose the endpoint and pass empty Member instance as model
    // so thymeleaf mapping doesn't break trying to map NOTHING to input fields on load
    // with this it will map empty values on initial load
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        return "add_member_form"; //return template mapped to above url
    }

    // mapping listening to POST requests from the client's JS fetch call, using a different endpoint url
    @PostMapping("add_member")
    // map the passed model's attributes into a Java object
    public String addMember(@ModelAttribute("member") Member member) {
        // using Java get a current date for the member object and stick it in place
        String date = java.time.LocalDate.now().toString();
        member.setRegistration_date(date);

        //write into db by calling the relevant function then redirect to up[dated list page
        databaseOperations.addMember(member);
        return "redirect:/members"; // redirect to updated list
    }

}

