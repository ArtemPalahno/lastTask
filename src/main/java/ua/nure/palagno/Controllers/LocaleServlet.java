package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Artem_Palagno on 16.09.2017.
 */
public class LocaleServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LocaleServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Starting changing locale");
        String loc = req.getParameter("loc");
          Locale locale= null ;
        HttpSession session = req.getSession() ;
       // System.out.println(session==null);
        if(loc.equals("en")){
            session.setAttribute("language","en");
            Config.set( session, Config.FMT_LOCALE, locale = new Locale("en") ) ;
        }
        if(loc.equals("ru")){
            session.setAttribute("language","ru");
            //req.getSession().setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", "ru");
            Config.set( session, Config.FMT_LOCALE, locale = new Locale("ru") ) ;
        }

    //    req.getRequestDispatcher("/main.jsp").forward(req , resp );
         String referer = req.getHeader("Referer");
        log.debug("Redirect to "+  referer);
        resp.sendRedirect(referer);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
