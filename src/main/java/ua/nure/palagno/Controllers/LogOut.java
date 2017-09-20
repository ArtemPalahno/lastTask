package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Artem_Palagno on 14.09.2017.
 */
public class LogOut extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogOut.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("starting logout");
        HttpSession session=req.getSession();
        session.invalidate();
        MyUtils.deleteUserCookie(resp);
        log.debug("logout finished");
        resp.sendRedirect(req.getContextPath() + "/main.jsp");
    }
}
