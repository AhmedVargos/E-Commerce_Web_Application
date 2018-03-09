package com.apicompany.e.commerceapplication.view.filter;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CategoryDAO;
import com.apicompany.e.commerceapplication.dal.models.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "LoadCategoriesFilter")
public class LoadCategoriesFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categoryList =  categoryDAO.getAllCatagory();
        session.setAttribute("categoryList",categoryList);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
