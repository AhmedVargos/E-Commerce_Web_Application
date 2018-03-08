package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CheckoutController;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession usersession=request.getSession(false);
       // int UserId= (int) usersession.getAttribute("userid");
       int UserId=1;
        CheckoutController mycontroller = new CheckoutController();
         if( mycontroller.checkUserFound(UserId))
         {
           int totalPrice=mycontroller.CalaulatetotalPrice(UserId);
             User currentUser = mycontroller.getUser(UserId);
             String username="B";
             String address="R";
             if(currentUser!=null)
             {
              username=currentUser.getUserName();
               address = currentUser.getAddress();
             }
           HttpSession ToptalPriceSession = request.getSession(true);
           ToptalPriceSession.setAttribute("TotalPrice", totalPrice);
           ToptalPriceSession.setAttribute("userName",username );
           ToptalPriceSession.setAttribute("adress", address);
           response.sendRedirect("shop-checkout.jsp");   
         }
         else
         {
           response.sendRedirect("shop-login.jsp"); 
          
         }   
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession usersession=request.getSession(false);
       // int UserId= (int) usersession.getAttribute("userid");
       int UserId=1;
        CheckoutController mycontroller = new CheckoutController();
          int totalPrice=mycontroller.CalaulatetotalPrice(UserId);
          boolean checkResult =mycontroller.checkLimitRange(UserId, totalPrice);
          PrintWriter out=response.getWriter();
          if(checkResult)
          { 
           boolean CreatOrder =mycontroller.MakeOrder(UserId);
           
           {           
            if(CreatOrder)
            {
            boolean check=mycontroller.clearCard(UserId);
              out.write("You create order successfuly and contact with you for payment"); 
            }
            
           }
           
          }
          else
          {
          out.write("the total price more than your credit  Limit sorry :( increase your creadit");
          
          }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
