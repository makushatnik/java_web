package jdbc;

import base.DBService;
import jpa.UserDataSet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 *
 * Подключение к БД устанавливаем один раз и храним в памяти ссылку.
 * Возможен пул подключений, в котором будут настройки нескольких подключений к БД.
 */
public class DBServiceImpl implements DBService {
    //private ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();
    private DBServiceImpl dbService;
    private static Connection conn;
    /*private DBServiceImpl(Properties props) {
        createConnections(props);
    }

    private createConnections(Properties props) {
        //props.size();
        int i=1;
        while (true) {
            String str = props.getProperty("driver" + i);
            if (str == null) break;
            Connection conn = getConnection(props, i);

            connections.put("conn"+i, conn);
            i++;
        }
    }*/
    public DBServiceImpl(Properties props)  throws SQLException, IOException {
        this.conn = getConnection(props);
    }

    public Connection getConnection(Properties props) throws SQLException, IOException
    {
        String driver = props.getProperty("jdbc.driver");
        //System.out.println("driver = " + driver);
        if (driver != null) System.setProperty("jdbc.driver", driver);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    public DBServiceImpl getInstance(Properties props)  throws SQLException, IOException {
    //public DBServiceImpl getInstance() {
        if (dbService == null) {
            dbService = new DBServiceImpl(props);
            //dbService = new DBServiceImpl();
        }
        return dbService;
    }
    //нет соединения - сервис не работает. надо перезапустить
    @Override
    public String getLocalStatus() {
        if (conn != null)
            return "worked";
        else
            return "closed";
    }

    @Override
    public void shutdown() {
        conn = null;
        dbService = null;
    }

    @Override
    public void save(UserDataSet dataSet) {

    }

    @Override
    public UserDataSet read(long id) {
        return null;
    }

    @Override
    public UserDataSet readByName(String name) {
        return null;
    }

    @Override
    public List<UserDataSet> readAll() {
        return null;
    }
}
