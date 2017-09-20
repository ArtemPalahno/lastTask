package ua.nure.palagno.Controllers;

import ua.nure.palagno.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Created by Artem_Palagno on 15.09.2017.
 */
public class AdminPage  extends HttpServlet {
    private static final Logger log = Logger.getLogger(AdminPage.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Starting AdminPage servlet");
        MyUtils.setPublicationListinReq(req);
        MyUtils.setUserListinReq(req);
        log.debug("AdminPage servlet finished");
        req.getRequestDispatcher("WEB-INF/jsp/admin/admin.jsp").forward(req, resp);

    }
}