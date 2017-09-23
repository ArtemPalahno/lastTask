package ua.nure.palagno.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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

import static org.junit.Assert.assertEquals;
import static ua.nure.palagno.controllers.UserPageTest.initialize;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class LoginServletTest extends Mockito {
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
    public void rememberLoginTest() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("fhjhkhkh@wadaw.vco");
        when(request.getParameter("psw")).thenReturn("hjkhkjhkhj");
        when(request.getParameter("rememberMe")).thenReturn("OK");
        when(request.getSession()).thenReturn(session);
        //when(request.getParameter("g-recaptcha-response")).thenReturn("string");
       // when(Verify.verify("string")).thenReturn(true);
       // when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new LoginServlet().doGet(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("signin.jsp", captor.getValue());


    }
}
