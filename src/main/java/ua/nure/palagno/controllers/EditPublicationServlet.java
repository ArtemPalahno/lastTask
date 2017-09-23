package ua.nure.palagno.controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlGroupDao;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;
import ua.nure.palagno.db.entity.Publication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Artem_Palagno on 15.09.2017.
 * Servlet that work with showing and handle Edit Publication page.
 */
public class EditPublicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(EditPublicationServlet.class);

    /*
       showing creation publication page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Starting diaplay editView");
        Connection conn = MySQLConnectionUtils.getMySQLConnection();

        int id = Integer.parseInt(request.getParameter("id"));
        log.trace("product's id ===> " + id);
        Publication publication = null;

        String errorString = null;


        publication = new MySqlPublicationDao(conn).get(id);


        /**
         * no publication with current ID
         */
        if (publication == null) {
            log.trace("publication is null");
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
        /*
        saving info about publication in request before forward to views.
         */

        request.setAttribute("publication", publication);
        request.setAttribute("groups", new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).getAll());

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/WEB-INF/jsp/admin/editPublicationView.jsp");
        dispatcher.forward(request, response);

    }

    /*
    Starts when admin input product information and press SUBMIT .
    */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("Starting validating and saving publication info");
        Connection conn = MySQLConnectionUtils.getMySQLConnection();
        String errorString = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = (String) request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int groupID = Integer.parseInt(request.getParameter("group"));
        if (name.length() < 2) {
            errorString = "name is to short ";
        }
        // if we got problem redirect to publication view.
        if (errorString != null) {
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
                  }else {

            Publication product = new Publication();
            product.setPrice(price);
            product.setGroupID(groupID);
            product.setBookName(name);
            product.setId(id);


            new MySqlPublicationDao(conn).update(product);

            request.setAttribute("product", product);
            log.trace("Setting publication in request's attribute " + product);

            //redirect to publication page .
            log.debug("Validation and saving are done ");
            response.sendRedirect("/PublicationList");
            // response.sendRedirect(request.getContextPath() + "/PublicationList");
        }
    }

}