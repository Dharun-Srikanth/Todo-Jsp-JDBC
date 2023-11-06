package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.Database;
import com.jsp_jdbc.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final Connection CONNECTION;
    private final String SELECT_ALL = "SELECT id, item, userId FROM todo WHERE userId=? AND isDeleted=false";
    private final String SELECT_TODO = "SELECT id, item, userId FROM todo WHERE id=?";
    private final String INSERT_TODO = "INSERT INTO todo (item, userId) VALUES (?,?)";
    String DELETE_TODO="UPDATE todo SET isDeleted=true WHERE id=?";

    public TodoDao() {
        CONNECTION = Database.getConnection();
    }

    public List<Todo> selectAllTodos(int userId){
        List<Todo> todos = new ArrayList<>();
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(SELECT_ALL);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Todo todo = new Todo();
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setTodoItem(rs.getString("item"));
                todo.setUserId(rs.getInt("userId"));
                todos.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    public void addTodo(String todo, int userId){
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(INSERT_TODO);
            ps.setString(1,todo);
            ps.setInt(2,userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodo(int id) {
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(DELETE_TODO);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
