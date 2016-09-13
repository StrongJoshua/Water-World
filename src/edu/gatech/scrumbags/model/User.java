package edu.gatech.scrumbags.model;

/**
 * Created by Beau on 9/12/2016.
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return password.equals(this.password);
    }
}