package ua.nure.palagno.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ua.nure.palagno.controllers.UserPageTest.initialize;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class LocaleServletTest extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void init() throws Exception {

        initialize();

    }
    @Test
    public void testRuLocale() throws ServletException, IOException {
        when(request.getParameter("loc")).thenReturn("ru");
        when(request.getHeader("Referer")).thenReturn("/refer");
        when(request.getSession()).thenReturn(session);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new LocaleServlet().doGet(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/refer", captor.getValue());
    }
    @Test
    public void testEnLocale() throws ServletException, IOException {
        when(request.getParameter("loc")).thenReturn("en");
        when(request.getHeader("Referer")).thenReturn("/refer");
        when(request.getSession()).thenReturn(session);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new LocaleServlet().doGet(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/refer", captor.getValue());
    }
}
