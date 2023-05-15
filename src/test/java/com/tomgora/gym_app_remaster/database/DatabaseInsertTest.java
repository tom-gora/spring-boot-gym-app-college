package com.tomgora.gym_app_remaster.database;

//internal imports
import com.tomgora.gym_app_remaster.Member;

//test related imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// utils
import java.util.List;
import java.util.Objects;


@SpringBootTest
class DatabaseInsertTest {

    // risky hook up to production db rather than using h2 / ok for the scope of this project
    @Autowired
    private DatabaseOperations databaseOperations;

    // test method
    @Test
    void isAdditionQueryExecutedCorrectly() {

        // create instance of Member to perform write test
        Member expectedMember = new Member("John", "Doe", "SM", "2012-12-12");

        // add member to the database
        databaseOperations.addMember(expectedMember);

        //retrieve list of members and extract the most recently added as Member object
        List<Member> testList = databaseOperations.getMemberList();
        Member testedMember = testList.get(testList.size()-1);
        // fix the string back to 2-letter codes because list reading class formats objects ready to be displayed
        switch (testedMember.getMembership_type()) {
            case "General Member":
                testedMember.setMembership_type("GM");
                break;
            case "Child Member":
                testedMember.setMembership_type("CM");
                break;
            default:
                testedMember.setMembership_type("SM");
                break;
        }
        // Assert the First name matches
        assertTrue(Objects.equals(expectedMember.getFirstName(), testedMember.getFirstName()));

        // Assert the Last name matches
        assertTrue(Objects.equals(expectedMember.getLastName(), testedMember.getLastName()));

        // Assert the Membership matches
        assertTrue(Objects.equals(expectedMember.getMembership_type(), testedMember.getMembership_type()));

//        // Assert the registration date name matches
        assertTrue(Objects.equals(expectedMember.getRegistration_date(), testedMember.getRegistration_date()));

        // clear the test subject from the database after tests
        databaseOperations.deleteMember(testedMember.getMember_id());
    }
}
