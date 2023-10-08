package com.tomgora.gym_app_remaster.controllers;

// internal imports
import com.tomgora.gym_app_remaster.database.DatabaseOperations;
import com.tomgora.gym_app_remaster.Member;

// spring related imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TicketController {
    @Autowired
    private DatabaseOperations databaseOperations; //access my database operation methods

    // extra constructor necessary for my JUnit test to enable injecting a mock instance of databaseOperations
    // with mock retrieval method with mockito to avoid messing with the real production db
    public TicketController(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    //map to url and pass retrieved member Object as model for thymeleaf to render
    @GetMapping("ticket")
    public String displayTicket(Model model) {
        // get data of a random member with SQL reading a random row with a dedicated method
        Member member = databaseOperations.getMember();
        model.addAttribute("member", member);

        // set up variables for pricing calculations
        int annFee;
        int processFee;
        final int journFee = 8;
        // fix code strings to verbose for the user to see in the ui
        // and calculate prices for each possible case of the 3 types
        switch (member.getMembership_type()) {
            case "GM":
                member.setMembership_type("General member");
                annFee = 100;
                processFee = 20;
                break;
            case "SM":
                member.setMembership_type("Student member");
                annFee = 70;
                processFee = 10;
                break;
            default:
                member.setMembership_type("Child member");
                annFee = 45;
                processFee = 0;
                break;
        }
        // add the values into the model as attribute for thymelaf to read
        model.addAttribute("annualFee", annFee);
        model.addAttribute("processingFee", processFee);
        model.addAttribute("journalFee", journFee);
        // calculate total accordingly and also add it to the model
        model.addAttribute("total", annFee + processFee + journFee);


        return "ticket"; //redirect
    }
}
