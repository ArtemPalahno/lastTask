package ua.nure.palagno.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.palagno.utils.Verify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static ua.nure.palagno.controllers.UserPageTest.initialize;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class SendingServletTest extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession session;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void init() throws Exception {

        initialize();

    }
    @Test
    public void positiveSendTest() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getParameter("email")).thenReturn("mail@gmail.com");
        new SendingServlet().doGet(request,response);
        verify(request).getRequestDispatcher("forgot.jsp");
    }
    @Test
    public void negativeSendTest() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getParameter("email")).thenReturn("ivanov@mail.com");
        new SendingServlet().doGet(request,response);
        verify(request).getRequestDispatcher("success.jsp");
    }
}
