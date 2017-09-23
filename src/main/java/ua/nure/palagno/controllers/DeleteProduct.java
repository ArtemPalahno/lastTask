package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Artem_Palagno on 15.09.2017.
 * Servlet that deleting publication.
 */
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 123L;
    private static final Logger log = Logger.getLogger(DeleteProduct.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Delete product is starting");
        Connection conn = MySQLConnectionUtils.getMySQLConnection();

        int id = Integer.parseInt(request.getParameter("id"));

        new MySqlPublicationDao(conn).delete(id);
        log.debug("Publucation" + id + " is deleted");
       // response.sendRedirect(request.getContextPath() + "/PublicationList");
        response.sendRedirect("/PublicationList");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

