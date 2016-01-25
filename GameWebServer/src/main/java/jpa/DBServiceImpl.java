package jpa;

import base.DBService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class DBServiceImpl implements DBService {
    private SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(UserDataSet.class);

        conf.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        conf.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
        conf.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/game");
        conf.setProperty("hibernate.connection.username","dbuser");
        conf.setProperty("hibernate.connection.password","s3cret");
        conf.setProperty("hibernate.show_sql","true");
        conf.setProperty("hibernate.hbm2ddl.auto","create");

        sessionFactory = createSessionFactory(conf);
    }

    /*public void config(Properties props) {
        Configuration conf = new Configuration();
        conf.setProperties(props);

        sessionFactory = createSessionFactory(conf);
    }*/

    private SessionFactory createSessionFactory(Configuration conf) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(conf.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return conf.buildSessionFactory(serviceRegistry);
    }

    public String getLocalStatus() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        String status = trx.getLocalStatus().toString();
        session.close();
        return status;
    }

    public void shutdown() {
        sessionFactory.close();
    }

    public UserDataSet read(long id) {
        Session session = sessionFactory.openSession();
        UserDataSetDAO dao = new UserDataSetDAO(session);
        UserDataSet dataSet = dao.read(id);
        session.close();
        return dataSet;
    }

    public void save(UserDataSet dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        UserDataSetDAO dao = new UserDataSetDAO(session);
        dao.save(dataSet);
        //session.save(dataSet);
        trx.commit();
        //session.close();
    }

    public UserDataSet read2(long id) {
        Session session = sessionFactory.openSession();
        return (UserDataSet) session.load(UserDataSet.class, id);
    }

    public UserDataSet readByName(String name) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return (UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<UserDataSet> readAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return (List<UserDataSet>) criteria.list();
    }
}
