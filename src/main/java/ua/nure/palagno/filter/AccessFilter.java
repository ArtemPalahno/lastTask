package ua.nure.palagno.filter;

import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlDaoFactory;
import ua.nure.palagno.db.dao.classes.MySqlRoleDao;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.dao.interfaces.DaoFactory;
import ua.nure.palagno.db.dao.interfaces.RoleDao;
import ua.nure.palagno.db.entity.User;
import ua.nure.palagno.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Artem_Palagno on 09.09.2017.
 * Access filter that store user info.
 */
public class AccessFilter implements Filter {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        User user = null;
        int ID = 0;
        HttpSession session = request.getSession();
        if ((ID = MyUtils.getUserNameInCookie(request)) != 0 && session.isNew()) {
            user = new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).get(ID);

            MyUtils.storeLoginedUser(session, user);
            chain.doFilter(request, res);
        } else {
            chain.doFilter(request, res);
        }



       /* HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        User userInSession = MyUtils.getLoginedUser(session);
        //
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        // Connection was created in JDBCFilter.
        Connection conn = MySQLConnectionUtils.getMySQLConnection();

        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            int userID = Integer.parseInt(MyUtils.getUserNameInCookie(req));

                User user = new MySqlUserDao(conn).get(userID);
                MyUtils.storeLoginedUser(session, user);

            // Mark checked Cookies.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
*/
    }


    @Override
    public void destroy() {

    }
}
