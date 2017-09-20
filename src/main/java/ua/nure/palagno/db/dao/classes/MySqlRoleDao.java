package ua.nure.palagno.db.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.Fields;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.interfaces.RoleDao;
import ua.nure.palagno.db.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 08.09.2017.
 */
public class MySqlRoleDao implements RoleDao {
    private static final Logger log = Logger.getLogger(MySqlRoleDao.class);
    private Connection con ;

    public MySqlRoleDao(Connection con) {
        this.con = con;
    }

    private static final String SQL__GET_ROLE_BY_ID ="SELECT * FROM role where id=?";
    @Override
    public Role get(int key) {
        Role role = new Role();
        PreparedStatement ps=null;
        ResultSet rs=null ;
        try{
        ps =  con.prepareStatement(SQL__GET_ROLE_BY_ID);
        ps.setInt(1,key);
        rs=ps.executeQuery();
        if(rs.next()){

        role.setId(rs.getInt(Fields.ENTITY__ID));
        role.setRoleName(rs.getString(Fields.ROLE__NAME));}

        }
        catch (SQLException e){
            System.out.println("exception here");
            MySQLConnectionUtils.rollbackQuietly(con);
            log.debug("SQL exception", e);
        }finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MySQLConnectionUtils.closeAndCommitQuietly(con);
        }

        return role;
    }
}
