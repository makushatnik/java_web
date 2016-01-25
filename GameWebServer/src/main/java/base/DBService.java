package base;

import jpa.UserDataSet;

import java.util.List;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public interface DBService {

    String getLocalStatus();

    void shutdown();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

}
