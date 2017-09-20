package ua.nure.palagno.db.dao.classes;

import ua.nure.palagno.db.dao.interfaces.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlDaoFactory implements DaoFactory {
    @Override
    public Connection getConnection() throws SQLException, NamingException {

        InitialContext initContext= new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/lastTask");
        Connection conn = ds.getConnection();
        return  conn ;
    }

    @Override
    public PublicationDao getPublicationDao(Connection connection) {
        return new MySqlPublicationDao(connection);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return new MySqlUserDao(connection);
    }

    @Override
    public GroupDao getGroupDao(Connection connection) {
        return new MySqlGroupDao(connection);
    }

    @Override
    public SubscriptionsDao getSubscriptionsDao(Connection connection) {
        return new MySqlSubscriptionsDao(connection);
    }

    @Override
    public StatusDao getStatusDao(Connection connection) {
        return new MySqlStatusDao(connection);
    }

    @Override
    public RoleDao getRoleDao(Connection connection) {
        return new MySqlRoleDao(connection);
    }
}
