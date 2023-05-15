package com.tomgora.gym_app_remaster;

public class Member {
     private int member_id;
    private String firstName;
    private String lastName;
    private String membership_type;
    private String registration_date;

    //full constructor
    public Member(int member_id, String firstName, String lastName, String membership_type, String registration_date) {
        this.member_id = member_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.membership_type = membership_type;
        this.registration_date = registration_date;
    }

    // constructor with no ID for mySQL to write into the database (DB increments IDs)
    public Member(String firstName, String lastName, String membership_type, String registration_date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.membership_type = membership_type;
        this.registration_date = registration_date;
    }

    // empty constructor for injecting attributes using model
    public Member() {
    }

    // necessary getters and setters
    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMembership_type() {
        return membership_type;
    }

    public void setMembership_type(String membership_type) {
        this.membership_type = membership_type;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
}
