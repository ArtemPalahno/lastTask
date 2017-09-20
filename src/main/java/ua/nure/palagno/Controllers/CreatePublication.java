package ua.nure.palagno.Controllers;

import org.apache.log4j.Logger;
import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlGroupDao;
import ua.nure.palagno.db.dao.classes.MySqlPublicationDao;
import ua.nure.palagno.db.entity.Group;
import ua.nure.palagno.db.entity.Publication;
import ua.nure.palagno.listener.ContextListener;

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
 */
public class CreatePublication extends HttpServlet {
    private static final long serialVersionUID = 12L;
    private static final Logger log = Logger.getLogger(CreatePublication.class);
    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Starting dispaly create publication page " );
        List<Group> list = new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).getAll();
        log.trace("Setting attribute groups in request " + list);
        request.setAttribute("groups", list);
        log.debug("Forward to createPublication.jsp");
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/admin/createPublication.jsp");
        dispatcher.forward(request, response);
    }

    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String errorString = null;
        log.debug("Start reading parameters from request" );
        Connection conn = MySQLConnectionUtils.getMySQLConnection();

        int group = Integer.parseInt(request.getParameter("group"));
        String name = (String) request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        if(name.length()<2){
            errorString = "publication's name is too short " ;
            log.trace(errorString + name);
        }

        log.trace("group ===> " + group + name+ "name ===> " + name + "price ===> " + price);

        Publication publication = new Publication();


        publication.setBookName(name);
        publication.setGroupID(group);
        publication.setPrice(price);


        new MySqlPublicationDao(conn).create(publication);
        // DBUtils.insertProduct(conn, product);


        // Сохранить информацию в request attribute перед тем как forward к views.
        log.trace("Setting atribute in request " + publication);
        request.setAttribute("publication", publication);

        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            log.debug("Fowarding to request edit page bocouse of " + errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            log.debug("Redirecting to publicationView");
              response.sendRedirect(request.getContextPath() + "/PublicationList");
        }
    }
}
