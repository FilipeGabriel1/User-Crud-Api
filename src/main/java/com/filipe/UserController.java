package com.filipe;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        SimpleIoCContainer container = new SimpleIoCContainer();
        GenericRepository<User, Long> repo = new GenericRepository<>();
        container.registerBean(GenericRepository.class, repo);
        container.registerBean(UserService.class, new UserService(repo));
        userService = container.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        try {
            if (action == null || action.equals("list")) {
                java.lang.reflect.Method method = UserService.class.getMethod("getAllUsers");
                java.util.List<User> users = (java.util.List<User>) method.invoke(userService);
                out.println("<h2>Users:</h2>");
                for (User u : users) {
                    out.println(u.getId() + ": " + u.getName() + " (" + u.getEmail() + ")<br>");
                }
            } else if (action.equals("get")) {
                Long id = Long.valueOf(req.getParameter("id"));
                java.lang.reflect.Method method = UserService.class.getMethod("getUser", Long.class);
                User user = (User) method.invoke(userService, id);
                if (user != null) {
                    out.println(user.getId() + ": " + user.getName() + " (" + user.getEmail() + ")");
                } else {
                    out.println("User not found");
                }
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        try {
            if (action != null) {
                switch (action) {
                    case "create": {
                        Long id = Long.valueOf(req.getParameter("id"));
                        String name = req.getParameter("name");
                        String email = req.getParameter("email");
                        java.lang.reflect.Method method = UserService.class.getMethod("createUser", Long.class, String.class, String.class);
                        method.invoke(userService, id, name, email);
                        out.println("User created");
                        break;
                    }
                    case "update": {
                        Long id = Long.valueOf(req.getParameter("id"));
                        String name = req.getParameter("name");
                        String email = req.getParameter("email");
                        java.lang.reflect.Method method = UserService.class.getMethod("updateUser", Long.class, String.class, String.class);
                        method.invoke(userService, id, name, email);
                        out.println("User updated");
                        break;
                    }
                    case "delete": {
                        Long id = Long.valueOf(req.getParameter("id"));
                        java.lang.reflect.Method method = UserService.class.getMethod("deleteUser", Long.class);
                        method.invoke(userService, id);
                        out.println("User deleted");
                        break;
                    }
                    default:
                        out.println("Invalid action");
                }
            } else {
                out.println("No action specified");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
