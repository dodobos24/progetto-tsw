package model;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int cartId;
	private int eventId;
	private int quantity;
	private String seatNumber;
	private float price;
	
	public CartItemBean() {}
	
	public CartItemBean(int id, int cartId, int eventId, int quantity, String seatNumber, float price) {
		this.id=id;
		this.cartId=cartId;
		this.eventId=eventId;
		this.quantity=quantity;
		this.seatNumber=seatNumber;
		this.price=price;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public int getCartId() { return cartId; }
	public void setCartId(int cartId) { this.cartId=cartId; }
	
	public int getEventId() { return eventId; }
	public void setEventId(int eventId) { this.eventId=eventId; }
	
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity=quantity; }
	
	public String getSeatNumber() { return seatNumber; }
	public void setSeatNumber(String seatNumber) { this.seatNumber=seatNumber; }
	
	public float getPrice() { return price; }
	public void setPrice(int price) { this.price=price; }
}
