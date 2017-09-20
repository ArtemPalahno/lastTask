package ua.nure.palagno.Controllers;

import ua.nure.palagno.utils.SendEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Artem_Palagno on 19.09.2017.
 */
public class SendingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(SendEmail.Send(email)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
            dispatcher.forward(req, resp);

        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("forgot.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
