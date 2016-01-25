package mechanics;

import base.GameUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class GameSession {
    static final Logger logger = LogManager.getLogger(GameSession.class);
    private final long startTime;
    private final GameUser first;
    private final GameUser second;

    private Map<String, GameUser> users = new HashMap<>();

    public GameSession(String user1, String user2) {
        startTime = new Date().getTime();
        GameUser gameUser1 = new GameUser(user1);
        gameUser1.setEnemyName(user2);

        GameUser gameUser2 = new GameUser(user2);
        gameUser1.setEnemyName(user1);

        users.put(user1, gameUser1);
        users.put(user2, gameUser2);

        this.first = gameUser1;
        this.second = gameUser2;
    }

    public GameUser getFirst() {
        return first;
    }

    public GameUser getSecond() {
        return second;
    }

    public GameUser getSelf(String user) {
        if (users.containsKey(user)) {
            return users.get(user);
        }
        else {
            logger.error("User - " + user + " - not exists!");
        }
        return null;
    }

    public GameUser getEnemy(String user) {
        if (users.containsKey(user)) {
            String enemyName = users.get(user).getEnemyName();
            if (users.containsKey(enemyName)) {
                return users.get(enemyName);
            }
            else {
                logger.error("User - " + user + " - not exists!");
            }
        }
        else {
            logger.error("User - " + user + " - not exists!");
        }
        return null;
    }

    public long getSessionTime() {
        long endTime = new Date().getTime();
        return endTime - startTime;
    }

    public boolean isFirstWin() {
        boolean result = true;
        if (second.getMyScore() > first.getMyScore()) {
            result = false;
        }
        return result;
    }
}
