package org.campus.webshop.web.filter;


import org.campus.webshop.service.SecurityService;
import org.campus.webshop.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {
    SecurityService securityService;
    private List<String> allowedPaths = List.of("/login");

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token = WebUtils.getUserToken(httpRequest.getCookies());
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.startsWith("/registration")) {
            if (securityService.isUserExist(httpRequest.getParameter("email"))) {
                httpResponse.sendRedirect("/registration");
                return;
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        for (String path : allowedPaths) {
            if (requestURI.startsWith(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        if (securityService.isAuth(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}



