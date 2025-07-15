package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO users (name, phone) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getPhone());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
            (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone"))
        );
    }
}
