package ua.nure.palagno.db.dao.classes;

import ua.nure.palagno.db.dao.interfaces.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 08.09.2017.
 * Realization of Singleton Pattern
 */
public class MySqlDaoFactory implements DaoFactory {
    private static MySqlUserDao userDao;
    private static MySqlPublicationDao publDao;
    private static MySqlGroupDao groupDao;
    private static MySqlSubscriptionsDao subsDao;
    private static MySqlStatusDao statusDao ;
    private static MySqlRoleDao roleDao ;


    private Connection getConnection() throws SQLException, NamingException {

        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/lastTask");
        Connection conn = ds.getConnection();
        return conn;
    }


    @Override
    public  PublicationDao getPublicationDao() {
        if(publDao==null){
            try {
                publDao = new MySqlPublicationDao(getConnection()) ;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return publDao;
    }

    @Override
    public UserDao getUserDao() {
        if(userDao==null){
            try {
                userDao = new MySqlUserDao(getConnection()) ;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return userDao;
    }


    @Override
    public GroupDao getGroupDao() {
        if (groupDao==null){
            try {
                groupDao = new MySqlGroupDao(getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return groupDao;
    }


    @Override
    public SubscriptionsDao getSubscriptionsDao() {
        if(subsDao==null){
            try {
                subsDao=new MySqlSubscriptionsDao(getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return subsDao;
    }


    @Override
    public StatusDao getStatusDao() {
        if (statusDao==null){
            try {
                statusDao=new MySqlStatusDao(getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return statusDao ;
    }


    @Override
    public RoleDao getRoleDao() {
        if (roleDao==null){
            try {
                roleDao=new MySqlRoleDao(getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return roleDao ;
    }

}
