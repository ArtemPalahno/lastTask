package ua.nure.palagno.db.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by palah on 03.09.2017.
 * Util class that help close and commit connections .
 */
public class MySQLConnectionUtils {
    public static Connection getMySQLConnection()
           {

               InitialContext initContext= null;
               try {
                   initContext = new InitialContext();
               } catch (NamingException e) {
                   e.printStackTrace();
               }
               DataSource ds = null;
               try {
                   Context envCtx = (Context) initContext.lookup("java:comp/env");

// Look up our data source
                   ds = (DataSource) envCtx.lookup("jdbc/lastTask");

               } catch (NamingException e) {
                   e.printStackTrace();
               }
               Connection conn = null;
               try {
                   conn = ds.getConnection();
               } catch (SQLException e) {
                   e.printStackTrace();
               }

             /* Connection conn = null ;
               try {  conn = ua.nure.palagno.db.connection.DataSource.getInstance().getConnection();
                   System.err.println("OPEN CONNECTION ");}
               catch (Exception e){

               }*/


               return  conn ;
    }

    public static void closeAndCommitQuietly(Connection conn) {
     try {

         //conn.commit();
         conn.close();
     } catch (Exception e) {

     }
 }

    public static void rollbackQuietly(Connection conn) {
        try {

            conn.rollback();
        } catch (Exception e) {
        }
    }


}
