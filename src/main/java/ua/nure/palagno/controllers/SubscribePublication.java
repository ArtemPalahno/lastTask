package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;
import ua.nure.palagno.db.dao.classes.MySqlSubscriptionsDao;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Artem_Palagno on 13.09.2017.
 * Servlet that work with subscribe operation .
 */
public class SubscribePublication extends HttpServlet {
    private static final Logger log = Logger.getLogger(SubscribePublication.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorString  = null;
        log.debug("Starting subscribing");
        User user = (User) req.getSession().getAttribute("user");
        log.trace("User==>" + user);
        int bookId =Integer.parseInt( req.getParameter("code"));
        log.trace("Book id " + bookId);
        if(!new MySqlSubscriptionsDao(MySQLConnectionUtils.getMySQLConnection()).IsUserHasPublication(user.getId(),bookId)){
           log.trace("Starting money operation");
              double bookPrice =  new MySqlPublicationDao(MySQLConnectionUtils.getMySQLConnection()).get(bookId).getPrice();
              if(user.getMoney()- bookPrice>=0){
              user.setMoney(user.getMoney()-bookPrice);
                  try {
                      new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).updateMoney(user);
                  } catch (SQLException e) {
                      errorString = "Problem " ;
                  }
              }
              else {
                  log.trace("user can't by this one");
                  errorString = "You don't have enough money" ;
                  req.setAttribute("error"  ,errorString);
                  req.getRequestDispatcher("/PublicationList").forward(req, resp);
              }
              new MySqlSubscriptionsDao(MySQLConnectionUtils.getMySQLConnection()).Add(user.getId(),bookId);
            req.getRequestDispatcher("/userPage").forward(req, resp);
            log.debug("Subscribing finished without any problem");

        }
        else{
            log.debug("Subscribing finished , user have that publication ");
           req.setAttribute("error" ,"You already have this one");
            req.getRequestDispatcher("/PublicationList").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
