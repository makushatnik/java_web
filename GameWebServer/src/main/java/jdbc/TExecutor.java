package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class TExecutor {
    public <T> T execQuery(Connection conn, String query, TResultHandler<T> handler) throws SQLException {
        Statement stat = conn.createStatement();
        stat.execute(query);
        ResultSet rs = stat.getResultSet();
        T value = handler.handle(rs);
        rs.close();
        stat.close();
        return value;
    }
}
