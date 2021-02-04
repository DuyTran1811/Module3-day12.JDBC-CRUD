package com.jdbc.controller;

import com.jdbc.controller.model.User;
import com.jdbc.controller.service.IUserService;
import com.jdbc.controller.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UseServlet", value = "/users")
public class UseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(request,response);
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userService.selectAllUsers();
        request.setAttribute("list", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "edit":
                break;
            case "delete":
                break;
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(name,email,country);
        userService.insertUser(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
