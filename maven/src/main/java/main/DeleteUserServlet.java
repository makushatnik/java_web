package main;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
 
    @EJB
    private UserDao userDao;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                                            throws ServletException, IOException {
    	String strId = req.getParameter("id");
        if(strId != null && !strId.equals("")){
            long id = Long.valueOf(strId);
            User user = userDao.findById(id);
            if (user != null) {
            	userDao.delete(user);
            }
        }
        resp.sendRedirect("list");
    }
}