package main;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/show")
public class ShowAll extends HttpServlet{
    @EJB
    private UserDao dao;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                   throws ServletException, IOException {
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        List<User> users = dao.findAll();
 
        if (users == null || users.isEmpty()){
            writer.write("You have no users");
        }else {
            for (User user : users){
                writer.write(user.toString() + "<br/>");
            }
        }
        writer.close();
    }
}