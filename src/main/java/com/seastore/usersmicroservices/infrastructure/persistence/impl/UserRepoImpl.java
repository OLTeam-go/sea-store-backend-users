package com.seastore.usersmicroservices.infrastructure.persistence.impl;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.User;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository("User")
public class UserRepoImpl implements UserRepo {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        final String sql = "insert into Users" +
                "(ID, Username, Email, Password, Name, Gender, Type, Active, Created_At, Updated_At) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getID(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getGender(),
                user.getType(),
                user.getActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return user;
    }

    @Override
    public User getByID(UUID findID) {
        final String sql = "select " +
                "ID," +
                "Username, " +
                "Email, " +
                "Password, " +
                "Name, " +
                "Gender, " +
                "Type, " +
                "Active, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "Users " +
                "where " +
                "ID=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findID}, ((result, i) -> {
            UUID id = UUID.fromString(result.getString("ID"));
            String username = result.getString("Username");
            String email = result.getString("Email");
            String password = result.getString("Password");
            String name = result.getString("Name");
            String gender = result.getString("Gender");
            String type = result.getString("Type");
            Boolean active = result.getBoolean("Active");
            Date createdAt = result.getDate("Created_At");
            Date updatedAt = result.getDate("Updated_At");
            return new User(id, username, email, password, name, gender, type, active, createdAt, updatedAt);
        }));
    }

    @Override
    public List<User> getAll() {
        final String sql = "select " +
                "ID," +
                "Username, " +
                "Email, " +
                "Password, " +
                "Name, " +
                "Gender, " +
                "Type, " +
                "Active, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "Users";
        return jdbcTemplate.query(sql, ((result, i) -> {
            UUID id = UUID.fromString(result.getString("ID"));
            String username = result.getString("Username");
            String email = result.getString("Email");
            String password = result.getString("Password");
            String name = result.getString("Name");
            String gender = result.getString("Gender");
            String type = result.getString("Type");
            Boolean active = result.getBoolean("Active");
            Date createdAt = result.getDate("Created_At");
            Date updatedAt = result.getDate("Updated_At");
            return new User(id, username, email, password, name, gender, type, active, createdAt, updatedAt);
        }));
    }

    @Override
    public Integer updateByID(UUID findID, User userToUpdate) {
        final String sql = "update Users set Username=?, Email=?, Password=?, Name=?, Gender=?, Type=?, Active=?, Created_At=?, Updated_At=? where ID=?";
        return jdbcTemplate.update(sql,
                userToUpdate.getUsername(),
                userToUpdate.getEmail(),
                userToUpdate.getPassword(),
                userToUpdate.getName(),
                userToUpdate.getGender(),
                userToUpdate.getType(),
                userToUpdate.getActive(),
                userToUpdate.getCreatedAt(),
                userToUpdate.getUpdatedAt(),
                findID);
    }

    @Override
    public Integer deleteByID(UUID findID) {
        final String sql = "delete from Users where ID=?";
        return jdbcTemplate.update(sql, findID);
    }

    @Override
    public User getByUsername(String findUsername) {
        final String sql = "select * from Users where Username=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findUsername}, (result, i) -> {
            UUID id = UUID.fromString(result.getString("ID"));
            String username = result.getString("Username");
            String email = result.getString("Email");
            String password = result.getString("Password");
            String name = result.getString("Name");
            String gender = result.getString("Gender");
            String type = result.getString("Type");
            Boolean active = result.getBoolean("Active");
            Date createdAt = result.getDate("Created_At");
            Date updatedAt = result.getDate("Updated_At");
            return new User(id, username, email, password, name, gender, type, active, createdAt, updatedAt);
        });
    }

    @Override
    public User getByUsernameAndPassword(String findUsername, String findPassword) {
        final String sql = "select * from Users where Username=? and Password=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findUsername, findPassword}, (result, i) -> {
            UUID id = UUID.fromString(result.getString("ID"));
            String username = result.getString("Username");
            String email = result.getString("Email");
            String password = result.getString("Password");
            String name = result.getString("Name");
            String gender = result.getString("Gender");
            String type = result.getString("Type");
            Boolean active = result.getBoolean("Active");
            Date createdAt = result.getDate("Created_At");
            Date updatedAt = result.getDate("Updated_At");
            return new User(id, username, email, password, name, gender, type, active, createdAt, updatedAt);
        });
    }

    @Override
    public Integer updateByUsername(String findUsername, User userToUpdate) {
        final String sql = "update Users set Username=?, Email=?, Password=?, Name=?, Gender=?, Active=?, Updated_At=? where Username=?";
        return jdbcTemplate.update(sql,
                userToUpdate.getUsername(),
                userToUpdate.getEmail(),
                userToUpdate.getPassword(),
                userToUpdate.getName(),
                userToUpdate.getGender(),
                userToUpdate.getActive(),
                new Timestamp(System.currentTimeMillis()),
                findUsername);
    }

    @Override
    public Integer updateByUsernameAndPassword(UUID ID, String username, String password, User userToUpdate) {
        final String sql = "update Users set Username=?, Email=?, Password=?, Name=?, Gender=?, Updated_At=? where ID=? and Username=? and Password=?";
        return jdbcTemplate.update(sql,
                userToUpdate.getUsername(),
                userToUpdate.getEmail(),
                userToUpdate.getPassword(),
                userToUpdate.getName(),
                userToUpdate.getGender(),
                new Timestamp(System.currentTimeMillis()),
                ID, username, password);
    }

    @Override
    public Integer deleteByUsername(String username) {
        final String sql = "delete from Users where Username=?";
        return jdbcTemplate.update(sql, username);
    }

}
