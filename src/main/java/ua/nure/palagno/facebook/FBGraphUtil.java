package ua.nure.palagno.facebook;

/**
 * Created by Artem_Palagno on 20.09.2017.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class FBGraphUtil {
    private String accessToken;

    public FBGraphUtil(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFBGraph() {
        String graph = null;
        try {
            String g = "https://graph.facebook.com/me?" + accessToken
                    + "&fields=name,email,gender";
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();
            graph = b.toString();
            //System.out.println(graph);
        } catch (Exception e) {
            throw new RuntimeException("ERROR in getting FB graph data. " + e);
        }
        return graph;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map getGraphData(String fbGraph) {
        Map fbUser = new HashMap();
        try {
            JSONObject data = new JSONObject(fbGraph);
            fbUser.put("id", data.getString("id"));
            fbUser.put("name", data.getString("name"));
            if (data.has("email"))
                fbUser.put("email", data.getString("email"));
            if (data.has("gender"))
                fbUser.put("gender", data.getString("gender"));
        } catch (JSONException e) {
            throw new RuntimeException("ERROR in parsing FB graph data. " + e);
        }
        return fbUser;
    }
}
