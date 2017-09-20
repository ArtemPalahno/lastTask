package ua.nure.palagno.Controllers;

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
 */
public class EditPublicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(EditPublicationServlet.class);


    // Отобразить страницу редактирования продукта.
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


        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).
        // Redirect sang trang danh sách sản phẩm.
        if (publication == null) {
            log.trace("publication is null");
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // Сохранить информацию в request attribute перед тем как forward к views.

        request.setAttribute("publication", publication);
        request.setAttribute("groups", new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).getAll());

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/admin/editPublicationView.jsp");
        dispatcher.forward(request, response);

    }

    // После того, как пользователь отредактировал информацию продукта и нажал на Submit.
    // Данный метод будет выполнен.
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
        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }

        Publication product = new Publication();
        product.setPrice(price);
        product.setGroupID(groupID);
        product.setBookName(name);
        product.setId(id);


        new MySqlPublicationDao(conn).update(product);

        request.setAttribute("product", product);
        log.trace("Setting publication in request's attribute " + product);

        // Если все хорошо.
        // Redirect к странице со списком продуктов.
        log.debug("Validation and saving are done ");
        response.sendRedirect(request.getContextPath() + "/PublicationList");

    }

}