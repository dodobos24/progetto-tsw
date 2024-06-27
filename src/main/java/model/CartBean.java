package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CartBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private LocalDateTime createdAt;
	
	public CartBean() {}
	
	public CartBean(int id, int userId, LocalDateTime createdAt) {
		this.id=id;
		this.userId=userId;
		this.createdAt=createdAt;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId=userId; }
	
	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt=createdAt; }
}
