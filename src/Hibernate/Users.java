package Hibernate;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @Column(name = "User_Id")
    private int id;

    @Column(name = "E_mail")
    private String eMail;

    @Column(name = "Password")
    private String password;

    public Users() {

    }

    public Users(String eMail, String password) {

        this.eMail = eMail;
        this.password = password;
    }

    public Users(int id, String eMail, String password) {

        this.id = id;
        this.eMail = eMail;
        this.password = password;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String geteMail() {

        return eMail;
    }

    public void seteMail(String eMail) {

        this.eMail = eMail;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


}
