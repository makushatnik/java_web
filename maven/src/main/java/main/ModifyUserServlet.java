package main;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
@WebServlet("/modify")
public class ModifyUserServlet extends HttpServlet {
 
    @EJB
    private UserDao userDao;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                                            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
 
        String strId = req.getParameter("edit");
        if(strId != null && !strId.equals("")){
            long id = Long.valueOf(strId);
            User user = userDao.findById(id);
 
            req.setAttribute("user", user);
        }
 
        req.getRequestDispatcher("/modify.jsp").forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
                                             throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
 
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        int age = Integer.valueOf(req.getParameter("age"));
 
        User user = null;
        String strId = req.getParameter("id");
        if(strId != null && !strId.equals("")){
            long id = Long.valueOf(strId);
            user = userDao.findById(id);
            if (user == null) user = new User(name, lastName, age);
            else {
            	user.setAge(age);
            	user.setLastName(lastName);
            	user.setName(name);
            }
        }
        else {
        	user = new User(name, lastName, age);
        }
        userDao.save(user);
 
        resp.sendRedirect("list");
    }
}