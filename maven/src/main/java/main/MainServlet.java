package main;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
 
@WebServlet(urlPatterns = {"/", "/list", "index.html", "index.htm", "index.jsp"})
public class MainServlet extends HttpServlet{
 
    // ��������� ������� � ���,
    // ��� ������ ������ ����� ���������������
    // ����������� ����� DI
    @EJB
    private UserDao userDao;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                                                 throws ServletException, IOException {
        // �������� ������ �������������
        List<User> allUser = userDao.getAll();
 
        // ��������� ���������� ������ � request,
        // ������� ���������� �� jsp 
        req.setAttribute("users", allUser);
 
        // ���������� request �� jsp
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
 
    }
}