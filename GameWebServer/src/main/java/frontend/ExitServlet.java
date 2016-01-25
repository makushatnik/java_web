package frontend;

import base.AuthService;
import main.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class ExitServlet extends HttpServlet {
    private AuthService authService;

    public ExitServlet(AuthService authService) {
        this.authService = authService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile prof = authService.getSession(sessionId);
        if (prof != null) {
            authService.deleteUser(sessionId);
            response.sendRedirect("index.html");
        }
        response.sendRedirect("error.html");
    }
}
