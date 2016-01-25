package jpa;

import java.sql.SQLException;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */

public interface UsersDAO {

    UserDataSet get(long id) throws SQLException;

    UserDataSet getByName(String name) throws SQLException;

    void add(UserDataSet dataSet) throws SQLException;

    void delete(long id) throws SQLException;

}
