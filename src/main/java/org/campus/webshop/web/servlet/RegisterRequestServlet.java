package org.campus.webshop.web.servlet;

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

public class RegisterRequestServlet extends HttpServlet {
    SecurityService securityService;

    public RegisterRequestServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("signup.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        try {
            User user = User.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .firstName(req.getParameter("firstname"))
                    .lastName(req.getParameter("lastname"))
                    .build();
            String token = securityService.signUp(user);

            Cookie cookie = new Cookie("user-token", token);
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "Error register account!";
            String page = pageGenerator.getPage("signup.html", errorMessage);
            resp.getWriter().write(page);
        }

    }
}
