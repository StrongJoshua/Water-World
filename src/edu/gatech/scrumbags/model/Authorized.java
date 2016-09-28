package edu.gatech.scrumbags.model;

import org.mindrot.jbcrypt.BCrypt;

public class Authorized {
	private String first, last;
	private String username;
	private String pass;
	
	public Authorized(String first, String last, String username, String pass) {
		this.first = first;
		this.last = last;
		this.username = username;
		this.pass = BCrypt.hashpw(pass, BCrypt.gensalt());
	}
	
	public boolean authenticate(String username, String pass) {
		return username.equals(this.username) && BCrypt.checkpw(pass, this.pass);
	}
}
