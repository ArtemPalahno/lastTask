package ua.nure.palagno.utils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Artem_Palagno on 23.09.2017.
 */
public class Verify {
    public static final String SECRET_KEY ="6Ldq9jAUAAAAAFfYEX0tEekV3KSI9d2uR9kL7yWq";

    public static final String SITE_VERIFY_URL = //
            "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            // Открыть соединение (Connection) к URL выше.
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

            // Добавить информации Header в Request, чтобы приготовить отправку к server.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // Данные будут отправлены на Server.
            String postParams = "secret=" + SECRET_KEY //
                    + "&response=" + gRecaptchaResponse;

            // Send Request
            conn.setDoOutput(true);

            // Получить Output Stream (Выходной поток) соединения к Server.
            // Записать данные в Output Stream, значит отправить информацию на Server.
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            // Ответный код возвращает из Server.
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode=" + responseCode);

            // Получить Input Stream (Входной поток) Connection
            // чтобы прочитать данные отправленные от Server.
            InputStream is = conn.getInputStream();

            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // ==> {"success": true}
            System.out.println("Response: " + jsonObject);

            boolean success = jsonObject.getBoolean("success");
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
