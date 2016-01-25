package base;

/**
 * Created by Ageev Evgeny on 20.01.2016.
 */
public interface GameMechanics {

    void addUser(String user);

    void incrementScore(String user);

    void run();
}
