package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.Database;
import com.jsp_jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Connection CONNECTION;

    public UserDao() {
        CONNECTION = Database.getConnection();
    }

    private final String LOGIN_QUERY = "SELECT * FROM auth WHERE username=? AND password=?";
    private static String ADD_USER="INSERT INTO auth(userName,password) VALUES(?,?)";

    public User loginUser(String username, String password){
        User user = null;
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(LOGIN_QUERY);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setPass(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void addUser(String name, String password) throws SQLException {
        PreparedStatement statement= CONNECTION.prepareStatement(ADD_USER);
        statement.setString(1,name);
        statement.setString(2,password);
        statement.executeUpdate();
    }

}
