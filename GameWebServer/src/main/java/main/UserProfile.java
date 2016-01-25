package main;

/**
 * Created by Ageev Evgeny on 23.01.2016.
 */
public class UserProfile {
    private String login;
    private String email;
    private String password;

    public UserProfile(String name, String password) {
        this.login = name;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}