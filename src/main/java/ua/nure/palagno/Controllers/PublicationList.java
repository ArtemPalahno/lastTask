package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artem_Palagno on 12.09.2017.
 */
public class PublicationList extends HttpServlet {
    private static final Logger log = Logger.getLogger(PublicationList.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("starting PublicationList");

        MyUtils.setPublicationListinReq(req);
        log.debug("PublicationList is finished");
        req.getRequestDispatcher("WEB-INF/jsp/other/publicationListView.jsp").forward(req, resp);

    }
}
