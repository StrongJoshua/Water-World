
package edu.gatech.scrumbags.model;

import org.mindrot.jbcrypt.BCrypt;

/** The class that contains all of an (authorized) user's information.
 * @author Jan Risse */
public class Authorized {
	private String first, last;
	private String username;
	private String pass;
	private AccountType accountType;
	private String email;
	private String address;

	/** Creates a new user with the given information.
	 * @param first The user's first name.
	 * @param last The user's last name.
	 * @param username The user's username.
	 * @param pass The user's password (stored in a hash).
	 * @param account The user's account type. */
	public Authorized (String first, String last, String username, String pass, AccountType account) {
		this.first = first;
		this.last = last;
		this.username = username;
		this.pass = BCrypt.hashpw(pass, BCrypt.gensalt());
		this.accountType = account;
		this.setEmail("");
		this.setAddress("");
	}

	/** Checks if the username and password given match this user's username and password.
	 * @param username username of this user
	 * @param pass hashed password for this user
	 * @return Whether the user is authenticated. */
	public boolean authenticate (String username, String pass) {
		return username.equalsIgnoreCase(this.username) && BCrypt.checkpw(pass, this.pass);
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
	public AccountType getAccountType () {
		return accountType;
	}

	/** @return User's email. */
	public String getEmail () {
		return email;
	}

	/** Sets the user's email.
	 * @param email The email to change to. */
	public void setEmail (String email) {
		this.email = email;
	}

	/** @return User's address. */
	public String getAddress () {
		return address;
	}

	/** Sets the user's address.
	 * @param address The address to change to. */
	public void setAddress (String address) {
		this.address = address;
	}
}
