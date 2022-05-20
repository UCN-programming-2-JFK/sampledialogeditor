package sampledialogeditor.model;

public class User {

	private String name, email;
	private boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String name, String email, boolean isAdmin) {
		this.name = name;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	public User() {	}
	
	@Override
	public String toString() {
		return "User: " + getName() + ", email: " + getEmail() + ", admin: " + (isAdmin() ? "yes" : "no");
	}
}