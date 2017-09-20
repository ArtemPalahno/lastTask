package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.Controllers.AdminPage;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.GroupDao;
import ua.nure.palagno.db.entity.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlGroupDao implements GroupDao {
    private static final Logger log = Logger.getLogger(MySqlGroupDao.class);
    private Connection con;

    private static final String SQL__GET_GROUP_BY_ID = "SELECT * FROM groups where id=?";
    private static final String SQL__GET_GROUP = "SELECT * FROM groups ";


    public MySqlGroupDao(Connection con) {
        this.con = con;
    }


    @Override
    public Group get(int GroupId) {
        Group group = new Group();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL__GET_GROUP_BY_ID);
            ps.setInt(1, GroupId);
            rs = ps.executeQuery();
            if (rs.next()) {

                group = extractGroup(rs);
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
        return group;
    }

    public List<Group> getAll() {
        List<Group> list = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(SQL__GET_GROUP);
            while (rs.next()) {
                list.add(extractGroup(rs));
            }
        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);


        } finally {
            try {

                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return list;
    }

    private Group extractGroup(ResultSet rs) {
        Group group = new Group();
        try {

            group.setGroupName(rs.getString(Fields.GROUP__NAME));
            group.setId(rs.getInt(Fields.ENTITY__ID));

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        }
        return group;
    }
}
