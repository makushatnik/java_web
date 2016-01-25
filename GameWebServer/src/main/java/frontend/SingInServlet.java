package frontend;

import base.AuthService;
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
 * Created by Ageev Evgeny on 13.01.2016.
 */
public class SingInServlet extends HttpServlet {
    private AuthService accountService;

    public SingInServlet(AuthService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        Map<String, Object> pageVariables = new HashMap<>();
        if (accountService.getUser(name) != null) {
            UserProfile prof = accountService.getUser(name);
            if (prof.getLogin().equals(name) && prof.getPassword().equals(pass)) {
                accountService.addSession(request.getSession().getId(), prof);
                pageVariables.put("status", "Login passed");
                pageVariables.put("sessionId", request.getSession().getId());
                response.getWriter().println(PageGenerator.getPage("auth.html", pageVariables));
            } else {
                pageVariables.put("status", "Wrong login/password");
                pageVariables.put("name", "");
                pageVariables.put("password", "");
                response.getWriter().println(PageGenerator.getPage("signup.html", pageVariables));
            }
        } else {
            pageVariables.put("status", "You not registered yet");
            pageVariables.put("name", "");
            pageVariables.put("password", "");
            response.getWriter().println(PageGenerator.getPage("signup.html", pageVariables));
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
