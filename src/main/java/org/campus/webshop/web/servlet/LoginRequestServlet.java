package org.campus.webshop.web.servlet;

import org.campus.webshop.entity.Product;
import org.campus.webshop.entity.User;
import org.campus.webshop.service.SecurityService;
import org.campus.webshop.service.UserService;
import org.campus.webshop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginRequestServlet extends HttpServlet {
    SecurityService securityService;

    public LoginRequestServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("signin.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String token = securityService.signIn(email, password);
            Cookie cookie = new Cookie("user-token", token);
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "Incorrect email or password!";
            String page = pageGenerator.getPage("signin.html", errorMessage);
            resp.getWriter().write(page);
        }
    }
}
