package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements EventDaoInterface {

    @Override
    public void addEvent(EventBean event) {
        String sql = "INSERT INTO Events (name, date, venue, description, event_type, organizer, artist_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, event.getName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getDate()));
            statement.setString(3, event.getVenue());
            statement.setString(4, event.getDescription());
            statement.setString(5, event.getEventType());
            statement.setString(6, event.getOrganizer());
            statement.setInt(7, event.getArtistId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EventBean getEventById(int id) {
        String sql = "SELECT * FROM Events WHERE id = ?";
        EventBean event = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                int artistId = resultSet.getInt("artist_id");
                event = new EventBean(id, name, date, venue, description, eventType, organizer, artistId);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<EventBean> getAllEvents() {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, artistId);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void updateEvent(EventBean event) {
        String sql = "UPDATE Events SET name = ?, date = ?, venue = ?, description = ?, event_type = ?, organizer = ?, artist_id = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, event.getName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getDate()));
            statement.setString(3, event.getVenue());
            statement.setString(4, event.getDescription());
            statement.setString(5, event.getEventType());
            statement.setString(6, event.getOrganizer());
            statement.setInt(7, event.getArtistId());
            statement.setInt(8, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int id) {
        String sql = "DELETE FROM Events WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EventBean> getEventsByArtist(int artistId) {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE artist_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, artistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public List<EventBean> getEventsByType(String type) {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE event_type = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public List<EventBean> getEventsByOrganizerId(int organizerId) {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE organizer_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, organizerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    @Override
    public List<EventBean> searchEvents(String venue, LocalDateTime date, String eventType, String artist) {
        List<EventBean> events = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Events WHERE 1=1");
        if (venue != null && !venue.trim().isEmpty()) sql.append(" AND venue = ?");
        if (date != null) sql.append(" AND date = ?");
        if (eventType != null && !eventType.trim().isEmpty()) sql.append(" AND event_type = ?");
        if (artist != null && !artist.trim().isEmpty()) sql.append(" AND artist_id = ?");

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            
            int index = 1;
            if (venue != null && !venue.trim().isEmpty()) {
                statement.setString(index++, venue);
            }
            if (date != null) {
                statement.setTimestamp(index++, Timestamp.valueOf(date));
            }
            if (eventType != null && !eventType.trim().isEmpty()) {
                statement.setString(index++, eventType);
            }
            if (artist != null && !artist.trim().isEmpty()) {
                // Assuming artist is a string identifier, otherwise adapt accordingly
                statement.setString(index++, artist);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime resultDate = resultSet.getTimestamp("date").toLocalDateTime();
                String resultVenue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String resultEventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, resultDate, resultVenue, description, resultEventType, organizer, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}

