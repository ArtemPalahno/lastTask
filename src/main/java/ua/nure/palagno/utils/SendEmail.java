package ua.nure.palagno.utils;

import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlDaoFactory;
import ua.nure.palagno.db.dao.classes.MySqlUserDao;
import ua.nure.palagno.db.entity.User;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by Artem_Palagno on 19.09.2017.
 */
public class SendEmail {
    public static boolean Send(String email){
        String from = "a.palagno@gmail.com";
        User user = null ;
        if((user = new MySqlUserDao(MySQLConnectionUtils.getMySQLConnection()).get(email))!=null){
        final String username = "a.palagno@gmail.com";
            final String password = "Abandon__19";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("from-email@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Testing Subject");
                message.setText("Dear user, it's your password " + user.getPassword());

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return true ;
        }
        return false ;
    }
}
