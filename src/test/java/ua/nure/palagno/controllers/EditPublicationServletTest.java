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

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ua.nure.palagno.controllers.UserPageTest.initialize;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class EditPublicationServletTest  extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
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
    public void testView() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("11");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        new EditPublicationServlet().doGet(request,response);
        verify(request).getRequestDispatcher("/WEB-INF/jsp/admin/editPublicationView.jsp");
    }
    @Test
    public void testHandler() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("9");
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("1");
        when(request.getParameter("price")).thenReturn("23");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new EditPublicationServlet().doPost(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/PublicationList", captor.getValue());
    }
    @Test
    public void negativeHandler() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("9");
        when(request.getParameter("name")).thenReturn("a");
        when(request.getParameter("group")).thenReturn("1");
        when(request.getParameter("price")).thenReturn("23");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
       // ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new EditPublicationServlet().doPost(request,response);

      //  new EditPublicationServlet().doPost(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/views/editProductView.jsp");

    }
}
