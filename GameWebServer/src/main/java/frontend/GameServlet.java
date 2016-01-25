package frontend;

import base.AuthService;
import base.GameMechanics;
import main.UserProfile;
import utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ageev Evgeny on 20.01.2016.
 */
public class GameServlet extends HttpServlet {
    private AuthService authService;
    private GameMechanics gameMechanics;

    public GameServlet(GameMechanics gameMechanics, AuthService authService) {
        this.authService = authService;
        this.gameMechanics = gameMechanics;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        //String sessionId = request.getParameter("sessionId");
        String sessionId = request.getSession().getId();
        UserProfile prof = authService.getSession(sessionId);
        //пользователь не авторизован
        if (prof == null) {
            response.sendRedirect("/index.html");
            return;
        }
        //String name = request.getParameter("name");
        //String safeName = name == null ? "NoName" : name;
        //authService.saveUserName(request.getSession().getId(), name);
        authService.saveUserName(sessionId, prof.getLogin());
        pageVariables.put("myName", prof.getLogin() == null ? "NoName" : prof.getLogin());

        response.getWriter().println(PageGenerator.getPage("game.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
