package model;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDaoInterface {
	void addEvent(EventBean event);
    EventBean getEventById(int id);
    List<EventBean> getAllEvents();
    void updateEvent(EventBean event);
    void deleteEvent(int id);
    List<EventBean> getEventsByArtist(int artistId);
    List<EventBean> getEventsByType(String type);
    List<EventBean> getEventsByOrganizerId(int organizerId);
    List<EventBean> searchEvents(String venue, LocalDateTime date, String eventType, String artist);
}
