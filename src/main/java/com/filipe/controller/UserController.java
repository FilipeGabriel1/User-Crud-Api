package com.filipe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import com.filipe.ioc.SimpleIoCContainer;
import com.filipe.model.User;
import com.filipe.repository.GenericRepository;
import com.filipe.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        GenericRepository<User, Long> repo = new GenericRepository<>();
        SimpleIoCContainer container = new SimpleIoCContainer();
    container.registerBean((Class)GenericRepository.class, repo);
    container.registerBean((Class)UserService.class, new UserService(repo));
    userService = (UserService) container.getBean((Class)UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        try {
            if ("list".equals(action)) {
                Method method = UserService.class.getMethod("getAllUsers");
                List<User> users = (List<User>) method.invoke(userService);
                for (User u : users) {
                    out.println("<p>" + u.getId() + ": " + u.getName() + " - " + u.getEmail() + "</p>");
                }
                out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
            } else if ("get".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Method method = UserService.class.getMethod("getUser", Long.class);
                User user = (User) method.invoke(userService, id);
                out.println("<p>" + user.getId() + ": " + user.getName() + " - " + user.getEmail() + "</p>");
                out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        try {
            if ("create".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                Method method = UserService.class.getMethod("createUser", Long.class, String.class, String.class);
                method.invoke(userService, id, name, email);
                out.println("<p>User created!</p>");
                out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
            } else if ("update".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                Method method = UserService.class.getMethod("updateUser", Long.class, String.class, String.class);
                method.invoke(userService, id, name, email);
                out.println("<p>User updated!</p>");
                out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
            } else if ("delete".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                Method method = UserService.class.getMethod("deleteUser", Long.class);
                method.invoke(userService, id);
                out.println("<p>User deleted!</p>");
                out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='formulario.html' style='display:inline-block;margin-top:20px;color:#3498db;text-decoration:none;font-weight:bold;'>&larr; Voltar ao formulário</a>");
        }
    }
}
