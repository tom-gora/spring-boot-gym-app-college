package com.tomgora.gym_app_remaster.controllers;
// internal imports
import com.tomgora.gym_app_remaster.Member;
import com.tomgora.gym_app_remaster.database.DatabaseOperations;

// Random imported to create random input at each test run
import java.util.Random;

// testing related imports
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Test
    void testTotalPriceCalculation(){

        // all calculations depend on member's membership type so...
        // test is not pulling from DB, for each run it will create a mock member and select random type
        // from prepared array of types, using Random lib

        // select membership type string at random
        final String[] membershipTypes = {"GM", "SM", "CM"};
        Random random = new Random();
        int index = random.nextInt(membershipTypes.length);

        // construct an instance of member class with mock data and random membership type
        Member testMember = new Member("John", "Doe", membershipTypes[index],"2022-01-21");

        // create a mock object for databaseOperations with mockito and tell it to return my mock member
        DatabaseOperations databaseOperations = mock(DatabaseOperations.class);
        when(databaseOperations.getMember()).thenReturn(testMember);

        // instance of tested class to invoke total price calculating method
        TicketController tc = new TicketController(databaseOperations);
        // feed it with a mock model, as it takes an instance of Model IRL to map it ot the website with thymeleaf
        Model model = new ExtendedModelMap();
        tc.displayTicket(model);

        // retrieve the "total" attribute from the model and cast it as primitive int
        int total = (int) model.getAttribute("total");


        // print to visually double check data
        System.out.println(testMember.getMembership_type());
        System.out.println("£" + total);

        // assert the total value based on the selected membership type
        switch (testMember.getMembership_type()) {
            case "General member":
                assertEquals(128, total);
                break;
            case "Student member":
                assertEquals(88, total);
                break;
            case "Child member":
                assertEquals(53, total);
                break;
            default:
                fail("Invalid membership type");
                break;
        }
    }

}