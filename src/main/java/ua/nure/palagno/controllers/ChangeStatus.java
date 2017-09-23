package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artem_Palagno on 15.09.2017.
 * Servlet that changing user status
 */
public class ChangeStatus extends HttpServlet {
    private static final Logger log = Logger.getLogger(ChangeStatus.class);
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("ChangeStatus starts");
        int id = 0 ;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            log.error(e);
        }
        log.trace("Request parameter is ===>" + id);
        User user = new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).get(id) ;
        new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).
                changeStatus(user);
        log.debug("ChangeStatus finished");
        req.getRequestDispatcher("/adminPage").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
