package ua.nure.palagno.db.entity;

import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlGroupDao;

import java.io.Serializable;

public class Publication implements Serializable{
    private int id;
    private String bookName;
    private String author;
    private double price;
    private String groupName ;

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", groupID=" + groupID +
                '}';
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    private int groupID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Publication(int id, String bookName, String author, double price,int groupID) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.groupID = groupID ;

    }

    public Publication() {
    }

    public String getGroupName() {
        return new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).get(this.getGroupID()).getGroupName();
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
