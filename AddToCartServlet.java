package com.cdesign.bookstore.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.rmi.*;
import javax.ejb.*;

import org.w3c.dom.*;

import com.cdesign.bookstore.*;
import com.cdesign.bookstore.model.*;

/**
 * @version 1.0
 * @author Ageev Evgeny
 */
public class AddToCartServlet extends XMLServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean reg = true;
        
        Document doc = getDocumentBuilder().newDocument();
        HttpSession session = request.getSession();
        //Register.isReg(session.getAttribute("login"))
        //if (!reg) return;
        ShoppingCart shopCart = (ShoppingCart)session.getAttribute("cart");
        String isbn = request.getParameter("isbn");
        try {
            InitialContext ic = new InitialContext();
            if (shopCart == null)
            {
                Object obj = ic.lookup("java:comp/env/ejb/ShoppingCart");
                
                ShoppingCartHome shopCartHome = (ShoppingCartHome)
                        PortableRemoteObject.narrow(obj, ShoppingCartHome.class);
                shopCart = shopCartHome.create();
                session.setAttribute("cart", shopCart);
            }
            shopCart.addProduct(isbn);
            //response.sendRedirect("ViewCart");
        } catch (NamingException e) {
            e.printStackTrace();
            String error = "The ShoppingCart JNDI was not found in JNDI directory.";
            doc.appendChild(buildErrorMessage(doc, error));
            writeXML(request, response, doc);
        } catch (CreateException e) {
            e.printStackTrace();
            String error = "ShoppingCart could not be created.";
            doc.appendChild(buildErrorMessage(doc, error));
            writeXML(request, response, doc);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            doc.appendChild(buildErrorMessage(doc, e.getMessage()));
            writeXML(request, response, doc);
        } 
    }

    @Override
    public String getServletInfo() {
        return "Add to cart servlet";
    }
}
