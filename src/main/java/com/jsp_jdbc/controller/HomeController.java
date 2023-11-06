package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.TodoDao;
import com.jsp_jdbc.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private final TodoDao todoDao;

    public HomeController() {
        todoDao = new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null) {
            todoDao.deleteTodo(Integer.parseInt(id));
        }

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");

        String userId = req.getSession().getAttribute("userId").toString();
        String item = req.getParameter("item");
        System.out.println(item);

        if(item != null && !item.trim().isEmpty()){
            todoDao.addTodo(item, Integer.parseInt(userId));
            item = "";
        }

        List<Todo> todos = todoDao.selectAllTodos(Integer.parseInt(userId));
        req.setAttribute("todos",todos);
        dispatcher.forward(req,resp);
    }
}
