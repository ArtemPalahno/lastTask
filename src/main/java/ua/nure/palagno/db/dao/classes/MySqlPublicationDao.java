package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.PublicationDao;
import ua.nure.palagno.db.entity.Publication;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlPublicationDao implements PublicationDao {
    private static final Logger log = Logger.getLogger(MySqlPublicationDao.class);
    private Connection con;
    /**
     * Sql query that read all publications from db.
     */
    private static final String SQL__GET_ALL_PUBLICATIONS = "SELECT * FROM publications";
    /**
     * Sql query that get publication by name .
     */
    private static final String SQL__FOUND_BY_NAME = "SELECT * FROM publications where Name=?";
    /**
     * Sql query that get publication by ID.
     */
    private static final String SQL__GET_PUBLICATION_BY_ID = "SELECT * FROM publications where id=?";
    /**
     * Sql query that insert into db new publication .
     */
    private static final String SQL__CREATE_PUBLICATION = "INSERT INTO publications (Name, Price, GroupID) " +
            "VALUES(?, ?, ?)";
    /**
     * Sql query that delete publication by ID.
     */
    private static final String SQL__DELETE_PUBLICATION_BY_ID = "DELETE FROM publications where id=? ";
    /**
     * Sql query that update publication by ID.
     */
    private static final String SQL__UPDATE_PUBLICATION = "UPDATE publications SET Name=?, Price=?, GroupID=? where id=?";
    /**
     * Sql query that get all publication in order by Name .
     */
    private static final String SQL__GET_ALL_BY_NAME = "SELECT * FROM publications ORDER BY Name";
    /**
     * Sql query that get all publication in order by Price.
     */
    private static final String SQL__GET_ALL_BY_Price = "SELECT * FROM publications ORDER BY Price";
    /**
     * Sql query that get all publication by Group.
     */
    private static final String SQL__GET_BY_GROUP = "SELECT * FROM publications where GroupID=?";
    /**
     * Sql query that get all user's publications .
     */
    private static final String SQL__GET_ALL_BY_USER_ID = "Select * from publications where ID IN (select bookID from subscription where userID=?)";

    public MySqlPublicationDao(Connection con) {

        this.con = con;
    }

    @Override
    public void create(Publication publ) {
        int counter = 1;
        try {
            PreparedStatement stmt = con.prepareStatement(SQL__CREATE_PUBLICATION);
            stmt.setString(counter++, publ.getBookName());
            stmt.setDouble(counter++, publ.getPrice());
            stmt.setInt(counter, publ.getGroupID());
            stmt.executeUpdate();
        } catch (SQLException e) {

            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }


    }

    public List<Publication> getAllByUserID(int ID) {
        List<Publication> list = new ArrayList<>();
        Publication pub = new Publication();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL__GET_ALL_BY_USER_ID);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractPublication(rs));
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
        return list;
    }

    @Override
    public Publication get(int ID) {
        Publication pub = new Publication();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL__GET_PUBLICATION_BY_ID);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                pub = extractPublication(rs);
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
        return pub;
    }

    @Override
    public void update(Publication publication) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL__UPDATE_PUBLICATION);
            int counter = 1;
            ps.setString(counter++, publication.getBookName());
            ps.setDouble(counter++, publication.getPrice());
            ps.setInt(counter++, publication.getGroupID());
            ps.setInt(counter, publication.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }


    }

    @Override
    public void delete(int publID) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL__DELETE_PUBLICATION_BY_ID);
            ps.setInt(1, publID);
            ps.executeUpdate();

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }

    }

   /* public Publication foundByName(String name) {
        Publication publ = new Publication();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(SQL__FOUND_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            publ.setPrice(rs.getDouble(Fields.PUBLICATION__PRICE));
            publ.setBookName(rs.getString(Fields.PUBLICATION__NAME));
            publ.setId(rs.getInt(Fields.ENTITY__ID));

        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return publ;
    }*/

    @Override
    public List<Publication> getAll() {
        List<Publication> publicationList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__GET_ALL_PUBLICATIONS);
            while (rs.next()) {
                publicationList.add(extractPublication(rs));
            }
            System.err.println("I'm here");
        } catch (SQLException e) {
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {
            try {
                System.err.println("I'm here too");
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }
        return publicationList;

    }


    private Publication extractPublication(ResultSet rs) {
        Publication publication = new Publication();
        try {

            publication.setId(rs.getInt(Fields.ENTITY__ID));
            publication.setBookName(rs.getString(Fields.PUBLICATION__NAME));
            publication.setPrice(rs.getDouble(Fields.PUBLICATION__PRICE));
            publication.setGroupID(rs.getInt(Fields.PUBLICATION__GROUP_ID));

        } catch (SQLException e) {
            System.out.println("HEHEHEHEHEHEHEHHEE");
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        } finally {


        }
        return publication;
    }
}
