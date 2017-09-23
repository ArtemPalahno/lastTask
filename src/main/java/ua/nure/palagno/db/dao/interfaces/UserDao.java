package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artem_Palagno on 05.09.2017.
 */
public interface UserDao {
    /**
     * Insert new publication into database
     *
     * @param user instance of User entity
     */
    void create(User user);

    /**
     * get Publication from database
     *
     * @param id User identifier
     * @return instance of User entity
     */

    User get(int id);

    /**
     * get Publication from database
     *
     * @param email User identifier
     * @return instance of User entity
     */
    User get(String email);

    /**
     * Get all users from database
     *
     * @return List which contains all users
     */
    List<User> getAll();

    /**
     * Change user status
     *
     * @param user User instance that will be changed
     */
    void changeStatus(User user);

    /**
     * to pay (cash) into user account
     *
     * @param user User instance that will be money update
     * @throws SQLException
     */
    void updateMoney(User user) throws SQLException;

    /**
     * Checking is user existing
     *
     * @param email    user email
     * @param password user password
     * @return true - user exist , false - user doesn't exist
     * @throws SQLException
     */
    boolean isExist(String email, String password) throws SQLException;

    /**
     * get Publication from database
     *
     * @param email    User identifier
     * @param password User identifier
     * @return instance of User entity
     */
    User get(String email, String password);
}
