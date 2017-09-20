package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 20.09.2017.
 */
public class Payment extends HttpServlet {
    private static final Logger log = Logger.getLogger(Payment.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Starting Payment servlet");
        String errorString = null;
        double value  = Double.valueOf(req.getParameter("payment"));
        User user = (User) req.getSession().getAttribute("user");
        log.trace("user is" + user);
        double beforePay = user.getMoney() ;
        user.setMoney(beforePay + value);
        try {
            new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).updateMoney(user);
        } catch (SQLException e) {
            errorString = e.getSQLState() ;
            req.setAttribute("error"  ,errorString);
            req.getRequestDispatcher("/userPage.jsp").forward(req, resp);
        }

        ServletContext context = req.getSession().getServletContext();
         context.setAttribute("OK" ,"some text here");
        resp.sendRedirect("/success.jsp");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
