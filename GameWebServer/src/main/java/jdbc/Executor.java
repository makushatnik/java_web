package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class Executor {
    public static int execUpdate(Connection conn, String update) throws SQLException {
        Statement stat = conn.createStatement();
        stat.execute(update);
        int rows = stat.getUpdateCount();
        stat.close();
        return rows;
    }

    public static void execQuery(Connection conn, String query, ResultHandler handler) throws SQLException {
        Statement stat = conn.createStatement();
        stat.execute(query);
        ResultSet rs = stat.getResultSet();
        handler.handle(rs);
        rs.close();
        stat.close();
    }
}
