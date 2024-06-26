package model;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private boolean admin;
	
	public UserBean() {}
	
	public UserBean(int id, String username, String password, String email, String name, String surname, boolean admin) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.email=email;
		this.name=name;
		this.surname=surname;
		this.admin=admin;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public String getNome() { return name; }
	public void setNome(String name) { this.name=name; }
	
	public String getCognome() { return surname; }
	public void setCognome(String surname) { this.surname=surname; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username=username; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email=email; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password=password; }
	
	public boolean isAdmin() { return admin; }
	public void setAdmin(boolean admin) { this.admin=admin; }
}
