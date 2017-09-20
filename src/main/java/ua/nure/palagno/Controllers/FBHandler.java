package ua.nure.palagno.Controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import ua.nure.palagno.facebook.CreateFBConnection;
import ua.nure.palagno.facebook.FBGraphUtil;
import ua.nure.palagno.facebook.IdTokenVerifierAndParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Artem_Palagno on 20.09.2017.
 */
public class FBHandler extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {}

       /* resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            System.out.println("User name: " + name);
            System.out.println("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);
            req.getServletContext()
                    .getRequestDispatcher("/welcome-page.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    private static final long serialVersionUID = 1L;
    private String code = "";

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback.");
        }
        CreateFBConnection fbConnection = new CreateFBConnection();
        String accessToken = fbConnection.getAccessToken(code);
        res.setContentType("text/html");
        FBGraphUtil fbGraph = new FBGraphUtil(accessToken);
        String graph = fbGraph.getFBGraph();
        @SuppressWarnings("unchecked")
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
        ServletOutputStream out = res.getOutputStream();
        out.println("<h2>Facebook OAuth Login</h2>");
        out.println("<div style='float:left'>");
        out.println("<img src='https://graph.facebook.com/"
                + fbProfileData.get("id")
                + "/picture?type=square' style='border-radius:50%'alt='Profile Pic'>");
        out.println("</div>");
        out.println("<div style='float:left'>");
        out.println("Name   : " + fbProfileData.get("name") + "<br>");
       // out.println("Email  : " + fbProfileData.get("email") + "<br>");
       // out.println("Gender : " + fbProfileData.get("gender") + "<br>");
        out.println("</div>");
    }
}