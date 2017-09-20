package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.UserDao;
import ua.nure.palagno.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Palagno on 05.09.2017.
 */
public class MySqlUserDao implements UserDao {
    private static final Logger log = Logger.getLogger(MySqlUserDao.class);

    private Connection connection;

    private static final String SQL__GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_CREATE_USER = "INSERT INTO users (Name, Surname, Email, Password , Session) " +
            "VALUES(?, ?, ?, ?, ?)";
    private static final String SQL__BLOCK_UNBLOCK_USER = "UPDATE users SET Status=? WHERE ID=?";
     private static final String SQL__UPDATE_USER = "UPDATE users SET Money = ? WHERE ID=?";
    private static final String SQL__FIND_USER_BY_ID = "SELECT  * FROM users WHERE ID=?";
    private static final String SQL__FIND_USER_BY_EMAIL = "SELECT  * FROM users WHERE Email=?";

    private static final String SQL__IS_USER_EXIST = "SELECT * FROM users WHERE Email=? and Password=?";
    private static final String SQL__FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM users WHERE Email=? and Password=?";

    public MySqlUserDao(Connection connection) {
        this.connection = connection;
    }

    public User get(String email, String password) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL__FIND_USER_BY_EMAIL_PASSWORD);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
            user = extractUser(rs);}

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
        }
        finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }

        return user;
    }

    public boolean isExist(String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL__IS_USER_EXIST);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            result = rs.next();


        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
        return result;
    }

    public void create(User user) {
        int counter = 1;
        try {
            System.out.println(5);
            PreparedStatement stmt = connection.prepareStatement(SQL_CREATE_USER);
            stmt.setString(counter++, user.getName());
            stmt.setString(counter++, user.getSurname());
            stmt.setString(counter++, user.getEmail());
            stmt.setString(counter++, user.getPassword());
            stmt.setString(counter, user.getSession());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(6);
            e.printStackTrace();
        } finally {
            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }

    }

    public User get(int ID) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL__FIND_USER_BY_ID);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if(rs.next()) {
                user = extractUser(rs);
            }

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
        return user;
    }
    public User get(String email) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()) {
                user = extractUser(rs);
            }

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
        return user;
    }

    public void update(User user) {

    }
    public void updateMoney(User user) throws SQLException {
        PreparedStatement ps = null ;
        try{
            ps = connection.prepareStatement(SQL__UPDATE_USER);
            ps.setDouble(1,user.getMoney());
            ps.setDouble(2,user.getId());
            ps.executeUpdate();

        }catch (SQLException e){
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
            throw e ;


        }finally {
            try {
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
    }
    public void delete(User user) {

    }

    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

           try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(SQL__GET_ALL_USERS);
            while (rs.next()){

                usersList.add(extractUser(rs));}
        } catch (SQLException e) {

               MySQLConnectionUtils.rollbackQuietly(connection);
               log.debug("SQL exception", e);
        } finally {
               try {
                   stmt.close();
                   rs.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
        return usersList;
    }


    private User extractUser(ResultSet rs) {
        User user = new User();
        try {


            user.setId(rs.getInt(Fields.ENTITY__ID));
            user.setSurname(rs.getString(Fields.USER__LAST_NAME));
            user.setName(rs.getString(Fields.USER__FIRST_NAME));
            user.setPassword(rs.getString(Fields.USER__PASSWORD));
            user.setEmail(rs.getString(Fields.USER__EMAIL));
            user.setMoney(rs.getDouble(Fields.USER__MONEY));
            user.setRole(rs.getInt(Fields.USER__ROLE_ID));
            user.setStatus(rs.getInt(Fields.USER__STATUS_ID));
        } catch (SQLException e) {

            log.debug("SQL exception", e);
        }
        return user;
    }

    @Override
    public void changeStatus(User user) {
        PreparedStatement stmt = null ;
        try {

            stmt = connection.prepareStatement(SQL__BLOCK_UNBLOCK_USER);
            Connection daoCon = MySQLConnectionUtils.getMySQLConnection();

            if (!user.isBlocked()) {
               // System.out.println(new MySqlStatusDao(connection).get("blocked"));
                stmt.setInt(1, new MySqlStatusDao(daoCon).get("blocked"));
                stmt.setInt(2, user.getId());
            }
             else  {
                //System.out.println(new MySqlStatusDao(connection).get("valid"));
                stmt.setInt(2, user.getId());
                stmt.setInt(1, new MySqlStatusDao(daoCon).get("valid"));
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(connection);
            log.debug("SQL exception", e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            MySQLConnectionUtils.closeAndCommitQuietly(connection);
        }
    }


}
