package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PaymentBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int ticketId;
	private float amount;
	private LocalDateTime paymentDate;
	private String paymentMethod;
	
	public PaymentBean() {}
	
	public PaymentBean(int id, int userId, int ticketId, float amount, LocalDateTime paymentDate, String paymentMethod) {
		this.id=id;
		this.userId=userId;
		this.ticketId=ticketId;
		this.amount=amount;
		this.paymentDate=paymentDate;
		this.paymentMethod=paymentMethod;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId=userId; }
	
	public int getTicketId() { return ticketId; }
	public void setTicketId(int ticketId) { this.ticketId=ticketId; }
	
	public float getAmount() { return amount; }
	public void setAmount(int amount) { this.amount=amount; }
	
	public LocalDateTime getPaymentDate() { return paymentDate; }
	public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate=paymentDate; }
	
	public String getPaymentMethod() { return paymentMethod; }
	public void setPaymentMethod(String paymentMethod) { this.paymentMethod=paymentMethod; }
}
