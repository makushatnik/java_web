package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public interface TResultHandler<T> {
    T handle(ResultSet result) throws SQLException;
}
