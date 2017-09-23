package ua.nure.palagno.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Artem_Palagno on 18.09.2017.
 * Listener that initialize log4j.
 */
public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Servlet context initialization starts");

        ServletContext servletContext = servletContextEvent.getServletContext();
        initLog4J(servletContext);
        log("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }
    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath(
                    "WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log("Log4J initialization finished");
    }
}
