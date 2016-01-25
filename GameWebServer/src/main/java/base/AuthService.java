package base;

import main.UserProfile;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public interface AuthService {
    String getUserName(String sessionId);

    void saveUserName(String sessionId, String name);

    boolean addUser(String name, UserProfile prof);

    void deleteUser(String sessionId);

    UserProfile getUser(String name);

    void addSession(String sessionId, UserProfile prof);

    UserProfile getSession(String sessionId);
}
