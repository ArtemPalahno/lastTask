package ua.nure.palagno.db.entity;

import ua.nure.palagno.db.connection.MySQLConnectionUtils;
import ua.nure.palagno.db.dao.classes.MySqlStatusDao;

import java.io.Serializable;

public class User implements Serializable {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", money=" + money +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", session='" + session + '\'' +
                ", blocked=" + blocked +
                '}';
    }

    private int id;
    private String name;


    private String surname;
    private double money;
    private String email;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
          return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private int status;
    private int role;
    private String password;
    private String session;

    public boolean isBlocked() {

       if("valid".equals( new MySqlStatusDao(MySQLConnectionUtils.getMySQLConnection()).get(status).getStatusName())){
          // blocked = false ;
           return false;
       }
       else {
          // blocked = true ;
           return true ;
       }
     }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    private boolean blocked;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public User() {

    }

    public User(int id, String name, double money, String email, int status,
                int role, String password, String session) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.email = email;
        this.status = status;
        this.role = role;
        this.password = password;
        this.session = session;


    }
}
