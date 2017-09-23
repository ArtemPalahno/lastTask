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
     * @return PublicationDAO
     */
    PublicationDao getPublicationDao();

    /**
     * @return UserDAO
     */
    UserDao getUserDao();

    /**
     * @return GroupDAO
     */
    GroupDao getGroupDao();

    /**
     * @return SubscriptionsDAO
     */
    SubscriptionsDao getSubscriptionsDao();

    /**
     * @return StatusDAO
     */
    StatusDao getStatusDao();

    /**
     * @return RoleDAO
     */
    RoleDao getRoleDao();


}


