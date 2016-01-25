package main;

import admin.AdminPageServlet;
import base.AuthService;
import base.GameMechanics;
import base.WebSocketService;
import base.impl.AuthServiceImpl;
import base.impl.GameMechanicsImpl;
import base.impl.WebSocketServiceImpl;
import frontend.*;
import io.sax.SAXReader;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties props = init();

        System.out.println("Property port = " + props.getProperty("port"));
        System.out.println("Property driver = " + props.getProperty("driver"));
        System.out.println("Property url = " + props.getProperty("url"));
        System.out.println("Property username = " + props.getProperty("username"));
        System.out.println("Property password = " + props.getProperty("password"));
        /*AuthService accountService = new AuthServiceImpl();

        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);
        AuthService authService = new AuthServiceImpl();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        //context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(new AdminPageServlet(accountService)), "/admin");
        context.addServlet(new ServletHolder(new ExitServlet(accountService)), "/exit");

        context.addServlet(new ServletHolder(new SingInServlet(accountService)), "/auth/signin");
        context.addServlet(new ServletHolder(new SingUpServlet(accountService)), "/auth/signup");

        context.addServlet(new ServletHolder(new WebSocketGameServlet(authService, gameMechanics, webSocketService)), "/gameplay");
        context.addServlet(new ServletHolder(new GameServlet(gameMechanics, authService)), "/game.html");

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        rHandler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{rHandler, context});

        Server server = new Server(Integer.valueOf(props.getProperty("port")));
        server.setHandler(handlers);
        server.start();

        gameMechanics.run();*/
        //throw new NullPointerException();
    }

    public static Properties init() {
        //Properties props = new Properties();
        String root = "data";
        Properties props = (Properties) SAXReader.readXML(root+"/GMConfig.xml");
        return props;
    }
}
