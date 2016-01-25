package admin;

import base.AuthService;
import main.UserProfile;
import utils.PageGenerator;
import utils.TimeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 *
 * Нужен только для отключения приложения на удаленном сервере.
 * Только авторизованный пользователь, обладающий правами админа, может его отключить.
 * Программно права админа не раздаются.
 *
 * Если не авторизован и не админ - редирект на главную.
 */
public class AdminPageServlet extends HttpServlet {
    private AuthService authService;

    public AdminPageServlet(AuthService authService) {
        this.authService = authService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile prof = authService.getSession(sessionId);
        //пользователь не авторизован
        if (prof == null) {
            response.sendRedirect("/index.html");
            return;
        }
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        String timeString = request.getParameter("shutdown");
        if (timeString != null) {
            int timeMS = Integer.valueOf(timeString);
            System.out.println("Server will be down after " + timeMS + " ms");
            TimeHelper.sleep(timeMS);
            System.out.println("Shutdown");
            System.exit(0);
        }
        pageVariables.put("status", "run");
        response.getWriter().println(PageGenerator.getPage("admin.html", pageVariables));
    }
}
