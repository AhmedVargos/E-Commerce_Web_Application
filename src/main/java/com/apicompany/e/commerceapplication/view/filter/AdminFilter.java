package com.apicompany.e.commerceapplication.view.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session.getAttribute("loggedin") != null) {
            boolean isLoggedIn = (boolean) session.getAttribute("loggedin");
            if (isLoggedIn == true) {
                if (session.getAttribute("isAdmin") != null) {
                    boolean isAdmin = (boolean) session.getAttribute("isAdmin");
                    if (isAdmin == true) {
                        chain.doFilter(req, resp);
                    }else {
                        response.sendRedirect("../shop-full-width.jsp");
                    }
                }
            } else {
                response.sendRedirect("../shop-login.jsp");
            }
        } else {
            response.sendRedirect("../shop-login.jsp");
        }

    }



    public void init(FilterConfig config) throws ServletException {

    }

}
