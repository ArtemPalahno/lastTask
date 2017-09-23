package ua.nure.palagno.controllers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
public class CreatePublicationTest extends Mockito {
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext ;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void init() throws Exception {

        initialize();

    }
   @Test
    public void doGetTesting() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        //when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
       // when(request.getServletContext()).thenReturn(servletContext);
        //when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
       /* when(request.getServletContext()
                .getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);*/
       // when(servletContext.getRequestDispatcher())
        new CreatePublication().doGet(request,response);
        verify(request).getRequestDispatcher("/WEB-INF/jsp/admin/createPublication.jsp");
        //verify(request).getServletContext().getRequestDispatcher("createPublication.jsp");
    }
    @Test
    public void doPostTesting() throws IOException, ServletException {
        when(request.getParameter("name")).thenReturn("Name");
        when(request.getParameter("group")).thenReturn("1");
        when(request.getParameter("price")).thenReturn("23");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        new CreatePublication().doPost(request,response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/PublicationList", captor.getValue());
    }
}
