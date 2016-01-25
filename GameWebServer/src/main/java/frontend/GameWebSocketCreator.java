package frontend;

import base.AuthService;
import base.GameMechanics;
import base.WebSocketService;
import main.UserProfile;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Ageev Evgeny on 20.01.2016.
 */
public class GameWebSocketCreator implements WebSocketCreator {
    private AuthService authService;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;

    public GameWebSocketCreator(AuthService authService, GameMechanics gameMechanics, WebSocketService webSocketService) {
        this.authService = authService;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        String name = authService.getUserName(sessionId);
        //пользователь не авторизован
        if (name == null || name.equals("")) {
            //resp.sendRedirect("/index.html");
            return null;
        }
        return new GameWebSocket(name, gameMechanics, webSocketService);
    }
}
