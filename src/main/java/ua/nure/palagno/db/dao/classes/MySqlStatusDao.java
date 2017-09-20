package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.StatusDao;
import ua.nure.palagno.db.entity.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlStatusDao implements StatusDao {
    Connection con;
    private static final Logger log = Logger.getLogger(MySqlStatusDao.class);
    private static final String SQL__GET_STATUS_BY_ID = "SELECT * FROM status where id=?";
    private static final String SQL__GET_STATUS_ID = "SELECT ID FROM status where statusName =?";


    public MySqlStatusDao(Connection con) {
        this.con = con;
    }

    public int get(String statusName) {
        int id = 0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(SQL__GET_STATUS_ID);
            ps.setString(1, statusName);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(Fields.ENTITY__ID);
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }


        return id;
    }

    @Override
    public Status get(int key) {
        Status status = new Status();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(SQL__GET_STATUS_BY_ID);
            ps.setInt(1, key);
            rs = ps.executeQuery();
            if (rs.next()) {
                status.setId(rs.getInt(Fields.ENTITY__ID));
                status.setStatusName(rs.getString(Fields.STATUS__NAME));
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return status;
    }
}
