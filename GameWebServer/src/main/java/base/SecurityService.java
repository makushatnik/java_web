package base;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public interface SecurityService {
    void writeIP();

    boolean isBanned();

    String cryptPass(String pass);
}
