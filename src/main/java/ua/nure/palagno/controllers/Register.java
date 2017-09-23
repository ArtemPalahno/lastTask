package ua.nure.palagno.controllers;
/**
 * Created by palah on 02.09.2017.
 * Servlet that work with handle register info .
 */

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlDaoFactory;
import ua.nure.palagno.db.dao.interfaces.UserDao;
import ua.nure.palagno.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class Register extends HttpServlet {
    private static final Logger log = Logger.getLogger(Register.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Start Registering user");

        resp.setContentType("text/html");
        User newUser = new User();
        String email = req.getParameter("uEmail");
        String name = req.getParameter("uName");
        String surname = req.getParameter("uSurname");
        String pass = req.getParameter("psw");
        log.trace("User's email==> " + email + " name==> " + name + " surname==> "
                + surname + " password==> " + pass);
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPassword(pass);
       // HttpSession session = req.getSession(true);
        /*session.setAttribute("user", newUser);
        session.setAttribute("email", email);*/
        UserDao userDao = new MySqlDaoFactory().getUserDao();
        userDao.create(newUser);
        log.trace("Registering finished");
        resp.sendRedirect("signin.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}