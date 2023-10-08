package com.tomgora.gym_app_remaster.controllers;

// internal imports
import com.tomgora.gym_app_remaster.database.DatabaseOperations;
import com.tomgora.gym_app_remaster.Member;

// spring related imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// utils
import java.util.List;

@Controller
public class RemoveMemberController {
    @Autowired
    private DatabaseOperations databaseOperations; //access my database operation methods

    //map to url and pass retrieved List of members as model for thymeleaf to render
    @GetMapping("delete_member_form")
    public String showMembers(Model model) {
        // retrieve List of members using relevant function
        List<Member> members = databaseOperations.getMemberList();
        // add list to the passed model as attribute named "members"
        model.addAttribute("members", members);
        return "delete_member_form";
    }

    // mapping listening to DELETE requests from the client's JS fetch call, using a different endpoint url
    @DeleteMapping("del_member")
    // if request received get the param from url (id) and perform deletion passing
    // this id into relevant function
    public String delMember(@RequestParam("id") int memberId) {
        databaseOperations.deleteMember(memberId);
        return "redirect:/memberlist"; // redirect to updated list
    }

}
