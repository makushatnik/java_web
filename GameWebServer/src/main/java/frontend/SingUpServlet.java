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
public class SingUpServlet extends HttpServlet {
    private AuthService accountService;

    public SingUpServlet(AuthService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        Map<String, Object> pageVariables = new HashMap<>();
        if (accountService.addUser(name, new UserProfile(name, pass))) {
            pageVariables.put("status", "New user created");
        } else {
            pageVariables.put("status", "User with name: " + name + " already exists");
        }
        pageVariables.put("name", name);
        pageVariables.put("pass", pass);

        response.getWriter().println(PageGenerator.getPage("signup.html", pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
