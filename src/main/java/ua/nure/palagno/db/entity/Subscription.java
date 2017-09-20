package ua.nure.palagno.db.entity;

import java.io.Serializable;

public class Subscription implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    private int userID;
    private int bookID;

    public Subscription() {
    }

    public Subscription(int id, int userID, int bookID) {
        this.id = id;
        this.userID = userID;
        this.bookID = bookID;
    }
}
