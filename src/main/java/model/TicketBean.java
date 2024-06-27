package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int eventId;
	private int userId;
	private LocalDateTime purchaseDate;
	private String seatNumber;
	private float price;
	
	public TicketBean() {}
	
	public TicketBean(int id, int eventId, int userId, LocalDateTime purchaseDate, String seatNumber, float price) {
		this.id=id;
		this.eventId=eventId;
		this.userId=userId;
		this.purchaseDate=purchaseDate;
		this.seatNumber=seatNumber;
		this.price=price;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public int getEventId() { return eventId; }
	public void setEventId(int eventId) { this.eventId=eventId; }
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId=userId; }
	
	public LocalDateTime getPurchaseDate() { return purchaseDate; }
	public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate=purchaseDate; }
	
	public String getSeatNumber() { return seatNumber; }
	public void setSeatNumber(String seatNumber) { this.seatNumber=seatNumber; }
	
	public float getPrice() { return price; }
	public void setPrice(float price) { this.price=price; }
}
