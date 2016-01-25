package base.impl;

import base.AuthService;
import main.UserProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class AuthServiceImpl implements AuthService {
    private Map<String, UserProfile> users = new HashMap<>();
    private Map<String, UserProfile> sessions = new HashMap<>();

    @Override
    public String getUserName(String sessionId) {
        return sessions.get(sessionId).getLogin();
    }

    @Override
    public void saveUserName(String sessionId, String name) {
        UserProfile user = new UserProfile(name, "");
        sessions.put(sessionId, user);
    }

    public boolean addUser(String login, UserProfile prof) {
        if (users.containsKey(login))
            return false;
        users.put(login, prof);
        return true;
    }

    public void deleteUser(String sessionId) {
        UserProfile prof = getSession(sessionId);
        sessions.remove(sessionId);
        users.remove(prof.getLogin());
    }

    public void addSession(String sessionId, UserProfile prof) {
        sessions.put(sessionId, prof);
    }

    public UserProfile getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public UserProfile getUser(String name) {
        return users.get(name);
    }
}
