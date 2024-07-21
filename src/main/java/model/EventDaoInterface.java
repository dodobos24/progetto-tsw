package model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface EventDaoInterface {
	void addEvent(EventBean event) throws SQLException;
    EventBean getEventById(int id) throws SQLException;
    List<EventBean> getAllEvents() throws SQLException;
    void updateEvent(EventBean event) throws SQLException;
    void deleteEvent(int id) throws SQLException;
    List<EventBean> getEventsByArtist(int artistId) throws SQLException;
    List<EventBean> getEventsByType(String type) throws SQLException;
    List<EventBean> getEventsByOrganizerId(int organizerId) throws SQLException;
    List<EventBean> searchEvents(String venue, LocalDateTime date, String eventType, String artist) throws SQLException;
    List<EventBean> getTrendingEvents() throws SQLException;
}
