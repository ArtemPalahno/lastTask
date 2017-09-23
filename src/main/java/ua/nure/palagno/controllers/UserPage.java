package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlRoleDao;
import ua.nure.palagno.db.entity.Role;
import ua.nure.palagno.db.entity.User;
import ua.nure.palagno.utils.MyUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Artem_Palagno on 11.09.2017.
 * Servlet that demonstrate User Page
 */
public class UserPage extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserPage.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("UserPage starts");
        Connection con = MySQLConnectionUtils.getMySQLConnection() ;

        HttpSession session = request.getSession();
        User user = MyUtils.getLoginedUser(session);
        log.trace("User is" + user);
        if (user.isBlocked()) {
            log.debug("UserPage finished because of blocks user ");

            request.getRequestDispatcher("/WEB-INF/jsp/other/blocked.jsp").forward(request, response);
        } else {
            Role role = new MySqlRoleDao(con).get(user.getRole()) ;
            log.trace("role is " + role);
            if (role.getRoleName().equals("admin")) {
                MyUtils.setPublicationListinReq(request);
                MyUtils.setUserListinReq(request);
                log.debug("redirecting to admin page");
                request.getRequestDispatcher("/adminPage").forward(request, response);
            } else {
                log.debug("redirecting to user page");
                request.getRequestDispatcher("/WEB-INF/jsp/other/userPage.jsp").forward(request, response);
               // response.sendRedirect("/WEB-INF/jsp/other/userPage.jsp");
            }
        }
    }
}
