
package edu.gatech.scrumbags.model;

import org.mindrot.jbcrypt.BCrypt;

public class Authorized {
	private String first, last;
	private String username;
	private String pass;
	private AccountType accountType;

	public Authorized (String first, String last, String username, String pass, AccountType account) {
		this.first = first;
		this.last = last;
		this.username = username;
		this.pass = BCrypt.hashpw(pass, BCrypt.gensalt());
		this.accountType = account;
	}

	/** Checks if the username and password given match this user's username and password.
	 * @param username
	 * @param pass
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
}
