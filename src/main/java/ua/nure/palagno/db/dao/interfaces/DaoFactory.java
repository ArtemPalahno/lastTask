package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.dao.interfaces.*;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by palah on 05.09.2017.
 */
public interface DaoFactory {
    /**
     * Возвращает подключение к базе данных
     */
    public Connection getConnection() throws SQLException, NamingException;

    public PublicationDao getPublicationDao(Connection connection);

    public UserDao getUserDao(Connection connection);

    public GroupDao getGroupDao(Connection connection);

    public SubscriptionsDao getSubscriptionsDao(Connection connection);

    public StatusDao getStatusDao(Connection connection);

    public RoleDao getRoleDao(Connection connection);


}


