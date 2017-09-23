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
public class RegisterTest extends Mockito {
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
    public void testRegistration() throws IOException, ServletException {
        when(request.getParameter("uEmail")).thenReturn("e@mail.ru");
        when(request.getParameter("uName")).thenReturn("axcvb");
        when(request.getParameter("psw")).thenReturn("aaaaaaaaaaaa");
        when(request.getParameter("uSurname")).thenReturn("aaaaaaaa");
        when(request.getSession()).thenReturn(session);
      //  when(session.setAttribute("user", newUser))
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
         new Register().doGet(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("signin.jsp", captor.getValue());
    }
}
