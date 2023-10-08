package com.tomgora.gym_app_remaster.database;

import com.tomgora.gym_app_remaster.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseOperations {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Member> getMemberList() {
        // SQL query to retrieve all data from the members table. Suppressing the warning because stupid IDE kept giving me
        // no defined schema warning while in fact the spring app itself was just fine and working like a charm.
        @SuppressWarnings("SqlResolve") String query = "SELECT * FROM members";

        // RowMapper library that maps response rows to objects
        RowMapper<Member> rowMapper = (rs, rowNum) -> {
            Member member = new Member(0, null, null, null, null);
            member.setMember_id(rs.getInt("member_id"));
            member.setFirstName(rs.getString("first_name"));
            member.setLastName(rs.getString("last_name"));
            member.setMembership_type(rs.getString("membership_type"));
            member.setRegistration_date(rs.getString("registration_date"));
            switch (member.getMembership_type()) {
                case "GM":
                    member.setMembership_type("General member");
                    break;
                case "SM":
                    member.setMembership_type("Student member");
                    break;
                default:
                    member.setMembership_type("Child member");
                    break;
            }
            return member;
        };

        // expecute the query with a template and map to objects and throw them in a list and return from method
        // such efficient one-liner
        return jdbcTemplate.query(query, rowMapper);
    }

    public Member getMember() {
        @SuppressWarnings("SqlResolve") String query = "SELECT * FROM members ORDER BY RAND() LIMIT 1;";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Member.class));
    }

    public void addMember(Member m) {
        switch (m.getMembership_type()) {
            case "General Member":
                m.setMembership_type("GM");
                break;
            case "Child Member":
                m.setMembership_type("CM");
                break;
            default:
                m.setMembership_type("SM");
                break;
        }
        @SuppressWarnings("SqlResolve") String query = "INSERT INTO members (first_name, last_name, membership_type, registration_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, m.getFirstName(), m.getLastName(), m.getMembership_type(), m.getRegistration_date());
    }

    public void deleteMember(int member_id) {
        @SuppressWarnings("SqlResolve") String query = "DELETE FROM members WHERE member_id = ?";
        jdbcTemplate.update(query, member_id);
    }
}
