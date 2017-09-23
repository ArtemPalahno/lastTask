package ua.nure.palagno.controllers;
/**
 * Created by Artem_Palagno on 23.09.2017.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import junit.framework.TestCase;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.palagno.controllers.UserPage;
import ua.nure.palagno.db.entity.Publication;
import ua.nure.palagno.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Artem_Palagno on 22.09.2017.
 */
public class UserPageTest extends Mockito {
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
    public void testSimpleUser() throws Exception {
        UserPage userPage = new UserPage();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(15, "AAA", 2.15, "user@mail.com", 1, 2, "dddddddd", "dddddd"));
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        userPage.doPost(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/jsp/other/userPage.jsp");

    }

    @Test
    public void testAdmin() throws ServletException, IOException {
        UserPage userPage = new UserPage();
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        ArrayList<Publication> publications = new ArrayList<>();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(15, "AAA", 2.15, "user@mail.com", 1, 1, "dddddddd", "dddddd"));
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        userPage.doPost(request, response);
        verify(request).getRequestDispatcher("/adminPage");
    }

    @Test
    public void testBlocked() throws ServletException, IOException {
        UserPage userPage = new UserPage();

        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User(15, "AAA", 2.15, "user@mail.com", 2, 2, "dddddddd", "dddddd"));
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        userPage.doPost(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/jsp/other/blocked.jsp");
    }

    public static void initialize() throws Exception {
        String log4jConfPath = "C:\\Users\\palah\\workspace\\lastTask\\src\\main\\webapp\\WEB-INF\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/db");
        properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        properties.setProperty("maxActive", "10");
        properties.setProperty("maxIdle", "8");
        properties.setProperty("minIdle", "10");
        properties.setProperty("maxWait", "10");
        properties.setProperty("testOnBorrow", "true");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        properties.setProperty("validationQuery", "SELECT 1");
        properties.setProperty("removeAbandoned", "true");
        properties.setProperty("removeAbandonedTimeout", "1");
        properties.setProperty("logAbandoned", "true");
        DataSource ds = BasicDataSourceFactory.createDataSource(properties);
        InitialContext ic = new InitialContext();
        ic.createSubcontext("java:");
        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.createSubcontext("java:comp/env/jdbc");
        ic.bind("java:comp/env/jdbc/lastTask", ds);


    }
}
