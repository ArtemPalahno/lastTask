package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlDaoFactory;
import ua.nure.palagno.db.dao.classes.MySqlGroupDao;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;
import ua.nure.palagno.db.entity.Group;
import ua.nure.palagno.db.entity.Publication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Artem_Palagno on 15.09.2017.
 * Servlet that work with showing and handle Creating Publication page.
 */

public class CreatePublication extends HttpServlet {
    private static final long serialVersionUID = 12L;
    private static final Logger log = Logger.getLogger(CreatePublication.class);

    /**
     * showing creation publication page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Starting dispaly create publication page ");
        List<Group> list = new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).getAll();
        log.trace("Setting attribute groups in request " + list);
        request.setAttribute("groups", list);
        log.debug("Forward to createPublication.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/admin/createPublication.jsp").forward(request, response);
      /*  RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/admin/createPublication.jsp");
        dispatcher.forward(request, response);*/
    }

    /*
    Starts when admin input product information and press SUBMIT .
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String errorString = null;
        log.debug("Start reading parameters from request");
        Connection conn = MySQLConnectionUtils.getMySQLConnection();

        int group = Integer.parseInt(request.getParameter("group"));
        String name = (String) request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        if (name.length() < 2) {
            errorString = "publication's name is too short ";
            log.trace(errorString + name);
        }
        log.trace("group ===> " + group + name + "name ===> " + name + "price ===> " + price);
        Publication publication = new Publication();
        publication.setBookName(name);
        publication.setGroupID(group);
        publication.setPrice(price);
        new MySqlDaoFactory().getPublicationDao().create(publication);
        // new MySqlPublicationDao(conn).create(publication);


        //Saving information in request attribute before fovard() to views

        log.trace("Setting atribute in request " + publication);
        request.setAttribute("publication", publication);
        //If we get problem forward to "edit"
        if (errorString != null) {
            log.debug("Fowarding to request edit page bocouse of " + errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Everything is well , redirect to page with publication list .
        else {
            log.debug("Redirecting to publicationView");
            //response.sendRedirect(request.getContextPath() + "/PublicationList");
            response.sendRedirect("/PublicationList");
        }
    }
}
