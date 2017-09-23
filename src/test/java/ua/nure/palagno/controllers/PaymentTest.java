package ua.nure.palagno.controllers;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.palagno.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class PaymentTest extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ServletContext servletContext ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeClass
    public static void init() throws Exception {
       UserPageTest.initialize();
    }
    @Test
    public void testCorrectPay() throws ServletException, IOException {
        User user = new User(15, "AAA", 2.15, "user@mail.com", 1, 2, "dddddddd", "dddddd");
        Payment pay = new Payment();
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(request.getParameter("payment")).thenReturn("22");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getSession().getServletContext()).thenReturn(servletContext);
        pay.doGet(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/success.jsp", captor.getValue());


    }

}
