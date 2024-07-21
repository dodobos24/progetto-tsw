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
	private float saldo;
	private boolean admin;
	private boolean valid;
	
	public UserBean() {}
	
	public UserBean(int id, String username, String password, String email, String name, String surname, float saldo, boolean admin) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.email=email;
		this.name=name;
		this.surname=surname;
		this.saldo=saldo;
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
	
	public float getSaldo() { return saldo; }
	public void setSaldo(float saldo) { this.saldo=saldo; }
	
	public boolean isAdmin() { return admin; }
	public void setAdmin(boolean admin) { this.admin=admin; }
	
	public boolean isValid() { return id>0; }
	public void setValid(boolean valid) { this.valid=valid; }
}
