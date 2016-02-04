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
 
    // Аннотация говорит о том,
    // что данный объект будет инициализирован
    // контейнером через DI
    @EJB
    private UserDao userDao;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                                                 throws ServletException, IOException {
        // Получаем список пользователей
        List<User> allUser = userDao.getAll();
 
        // добавляем полученный список в request,
        // который отправится на jsp 
        req.setAttribute("users", allUser);
 
        // отправляем request на jsp
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
 
    }
}