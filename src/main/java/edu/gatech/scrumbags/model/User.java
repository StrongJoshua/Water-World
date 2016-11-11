
package edu.gatech.scrumbags.model;

/** The class that contains all of an user's information.
 *
 * @author Jan Risse */
public class User {
    private String first, last;
    private String username;
    private Authorization authorization;
    private String email;
    private String address;

    /** Creates a new user with the given information.
     *
     * @param first The user's first name.
     * @param last The user's last name.
     * @param username The user's username.
     * @param authorization The user's authorization. */
    public User (String first, String last, String username, Authorization authorization) {
        this(first, last, username, authorization, "", "");
    }

    /** Creates a new user with the given information.
     *
     * @param first The user's first name.
     * @param last The user's last name.
     * @param username The user's username.
     * @param authorization The user's authorization. */
    public User (String first, String last, String username, Authorization authorization, String email, String address) {
        this.first = first;
        this.last = last;
        this.username = username;
        this.authorization = authorization;
        this.setEmail(email);
        this.setAddress(address);
    }

    /** Checks if the username and password given match this user's username and password.
     *
     * @param username username of this user
     * @param pass hashed password for this user
     * @return Whether the user is authenticated. */
    public boolean authenticate (String username, String pass) {
        return false; // broken
        // return username.equalsIgnoreCase(this.username) && BCrypt.checkpw(pass, this.password);
    }

    /** @return User's first name. */
    public String getFirst () {
        return first;
    }

    /** @return User's last name. */
    public String getLast () {
        return last;
    }

    /** @return User's full name. */
    public String getFullName () {
        return first + " " + last;
    }

    /** @return User's username. */
    public String getUsername () {
        return username;
    }

    /** @return User's account type. */
    public Authorization getAuthorization () {
        return authorization;
    }

    /** @return User's email. */
    public String getEmail () {
        return email;
    }

    /** Sets the user's email.
     *
     * @param email The email to change to. */
    public void setEmail (String email) {
        this.email = email;
    }

    /** @return User's address. */
    public String getAddress () {
        return address;
    }

    /** Sets the user's address.
     *
     * @param address The address to change to. */
    public void setAddress (String address) {
        this.address = address;
    }
}
