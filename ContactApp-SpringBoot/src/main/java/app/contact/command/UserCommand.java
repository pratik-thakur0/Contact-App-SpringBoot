package app.contact.command;

import app.contact.domain.User;

public class UserCommand {

	User user;   // Reuse domain
	// TODO: other properties if it is required
	// command is used for the form, for additional fields such as terms and conditions.
	// T&C are not in the database but it is req. so we make command class
	Boolean tnc;

	public Boolean getTnc() {
		return tnc;
	}

	public void setTnc(Boolean tnc) {
		this.tnc = tnc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
