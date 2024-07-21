package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EventBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private LocalDateTime date;
	private String venue;
	private String description;
	private String eventType;
	private String organizer;
	private String image;
	private float price;
	private int artistId;
	
	public EventBean() {}
	
	public EventBean(int id, String name, LocalDateTime date, String venue, String description, String eventType, String organizer, String image, float price, int artistId) {
		this.id=id;
		this.name=name;
		this.date=date;
		this.venue=venue;
		this.description=description;
		this.eventType=eventType;
		this.organizer=organizer;
		this.image=image;
		this.price=price;
		this.artistId=artistId;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id=id; }
	
	public String getName() { return name; }
	public void setName(String name) {this.name=name; }
	
	public LocalDateTime getDate() { return date; }
	public void setDate(LocalDateTime date) { this.date=date; }
	
	public String getVenue() { return venue; }
	public void setVenue(String venue) { this.venue=venue; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description=description; }
	
	public String getEventType() { return eventType; }
	public void setEventType(String eventType) { this.eventType=eventType; }
	
	public String getOrganizer() { return organizer; }
	public void setOrganizer(String organizer) { this.organizer=organizer; }
	
	public String getImage() { return image; }
	public void setImage(String image) { this.image=image; }
	
	public float getPrice() { return price; }
	public void setPrice(float price) { this.price=price; }
	
	public int getArtistId() { return artistId; }
	public void setArtistId(int artistId) { this.artistId=artistId; }
}
