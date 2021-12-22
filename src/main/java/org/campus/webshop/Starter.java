package org.campus.webshop;

import org.campus.webshop.dao.jdbc.JdbcProductDao;
import org.campus.webshop.dao.jdbc.JdbcUserDao;
import org.campus.webshop.service.*;
import org.campus.webshop.web.filter.ContextTypeFilter;
import org.campus.webshop.web.filter.SecurityFilter;
import org.campus.webshop.web.servlet.LoginRequestServlet;
import org.campus.webshop.web.servlet.RegisterRequestServlet;
import org.campus.webshop.web.servlet.ShowAllReviewsRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Starter {
    public static void main(String[] args) throws Exception {

        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao jdbcUserDao = new JdbcUserDao();

        ProductService productService = new ProductService(jdbcProductDao);
        UserService userService = new UserService(jdbcUserDao);
        SecurityService securityService = new SecurityService(userService);

        ShowAllReviewsRequestServlet showAllReviewsRequestServlet = new ShowAllReviewsRequestServlet(productService);
        LoginRequestServlet loginRequestServlet = new LoginRequestServlet(securityService);
        RegisterRequestServlet registerRequestServlet = new RegisterRequestServlet(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addFilter(new FilterHolder(new ContextTypeFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(new SecurityFilter(securityService)), "/*", EnumSet.of(DispatcherType.REQUEST));

        context.addServlet(new ServletHolder(loginRequestServlet), "/login");
        context.addServlet(new ServletHolder(registerRequestServlet), "/registration");
        context.addServlet(new ServletHolder(showAllReviewsRequestServlet), "/products");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }

}
