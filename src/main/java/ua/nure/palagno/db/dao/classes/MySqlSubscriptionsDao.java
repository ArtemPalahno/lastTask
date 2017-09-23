package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.SubscriptionsDao;
import ua.nure.palagno.db.entity.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlSubscriptionsDao implements SubscriptionsDao {
    private static final Logger log = Logger.getLogger(MySqlSubscriptionsDao.class);
    /**
     * Sql query that get all user's subscriptions
     */
    private static final String SQL__SUBSCRIPTIONS_BY_USER_ID = "select bookID from subscription where userID = ?";
    /**
     * Sql query that get all subscriptions by userID and publicationID.
     */
    private static final String SQL__IS_USER_HAS_PUBLICATION = "SELECT * FROM subscription WHERE userID=? and bookID=?";
    /**
     * Sql query that insert user subscription.
     */
    private static final String SQL_ADD_USER_SUB = "INSERT INTO subscription (userID, bookID) " +
            "VALUES(?, ?)";
    private Connection con;

    public MySqlSubscriptionsDao(Connection con) {
        this.con = con;
    }

    @Override
    public List<Publication> getAllPublicationsByUserID(int UserId) {
        List<Publication> list = new ArrayList<>();
        PreparedStatement preSt = null;
        ResultSet rs = null;
        try {
            preSt = con.prepareStatement(SQL__SUBSCRIPTIONS_BY_USER_ID);
            preSt.setInt(1, UserId);
            rs = preSt.executeQuery();
            while (rs.next()) {
                list.add(new MySqlPublicationDao(MySQLConnectionUtils.getMySQLConnection()).get(rs.getInt(Fields.SUBSCRIPTION__BOOK_ID)));
            }


        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                preSt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return list;
    }

    public boolean IsUserHasPublication(int userID, int bookID) {
        boolean result = false ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL__IS_USER_HAS_PUBLICATION);
            ps.setInt(1,userID);
            ps.setInt(2,bookID);
            rs = ps.executeQuery();
            result = rs.next();

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        }
        finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return result;
    }
    public void Add(int userID , int bookID){
        PreparedStatement ps = null ;
        try{
            ps = con.prepareStatement(SQL_ADD_USER_SUB);
            ps.setInt(1,userID);
            ps.setInt(2,bookID);
            ps.executeUpdate();
        }catch (SQLException e){
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        }finally {
            try {
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }

    }
}
