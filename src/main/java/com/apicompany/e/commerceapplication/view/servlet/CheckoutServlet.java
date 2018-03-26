package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CheckoutController;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;

import com.apicompany.e.commerceapplication.dal.dao.daoint.CartDAOInt;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.entities.CartItem;
import com.apicompany.e.commerceapplication.dal.models.CheckoutModel;
import com.apicompany.e.commerceapplication.dal.models.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
           HttpSession usersession = request.getSession(false);
        User User=  (User) usersession.getAttribute("userObj");
       int UserId = User.getUserId();
      //sint UserId = 1;
        PrintWriter out = response.getWriter();
        CheckoutController mycontroller = new CheckoutController();
        if (mycontroller.checkUserFound(UserId)) {
            int totalPrice = mycontroller.CalaulatetotalPrice(UserId);
            User currentUser = mycontroller.getUser(UserId);
            String username = " ";
            String address = " ";
            if (currentUser != null) {
                username = currentUser.getUserName();
                address = currentUser.getAddress();
            }
            response.setContentType("application/json");
//           HttpSession ToptalPriceSession = request.getSession(true);
//           ToptalPriceSession.setAttribute("TotalPrice", totalPrice);
//           ToptalPriceSession.setAttribute("userName",username );
//           ToptalPriceSession.setAttribute("adress", address);
//           JsonObjectBuilder myjson=Json.createObjectBuilder();
//           myjson.add("totalPrice",totalPrice);
//           myjson.add("userCurrent",username);
//           myjson.add("userAddress",address);
            CheckoutModel model = new CheckoutModel();
            model.setTotalPrice(totalPrice);
            model.setUserCurrent(username);
            model.setUserAddress(address);
            Gson mygson = new Gson();
            // String mydata="{totalPrice:"+totalPrice+",userCurrent:"+username+",userAddress: test}";
            out.write(mygson.toJson(model));
            // response.sendRedirect("shop-checkout.jsp");   
        } else {
            response.sendRedirect("shop-login.jsp");

        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession usersession = request.getSession(false);
        User User=  (User) usersession.getAttribute("userObj");
       int UserId = User.getUserId();
      //int UserId = 1;
        CartDAOInt card=new CartDAO();
        Cart currentCart= card.getCartByUserID(UserId);
        
        PrintWriter out = response.getWriter();
        CheckoutController mycontroller = new CheckoutController();
        int totalPrice = mycontroller.CalaulatetotalPrice(UserId);
        if (totalPrice > 0) {
            boolean checkResult = mycontroller.checkLimitRange(UserId, totalPrice);

            if (checkResult) {
                int CreatOrder = mycontroller.MakeOrder(UserId);

                if (CreatOrder==1) {
                    boolean check = mycontroller.clearCard(UserId);
                    //Ahmed Edit to empty the cart itmes
                    Cart myCart = (Cart)usersession.getAttribute("cart");
                    myCart.getCartItems().clear();
                    usersession.setAttribute("cart", myCart);
                    out.write("You create order successfully and contact with you for payment");
                }
                else if(CreatOrder==-1){
                     ArrayList<CartItem> totalItemsOfCards = currentCart.getCartItems();
                     for(int i=0;i<totalItemsOfCards.size();i++)
                     {
                        if(totalItemsOfCards.get(i).getProduct().getQuantity()<totalItemsOfCards.get(i).getQuantity())
                        {
                          out.println("the product "+totalItemsOfCards.get(i).getProduct().getProductName()+" is out of stoke ");  
                        }
                     }
                    
                  
                } 
                

            } else {
                out.write(" the total price more than your credit  Limit");

            }
        } else {
            out.write("your cart is Empty ");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
