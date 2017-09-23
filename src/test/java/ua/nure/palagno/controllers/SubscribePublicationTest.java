package ua.nure.palagno.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.palagno.db.entity.User;

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
public class SubscribePublicationTest extends Mockito {
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
    public void hasThisPublTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(10, "AAA", 102.15, "user@mail.com", 1, 2, "dddddddd", "dddddd"));
        when(request.getParameter("code")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new SubscribePublication().doPost(request,response);
        verify(request).getRequestDispatcher("/PublicationList");
    }
    @Test
    public void negativeTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(6, "AAA", 0.1, "user@mail.com", 1, 2, "dddddddd", "dddddd"));
        when(request.getParameter("code")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new SubscribePublication().doPost(request,response);
        verify(request).getRequestDispatcher("/PublicationList");
    }
    @Test
    public void positiveTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(10, "AAA", 102.15, "user@mail.com", 1, 2, "dddddddd", "dddddd"));
        when(request.getParameter("code")).thenReturn("5");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new SubscribePublication().doPost(request,response);
        verify(request).getRequestDispatcher("/userPage");
    }
}
