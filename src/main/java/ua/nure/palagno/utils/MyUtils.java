package ua.nure.palagno.utils;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlDaoFactory;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.Publication;
import ua.nure.palagno.db.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Artem_Palagno on 14.09.2017.
 * Util class that work with session , cookies , setting
 */
public class MyUtils {
    private static final Logger log = Logger.getLogger(MyUtils.class);
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        log.trace("Setting in sesion user's object" + loginedUser);
        session.setAttribute("user", loginedUser);
        log.trace("Setting complete");
    }

    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        log.trace("Getting user's object from session");
        User loginedUser = (User) session.getAttribute("user");
        log.trace("Getting logined user is done");
        return loginedUser;
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        log.trace("Storing user in cookie");
        Integer ID = user.getId();
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, ID.toString());
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        log.trace("Storing done");
        response.addCookie(cookieUserName);
    }

    //Read user name from cookies
    public static int getUserNameInCookie(HttpServletRequest request) {
        log.trace("Getting user from cookies");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    log.trace("Getting user from session is done");
                    return Integer.parseInt(cookie.getValue());

                }
            }
        }
        log.trace("User doesn't have cooies");
        return 0;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        log.trace("Deleting user cookies");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        log.trace("Deleting is done");
        response.addCookie(cookieUserName);
    }

    public static void setPublicationListinReq(HttpServletRequest req) {
        log.trace("Setting publications in request");
        List<Publication> list = null;
        // Connection con = MySQLConnectionUtils.getMySQLConnection();
        list = new MySqlDaoFactory().getPublicationDao().getAll();
        // list = new MySqlPublicationDao(con).getAll();
        req.setAttribute("publicationList", list);
        log.trace("Setting publications is done" + list);
    }

    //Setting Users list in user
    public static void setUserListinReq(HttpServletRequest req) {
        log.trace("Setting user's publications in requset");
        List<User> list = null;
        // Connection con = MySQLConnectionUtils.getMySQLConnection();
        // list = new MySqlUserDao(con).getAll();
        list = new MySqlDaoFactory().getUserDao().getAll();
        req.setAttribute("usersList", list);
        log.trace("Setting user's publications is done" + list);
    }

}
