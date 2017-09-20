package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlRoleDao;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.User;
import ua.nure.palagno.utils.MyUtils;
import ua.nure.palagno.utils.Verify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 11.09.2017.
 */
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Starting LoginServlet");
        HttpSession session = request.getSession();
        boolean valid = true;
        String errorString = null ;
        String email = request.getParameter("email");
        String password = request.getParameter("psw");
        boolean remember = "OK".equals(request.getParameter("rememberMe"));
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        valid = Verify.verify(gRecaptchaResponse);
        log.trace("email ===>" + email + "remember ===>" + remember);
        try {
            if(!valid){
                errorString = "captcha is invalid" ;
                request.setAttribute("errorString", errorString);
                response.sendRedirect("signin.jsp");
        }
            else{
            if (new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).isExist(email, password)) {
                User user = new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).get(email, password);
                System.out.println(user +" " + new MySqlRoleDao(MySQLConnectionUtils.getMySQLConnection()).get(user.getRole()));
                MyUtils.storeLoginedUser(session, user);
                if (remember) {
                    System.out.println("+");
                    MyUtils.storeUserCookie(response, user);
                } else {
                    System.out.println("-");
                    MyUtils.deleteUserCookie(response);
                }
                if (new MySqlRoleDao(MySQLConnectionUtils.getMySQLConnection()).get(user.getRole()).getRoleName().equals("admin")) {
                    //response.sendRedirect(request.getContextPath() + "/adminPage");
                    // response.sendRedirect(request.getContextPath() + "/userPage.jsp");
                    MyUtils.setPublicationListinReq(request);
                    MyUtils.setUserListinReq(request);
                    log.trace("Client is admin");
                    request.getRequestDispatcher("WEB-INF/jsp/admin/admin.jsp").forward(request, response);
                } else {
                    log.trace("Client is user");
                    request.getRequestDispatcher("/userPage").forward(request, response);
                }
                //response.sendRedirect(request.getContextPath() + "/userPage.jsp");}
                // request.getRequestDispatcher("userPage.jsp").include(request, response);
                //  response.sendRedirect("userPage.jsp");
            } else {
                errorString = "Incorrect password , login " ;
                //request.getRequestDispatcher("signin.html").include(request, response);
                request.setAttribute("errorString", errorString);
                response.sendRedirect("signin.jsp");
            }}
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
